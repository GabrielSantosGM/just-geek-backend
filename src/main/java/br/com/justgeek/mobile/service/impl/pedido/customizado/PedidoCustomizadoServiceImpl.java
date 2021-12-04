package br.com.justgeek.mobile.service.impl.pedido.customizado;

import br.com.justgeek.mobile.dto.CompraCustomizadaDTO;
import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.repository.ArtistaRepository;
import br.com.justgeek.mobile.repository.PedidoCustomizadoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.PedidoCustomizadoService;
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

@Service
public class PedidoCustomizadoServiceImpl implements PedidoCustomizadoService {

    private static final String ACCESS_TOKEN = "TEST-1779838665200913-102407-e4c969c2348b8ccd3dff3758d9056e30-1005961803";
    private static final Logger LOG = LoggerFactory.getLogger(PedidoCustomizadoServiceImpl.class);

    private final PedidoCustomizadoRepository pedidoCustomizadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArtistaRepository artistaRepository;

    private Preference preferencia = null;

    @Autowired
    public PedidoCustomizadoServiceImpl(PedidoCustomizadoRepository pedidoCustomizadoRepository,
                                        UsuarioRepository usuarioRepository,
                                        ArtistaRepository artistaRepository) {
        this.pedidoCustomizadoRepository = pedidoCustomizadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.artistaRepository = artistaRepository;
    }

    public void setarToken() {
        try {
            MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        } catch (MPConfException e) {
            e.printStackTrace();
        }
    }

    public Item gerandoCompraASerPaga(CompraCustomizadaDTO compraDTO) {
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
    public PedidoCustomizadoServiceImpl cadastrar(int idUsuario, int idArtista, PedidoCustomizadoDTO pedidoCustomizado) {
        LOG.info("RECUPERANDO DADOS DO USUARIO.");
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario)
                .orElseThrow(() -> {
                    throw new NullPointerException("NENHUM USUARIO DE ID " + idUsuario + " ENCONTRADO");
                });

        LOG.info("RECUPERANDO DADOS DO ARTISTA.");
        Artista artista = artistaRepository.findByIdArtista(idArtista)
                .orElseThrow(() -> {
                    throw new NullPointerException("NENHUM ARTISTA DE ID " + idArtista + " ENCONTRADO");
                });

        LOG.info("ASSOCIANDO USUARIO E ARTISTA A UM PEDIDO.");
        pedidoCustomizado.dtoToEntity(usuario, artista);

        LOG.info("SALVANDO PEDIDO.");
        pedidoCustomizadoRepository.save(pedidoCustomizado.dtoToEntity(usuario, artista));

        LOG.info("INSERINDO TOKEN DE ACESSO");
        setarToken();

        CompraCustomizadaDTO compraCustomizadaDTO = new CompraCustomizadaDTO(usuario);

        LOG.info("GERANDO COMPRA NO MERCADO PAGO");
        Item item = gerandoCompraASerPaga(compraCustomizadaDTO);

        LOG.info("SALVANDO INFORMAÇÕES DA COMPRA");
        preferencia = acrescentarESalvar(item);

        return this;
    }

    public MercadoPagoPreferenceDTO retornarPreferenceMP() {
        return new MercadoPagoPreferenceDTO(preferencia);
    }
}
