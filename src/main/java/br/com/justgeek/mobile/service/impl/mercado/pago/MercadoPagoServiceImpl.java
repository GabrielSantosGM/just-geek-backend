package br.com.justgeek.mobile.service.impl.mercado.pago;

import br.com.justgeek.mobile.dto.CompraDTO;
import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.entities.Carrinho;
import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.repository.CarrinhoRepository;
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

import java.time.LocalDateTime;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    private static final Logger LOG = LoggerFactory.getLogger(MercadoPagoServiceImpl.class);

    private static final String ACCESS_TOKEN = "TEST-1779838665200913-102407-e4c969c2348b8ccd3dff3758d9056e30-1005961803";

    private final PedidoRepository pedidoRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final UsuarioRepository usuarioRepository;

    private Preference preferencia = null;

    @Autowired
    public MercadoPagoServiceImpl(CarrinhoRepository carrinhoRepository,
                                  PedidoRepository pedidoRepository,
                                  UsuarioRepository usuarioRepository) {
        this.pedidoRepository = pedidoRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.usuarioRepository = usuarioRepository;
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
    public MercadoPagoServiceImpl finalizarCompra(int idUsuario, Double valorFrete) {

        LOG.info("VERIFICANDO O CARRINHO");
        Carrinho carrinhoVerificado = verificarCarrinho(idUsuario);
        carrinhoVerificado.setValorTotal(carrinhoVerificado.getValorTotal() + valorFrete);
        carrinhoVerificado.setFinalizado(true);

        LOG.info("INSERINDO TOKEN DE ACESSO");
        setarToken();

        CompraDTO compraDTO = new CompraDTO(carrinhoVerificado);
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
        pedido.setFkUsuario(verificarUsuario(idUsuario));
        return pedidoRepository.save(pedido);
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
                    throw new CarrinhoException("[VERIFICAÇÃO CARRINHO] FALHA AO RECUPERAR O CARRINHO DE ID " + idUsuario);
                });
    }
}
