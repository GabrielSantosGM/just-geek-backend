package br.com.justgeek.mobile.controller.artista;

import br.com.justgeek.mobile.exceptions.ImagemException;
import br.com.justgeek.mobile.service.ImagemArtistaService;
import br.com.justgeek.mobile.service.impl.imagens.ImagemArtistaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/artist-image")
public class ArtistImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ArtistImageController.class);

    private final ImagemArtistaService imagemArtistaService;

    @Autowired
    public ArtistImageController(ImagemArtistaServiceImpl imagemArtistaService) {
        this.imagemArtistaService = imagemArtistaService;
    }

    @PostMapping("/{idArtist}")
    public ResponseEntity<List<String>> uploadImageArtist(@PathVariable int idArtist,
                                                          @RequestParam String image1,
                                                          @RequestParam String image2,
                                                          @RequestParam String image3) {
        try {
            imagemArtistaService.uploadImagemArtista(idArtist, image1, image2, image3);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ImagemException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/perfil/{idArtist}")
    public ResponseEntity<String> returnImagePerfil(@PathVariable int idArtist) {
        try {
            LOG.info("[IMAGEM DO ARTISTA] RETORNANDO IMAGEM DE PERFIL DO ARTISTA!");
            return ResponseEntity.status(HttpStatus.OK).body(imagemArtistaService.retornarImagemPerfil(idArtist));
        } catch (NullPointerException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/images/{idArtist}")
    public ResponseEntity<List<String>> returnImages(@PathVariable int idArtist) {
        try {
            LOG.info("[IMAGEM DO ARTISTA] RETORNANDO IMAGENS DO ARTISTA!");
            return ResponseEntity.status(200).body(imagemArtistaService.retornarImagensArtista(idArtist));
        } catch (NullPointerException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(204).build();
        }
    }
}
