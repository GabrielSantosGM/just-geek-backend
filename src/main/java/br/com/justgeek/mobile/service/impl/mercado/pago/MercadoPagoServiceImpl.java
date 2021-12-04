package br.com.justgeek.mobile.service.impl.mercado.pago;

import br.com.justgeek.mobile.dto.CompraDTO;
import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.entities.Carrinho;
import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.enums.CupomDescontoEnum;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.repository.CarrinhoRepository;
import br.com.justgeek.mobile.repository.CupomDeDescontoRepository;
import br.com.justgeek.mobile.repository.PedidoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.MercadoPagoService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.NoSuchElementException;
import java.util.Optional;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    private static final Logger LOG = LoggerFactory.getLogger(MercadoPagoServiceImpl.class);

    private static final String ACCESS_TOKEN = "TEST-1779838665200913-102407-e4c969c2348b8ccd3dff3758d9056e30-1005961803";

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;
    private final CupomDeDescontoRepository cupomDeDescontoRepository;
    private final DecimalFormat formatadorDecimal;

    private Preference preferencia = null;

    @Autowired
    public MercadoPagoServiceImpl(CarrinhoRepository carrinhoRepository,
                                  PedidoRepository pedidoRepository,
                                  UsuarioRepository usuarioRepository,
                                  CupomDeDescontoRepository cupomDeDescontoRepository) {
        this.pedidoRepository = pedidoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.usuarioRepository = usuarioRepository;
        this.cupomDeDescontoRepository = cupomDeDescontoRepository;
        this.formatadorDecimal = new DecimalFormat("#.00");
    }

    public void setarToken() {
        try {
            MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        } catch (MPConfException e) {
            e.printStackTrace();
        }
    }

    public Item gerandoCompraASerPaga(CompraDTO compraDTO) {
        Item item = new Item();
        return item.setTitle(compraDTO.getProtocoloPedido())
                .setQuantity(1)
                .setUnitPrice(compraDTO.getValorTotal().floatValue());
    }

    public Preference acrescentarESalvar(Item item) {
        Preference preference = new Preference();
        preference.setBackUrls(criarBackUrls());
        preference.appendItem(item);

        try {
            preference.save();
        } catch (MPException e) {
            e.printStackTrace();
        }
        return preference;
    }

    public BackUrls criarBackUrls() {
        BackUrls backUrls = new BackUrls();
        return backUrls.setSuccess("") //TODO: endpoint para sucesso, falha, e pendencia
                .setFailure("")
                .setPending("");
    }

    @Override
    public MercadoPagoServiceImpl finalizarCompra(int idUsuario, Double valorFrete, Optional<String> cupom) {

        LOG.info("VERIFICANDO O CARRINHO");
        Carrinho carrinhoVerificado = verificarCarrinho(idUsuario);

        Double valorProdutos = carrinhoVerificado.getValorTotal();
        double descontoComCupom = 0.0;

        LOG.info("VERIFICANDO O CUPOM : [ {} ]", cupom.get());
        if (!cupom.get().isEmpty()) {
            LOG.info("EXECUTANDO OPERACAO DE CALCULO DA COMPRA COM O CUPOM [ {} ]", cupom.get());
            descontoComCupom = ((carrinhoVerificado.getValorTotal() + valorFrete) * validaCupom(cupom.get())) / 100;
            String valorFormatado = formatadorDecimal.format((carrinhoVerificado.getValorTotal() + valorFrete) - descontoComCupom).replace(",",".");
            carrinhoVerificado.setValorTotal(Double.parseDouble(valorFormatado));
        } else{
            LOG.info("EXECUTANDO OPERACAO DE CALCULO DA COMPRA SEM O CUPOM");
            carrinhoVerificado.setValorTotal(carrinhoVerificado.getValorTotal() + valorFrete);
        }
        carrinhoVerificado.setFinalizado(true);

        LOG.info("INSERINDO TOKEN DE ACESSO");
        setarToken();

        CompraDTO compraDTO = new CompraDTO(carrinhoVerificado, valorProdutos, descontoComCupom, valorFrete);
        carrinhoVerificado.setFkPedido(gerarPedido(compraDTO, idUsuario));
        carrinhoRepository.save(carrinhoVerificado);

        LOG.info("GERANDO COMPRA NO MERCADO PAGO");
        Item item = gerandoCompraASerPaga(compraDTO);

        LOG.info("SALVANDO INFORMAÇÕES DA COMPRA");
        preferencia = acrescentarESalvar(item);

        return this;
    }

    private Pedido gerarPedido(CompraDTO compraDTO, int idUsuario) {
        LOG.info("GERANDO PEDIDO");
        Pedido pedido = new Pedido();
        pedido.setCodCompra(compraDTO.getProtocoloPedido());
        pedido.setSituacao("Em faturamento");
        pedido.setDataHora(LocalDateTime.now());
        pedido.setValorCupom(compraDTO.getValorCupom());
        pedido.setValorFrete(compraDTO.getValorFrete());
        pedido.setValorProdutos(compraDTO.getValorProdutos());
        pedido.setFkUsuario(verificarUsuario(idUsuario));
        return pedidoRepository.save(pedido);
    }

    private Integer validaCupom(String cupom) {
        return cupomDeDescontoRepository
                .findByNomeCupomAndDataInicioVigenciaLessThanAndDataFimVigenciaGreaterThan(cupom, LocalDate.now(), LocalDate.now())
                .orElseThrow(() -> {
                    throw new NoSuchElementException("NAO FOI ENCONTRADO CUPOM COM ESSE NOME OU ESTA FORA DA DATA DE VALIDADE!");
                }).getPorcentagemDesconto();
    }

    @Override
    public MercadoPagoPreferenceDTO retornarPreferenceMP() {
        return new MercadoPagoPreferenceDTO(preferencia);
    }

    public Usuario verificarUsuario(int idUsuario) {
        return usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(() -> {
            throw new NullPointerException("NENHUM USUARIO DE ID " + idUsuario + " ENCONTRADO.");
        });
    }

    public Carrinho verificarCarrinho(int idUsuario) {
        return carrinhoRepository.findByFinalizadoFalseAndFkUsuarioIdUsuario(idUsuario).
                orElseThrow(() -> {
                    throw new CarrinhoException("[VERIFICAÇÃO CARRINHO] FALHA AO RECUPERAR O CARRINHO DO USUARIO DE ID " + idUsuario);
                });
    }
}
