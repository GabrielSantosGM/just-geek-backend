package br.com.justgeek.mobile.controller.artista;

import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.dto.ArtistaDTO;
import br.com.justgeek.mobile.dto.ProgramasHabilidadesDTO;
import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.exceptions.ArtistaException;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.mapper.artista.ArtistaFavoritadoMapper;
import br.com.justgeek.mobile.mapper.artista.ArtistaMapper;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.artista.ArtistaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artists")
public class ArtistController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistController.class);

    private final ArtistaServiceImpl artistService;

    @Autowired
    public ArtistController(UsuarioRepository usuarioRepository, ArtistaServiceImpl artistService) {
        super(usuarioRepository);
        this.artistService = artistService;
    }

    @PostMapping("/register")
    public ResponseEntity<Artista> registerArtist(@RequestBody ArtistaDTO artistaDTO) {
        try {
            LOG.info("[ARTISTA] Cadastrando novo artista.");
            artistService.cadastrar(artistaDTO.transformarParaEntidade());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ArtistaException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping
    public ResponseEntity<List<ArtistaMapper>> returnsAllArtists() {
        try {
            LOG.info("[ARTISTA] Retornando todos os artistas.");
            return ResponseEntity.status(HttpStatus.OK).body(artistService.retornarTodosOsArtistas());
        } catch (ArtistaException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/similar/{idArtist}")
    public ResponseEntity<List<ArtistaMapper>> returnsSimilarArtists(@PathVariable int idArtist) {
        try {
            LOG.info("[ARTISTA] Retornando todos os artistas semelhantes com o Artista de ID {}", idArtist);
            return ResponseEntity.status(HttpStatus.OK).body(artistService.retornarArtistasSemelhantes(idArtist));
        } catch (ArtistaException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/{idArtist}")
    public ResponseEntity<ArtistaMapper> returnSpecificArtist(@PathVariable int idArtist) {
        try {
            LOG.info("[ARTISTA] Retornando Artista de ID {}...", idArtist);
            return ResponseEntity.status(HttpStatus.OK).body(artistService.retornarArtistaEspecifico(idArtist));
        } catch (ProdutoException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/my-list/{idUser}")
    public ResponseEntity<List<ArtistaFavoritadoMapper>> returnsFavoriteArtists(@PathVariable int idUser) {
        try {
            LOG.info("[ARTISTA] Retornando artistas favoritados pelo usuário de ID {}", idUser);
            return ResponseEntity.status(HttpStatus.OK).body(artistService.artistasFavoritados(idUser));
        } catch (ArtistaException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/favorite/{idUser}/{idArtist}")
    public ResponseEntity<String> favoriteArtist(@PathVariable int idUser, @PathVariable int idArtist) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[ARTISTA] Favoritando artista.");
                artistService.favoritarArtista(idUser, idArtist);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (ArtistaException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_AUTENTICACAO.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/{idArtist}")
    public ResponseEntity<String> registerSkill(@PathVariable int idArtist,
                                                @RequestBody ProgramasHabilidadesDTO programasHabilidades) {
        try {
            artistService.cadastrarSkillPrograma(idArtist, programasHabilidades.dtoToEntity());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ArtistaException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @DeleteMapping("/disfavor/{idUser}/{idArtist}")
    public ResponseEntity<String> disfavorArtist(@PathVariable int idUser, @PathVariable int idArtist) {
        if (authenticate(idUser)) {
            artistService.desfavoritarArtista(idUser, idArtist);
            return ResponseEntity.status(HttpStatus.OK).build();
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_AUTENTICACAO.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
