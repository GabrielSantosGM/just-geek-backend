package br.com.justgeek.mobile.controller.produto;

import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.dto.AvaliacaoProdutoDTO;
import br.com.justgeek.mobile.dto.FreteDTO;
import br.com.justgeek.mobile.entities.AvaliacaoProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.dto.ProdutoDTO;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.mapper.produto.AvaliacaoProdutoMapper;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.frete.FreteServiceImpl;
import br.com.justgeek.mobile.service.impl.produto.ComprarProdutoServiceImpl;
import br.com.justgeek.mobile.service.impl.produto.ProdutoServiceImpl;
import br.com.justgeek.mobile.service.impl.produto.ProdutoFavoritoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(ProductController.class);

    private final ProdutoServiceImpl productServices;
    private final ProdutoFavoritoServiceImpl favoriteProductServices;
    private final ComprarProdutoServiceImpl buyProductServices;
    private final FreteServiceImpl freteService;

    @Autowired
    public ProductController(UsuarioRepository usuarioRepository,
                             ProdutoServiceImpl productServices,
                             ProdutoFavoritoServiceImpl favoriteProductServices,
                             ComprarProdutoServiceImpl buyProductServices, FreteServiceImpl freteService) {
        super(usuarioRepository);
        this.productServices = productServices;
        this.favoriteProductServices = favoriteProductServices;
        this.buyProductServices = buyProductServices;
        this.freteService = freteService;
    }

    @PostMapping("/register/{idRoupa}")
    public ResponseEntity<Produto> registerProduct(@PathVariable int idRoupa,
                                                   @RequestBody @Validated ProdutoDTO produto) {
        try {
            LOG.info("[PRODUTO] Cadastrando um novo produto...");
            productServices.cadastrarProduto(idRoupa, produto);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ContaException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/{idUser}/{idProduct}")
    public ResponseEntity<String> favoriteProduct(@PathVariable int idUser,
                                                  @PathVariable int idProduct) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[FAVORITO] Favoritando produto de ID {}", idProduct);
                favoriteProductServices.favoritarProduto(idUser, idProduct);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (ProdutoException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/move/{idUser}/{idFavorite}")
    public ResponseEntity<String> moveFavorite(@PathVariable int idUser,
                                               @PathVariable int idFavorite) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[COMPRAS] Movendo produto para o carrinho");
                buyProductServices.moverProdutoParaCarrinho(idUser, idFavorite);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (ProdutoException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/comprar/{idUser}/{idProduct}/{quantidade}")
    public ResponseEntity<String> buyProduct(@PathVariable int idUser,
                                             @PathVariable int idProduct,
                                             @PathVariable int quantidade) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[COMPRAS] Adicionando produto de ID {} no carrinho", idProduct);
                buyProductServices.adicionarProdutoNoCarrinho(idUser, idProduct, quantidade);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (ProdutoException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @PostMapping("/rate/{idUser}/{idProduct}")
    public ResponseEntity<AvaliacaoProduto> registerComment(@PathVariable int idUser,
                                                            @PathVariable int idProduct,
                                                            @RequestBody AvaliacaoProdutoDTO avaliacaoProdutoDTO) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[AVALIACAO] Registrando avaliacao do usuario de ID {} no produto de ID {}", idUser, idProduct);
                productServices.fazerAvaliacaoDoProduto(idUser, idProduct, avaliacaoProdutoDTO.transformarParaEntidade());
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (ProdutoException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/best-rating")
    public ResponseEntity<List<AvaliacaoProdutoMapper>> returnBestRating() {
        try {
            LOG.info("[AVALIACOES] Retornando as melhores avaliacoes dos produto.");
            return ResponseEntity.status(HttpStatus.OK).body(productServices.retornarMelhoresAvaliacoes());
        } catch (ProdutoException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/rating/{idProduct}")
    public ResponseEntity<List<AvaliacaoProdutoMapper>> returnProductReviews(@PathVariable int idProduct) {
        try {
            LOG.info("[AVALIACOES] Retornando avaliacoes do produto de ID {}...", idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(productServices.retornarAvaliacoesDoProduto(idProduct));
        } catch (ProdutoException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @PostMapping("/frete/{cep}")
    public ResponseEntity<FreteDTO> shippingValue(@PathVariable String cep) {
        try {
            LOG.info("BUSCANDO INFORMAÇÕES DOS CORREIOS PARA O CEP: {}", cep);
            return ResponseEntity.status(HttpStatus.OK).body(freteService.calcularFrete(cep));
        } catch (IllegalStateException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
