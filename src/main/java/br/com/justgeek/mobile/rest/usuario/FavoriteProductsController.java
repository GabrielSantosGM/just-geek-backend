package br.com.justgeek.mobile.rest.usuario;

import br.com.justgeek.mobile.mapper.produto.ProdutoFavoritoMapper;
import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.entities.ProdutoFavorito;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.messages.ContaUsuarioMensagens;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.produto.ProdutoFavoritoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/favorites")
public class FavoriteProductsController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(FavoriteProductsController.class);

    private final ProdutoFavoritoServiceImpl favoriteProductServices;

    @Autowired
    public FavoriteProductsController(UsuarioRepository usuarioRepository,
                                      ProdutoFavoritoServiceImpl favoriteProductServices) {
        super(usuarioRepository);
        this.favoriteProductServices = favoriteProductServices;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<ProdutoFavoritoMapper>> returnFavoriteProducts(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[Favoritos] Retornando lista de produtos favoritados.");
                return ResponseEntity.status(HttpStatus.OK).body(favoriteProductServices.retornarProdutosFavoritosDoUsuario(idUser));
            } catch (ProdutoException e) {
                LOG.info(e.getMessage());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            LOG.warn(ContaUsuarioMensagens.MENSAGEM_UNAUTHORIZED, idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{idUser}/{idProduct}")
    public ResponseEntity<ProdutoFavorito> disfavorProduct(@PathVariable int idUser, @PathVariable int idProduct) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[Favoritados] Desfavoritando produto.");
                favoriteProductServices.desfavoritarProduto(idUser, idProduct);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (RuntimeException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(ContaUsuarioMensagens.MENSAGEM_UNAUTHORIZED, idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
