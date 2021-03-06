package br.com.justgeek.mobile.service.impl.artista;

import br.com.justgeek.mobile.dto.ProgramasHabilidadesDTO;
import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ArtistaFavorito;
import br.com.justgeek.mobile.entities.ProgramasHabilidadesArtista;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesArtistaEnum;
import br.com.justgeek.mobile.exceptions.ArtistaException;
import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.mapper.artista.ArtistaFavoritadoMapper;
import br.com.justgeek.mobile.mapper.artista.ArtistaMapper;
import br.com.justgeek.mobile.repository.*;
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
    private final ProgramasHabilidadesArtistaRepository programasHabilidadesArtistaRepository;

    @Autowired
    public ArtistaServiceImpl(ArtistaRepository artistaRepository,
                              UsuarioRepository usuarioRepository,
                              ArtistaFavoritoRepository artistaFavoritoRepository,
                              ProgramasHabilidadesArtistaRepository programasHabilidadesArtistaRepository) {
        this.artistaRepository = artistaRepository;
        this.usuarioRepository = usuarioRepository;
        this.artistaFavoritoRepository = artistaFavoritoRepository;
        this.programasHabilidadesArtistaRepository = programasHabilidadesArtistaRepository;
    }

    @Override
    public Artista cadastrar(Artista artista) {
        Optional<Artista> artistaVerificado = artistaRepository.findByApelido(artista.getApelido());

        if (artistaVerificado.isPresent()) {
            throw new ArtistaException("[ARTISTA] J?? existe uma conta de artista cadastrada com esse apelido.");
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
            throw new ArtistaException("[ARTISTA] N??o foi encontrado o Artista de ID " + idArtista);
        }
    }

    @Override
    public List<ArtistaMapper> retornarTodosOsArtistas() {
        List<Artista> artistasParceiros = artistaRepository.findAll();

        if (artistasParceiros.isEmpty()) {
            throw new ArtistaException("[ARTISTA] Nenhum artista cadastrado.");
        } else {
            return EntidadeParaMapperListaUtils.listaArtista(artistasParceiros);
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
            throw new ArtistaException("[ARTISTA] Esse artista j?? est?? favoritado!");
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

    @Override
    public void cadastrarSkillPrograma(int idArtista, ProgramasHabilidadesArtista programasHabilidadesArtista) {
        Artista artista = verificaArtista(idArtista);

        try {
            programasHabilidadesArtistaRepository.save(ProgramasHabilidadesDTO.getInstance(
                    programasHabilidadesArtista.getHabilidade(),
                    programasHabilidadesArtista.getPrograma(),
                    artista).dtoToEntity());
        } catch (ArtistaException e) {
            LOG.error(e.getMessage());
            throw new ArtistaException("Erro ao cadastrar habilidade do artista.");
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
                    throw new ContaException("[Verifica????o Usuario] Falha ao recuperar os dados do usuario.");
                });
    }

    private Artista verificaArtista(int idArtista) {
        return artistaRepository
                .findById(idArtista)
                .orElseThrow(() -> {
                    throw new ArtistaException("[Verifica????o Artista] Falha ao recuperar os dados do artista.");
                });
    }
}
