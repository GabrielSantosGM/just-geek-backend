package br.com.justgeek.mobile.service.impl.pedido.customizado;

import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.PedidoCustomizado;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.repository.ArtistaRepository;
import br.com.justgeek.mobile.repository.PedidoCustomizadoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.PedidoCustomizadoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class PedidoCustomizadoServiceImpl implements PedidoCustomizadoService {

    private static final Logger LOG = LoggerFactory.getLogger(PedidoCustomizadoServiceImpl.class);

    private final PedidoCustomizadoRepository pedidoCustomizadoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArtistaRepository artistaRepository;

    @Autowired
    public PedidoCustomizadoServiceImpl(PedidoCustomizadoRepository pedidoCustomizadoRepository,
                                        UsuarioRepository usuarioRepository,
                                        ArtistaRepository artistaRepository) {
        this.pedidoCustomizadoRepository = pedidoCustomizadoRepository;
        this.usuarioRepository = usuarioRepository;
        this.artistaRepository = artistaRepository;
    }

    @Override
    public PedidoCustomizado cadastrar(int idUsuario, int idArtista, PedidoCustomizadoDTO pedidoCustomizado) {
        LOG.info("RECUPERANDO DADOS DO USUARIO.");
        Usuario usuario = usuarioRepository.findByIdUsuario(idUsuario).orElseThrow();

        LOG.info("RECUPERANDO DADOS DO ARTISTA.");
        Artista artista = artistaRepository.findByIdArtista(idArtista).orElseThrow();

        LOG.info("ASSOCIANDO USUARIO E ARTISTA A UM PEDIDO.");
        pedidoCustomizado.dtoToEntity(usuario, artista);

        LOG.info("SALVANDO PEDIDO.");
        return pedidoCustomizadoRepository.save(pedidoCustomizado.dtoToEntity(usuario, artista));
    }
}
