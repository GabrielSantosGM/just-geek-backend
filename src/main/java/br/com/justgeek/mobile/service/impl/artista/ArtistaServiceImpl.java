package br.com.justgeek.mobile.service.impl.artista;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ArtistaFavorito;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesArtistaEnum;
import br.com.justgeek.mobile.exceptions.ArtistaException;
import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.mapper.artista.ArtistaFavoritadoMapper;
import br.com.justgeek.mobile.mapper.artista.ArtistaMapper;
import br.com.justgeek.mobile.repository.ArtistaFavoritoRepository;
import br.com.justgeek.mobile.repository.ArtistaRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.ArtistaService;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ArtistaServiceImpl implements ArtistaService {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistaServiceImpl.class);

    private final ArtistaRepository artistaRepository;
    private final UsuarioRepository usuarioRepository;
    private final ArtistaFavoritoRepository artistaFavoritoRepository;

    @Autowired
    public ArtistaServiceImpl(ArtistaRepository artistaRepository,
                              UsuarioRepository usuarioRepository, ArtistaFavoritoRepository artistaFavoritoRepository) {
        this.artistaRepository = artistaRepository;
        this.usuarioRepository = usuarioRepository;
        this.artistaFavoritoRepository = artistaFavoritoRepository;
    }

    @Override
    public Artista cadastrar(Artista artista) {
        Optional<Artista> artistaVerificado = artistaRepository.findByApelido(artista.getApelido());

        if (artistaVerificado.isPresent()) {
            throw new ArtistaException("[ARTISTA] Já existe uma conta de artista cadastrada com esse apelido.");
        } else {
            try {
                return artistaRepository.save(artista);
            } catch (Exception e) {
                throw new ArtistaException(RespostasRequisicoesArtistaEnum.MENSAGEM_VALIDACAO.getResposta());
            }
        }
    }

    @Override
    public ArtistaMapper retornarArtistaEspecifico(int idArtista) {
        Optional<Artista> artista = artistaRepository.findByIdArtista(idArtista);

        if (artista.isPresent()) {
            return ArtistaMapper.transformar(artista.get());
        } else {
            throw new ArtistaException("[ARTISTA] Não foi encontrado o Artista de ID " + idArtista);
        }
    }

    @Override
    public List<Artista> retornarTodosOsArtistas() {
        List<Artista> artistasParceiros = artistaRepository.findAll();

        if (artistasParceiros.isEmpty()) {
            throw new ArtistaException("[ARTISTA] Nenhum artista cadastrado.");
        } else {
            return artistasParceiros;
        }
    }

    @Override
    public List<ArtistaMapper> retornarArtistasSemelhantes(int idArtista) {
        Artista artistaReferencia = verificaArtista(idArtista);
        List<Artista> artistasSemelhantes = artistaRepository.findByCategoriaIgnoreCaseContains(artistaReferencia.getCategoria());

        if (artistasSemelhantes.isEmpty()) {
            throw new ArtistaException("[ARTISTA] Nenhum artista semelhante foi recuperado");
        } else {
            return EntidadeParaMapperListaUtils.listaArtista(artistasSemelhantes);
        }
    }

    @Override
    public void favoritarArtista(int idUsuario, int idArtista) {
        Optional<ArtistaFavorito> artistaFavoritado = artistaFavoritoRepository.findByFkUsuarioIdUsuarioAndFkArtistaIdArtistaAndFavoritoTrue(idUsuario, idArtista);

        ArtistaFavorito favoritandoArtista = new ArtistaFavorito();
        Usuario usuario = verificaUsuario(idUsuario);
        Artista artista = verificaArtista(idArtista);

        if (artistaFavoritado.isPresent()) {
            throw new ArtistaException("[ARTISTA] Esse artista já está favoritado!");
        } else {
            favoritandoArtista.setFkUsuario(usuario);
            favoritandoArtista.setFkArtista(artista);
            favoritandoArtista.setFavorito(true);
            artistaFavoritoRepository.save(favoritandoArtista);
        }
    }

    @Override
    public void desfavoritarArtista(int idUsuario, int idArtista) {
        ArtistaFavorito artistaFavoritado = verificarArtistaFavoritado(idUsuario, idArtista);

        artistaFavoritado.setIdArtistaFavorito(artistaFavoritado.getIdArtistaFavorito());
        artistaFavoritado.setFavorito(false);
        artistaFavoritoRepository.save(artistaFavoritado);
    }

    @Override
    public List<ArtistaFavoritadoMapper> artistasFavoritados(int idUsuario) {
        List<ArtistaFavorito> artistasFavoritados = artistaFavoritoRepository.findAllByFkUsuarioIdUsuarioAndFavoritoTrue(idUsuario);

        if (artistasFavoritados.isEmpty()) {
            throw new ArtistaException("[ARTISTA] Nenhum artista favoritado foi encontrado");
        } else {
            return EntidadeParaMapperListaUtils.listaArtistasFavoritados(artistasFavoritados);
        }
    }

    private ArtistaFavorito verificarArtistaFavoritado(int idUsuario, int idArtista) {
        return artistaFavoritoRepository
                .findByFkUsuarioIdUsuarioAndFkArtistaIdArtistaAndFavoritoTrue(idUsuario, idArtista)
                .orElseThrow(() -> {
                    throw new ContaException("[Verificacao Artista Favoritado] Nenhum artista favoritado recuperado.");
                });
    }

    private Usuario verificaUsuario(int idUsuario) {
        return usuarioRepository
                .findByIdUsuario(idUsuario)
                .orElseThrow(() -> {
                    throw new ContaException("[Verificação Usuario] Falha ao recuperar os dados do usuario.");
                });
    }

    private Artista verificaArtista(int idArtista) {
        return artistaRepository
                .findById(idArtista)
                .orElseThrow(() -> {
                    throw new ArtistaException("[Verificação Artista] Falha ao recuperar os dados do artista.");
                });
    }
}
