package br.com.justgeek.mobile.rest.produto;

import br.com.justgeek.mobile.mapper.produto.ProdutoMapper;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.service.impl.produto.ProdutoFiltroServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/filter")
public class FilterProductController {

    private static final Logger LOG = LoggerFactory.getLogger(FilterProductController.class);

    private final ProdutoFiltroServiceImpl produtoService;

    @Autowired
    public FilterProductController(ProdutoFiltroServiceImpl produtoService) {
        this.produtoService = produtoService;
    }

    @GetMapping("/product/{idProduct}")
    public ResponseEntity<ProdutoMapper> returnSpecificProduct(@PathVariable int idProduct) {
        try {
            LOG.info("[Produtos] Retornando produto de ID {}...", idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutoEspecifico(idProduct));
        } catch (ProdutoException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/similar/{idProduct}")
    public ResponseEntity<List<ProdutoMapper>> returnsSimilarProducts(@PathVariable int idProduct) {
        try {
            LOG.info("[Produtos] Retornando produtos semelhantes do produto de ID {}...", idProduct);
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosSemelhantes(idProduct));
        } catch (ProdutoException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/category/{categoria}")
    public ResponseEntity<List<ProdutoMapper>> returnsCategoryProducts(@PathVariable String categoria) {
        try {
            LOG.info("[Produtos] Retornando produtos de categoria {}...", categoria);
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosCategoria(categoria));
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/theme/{tema}")
    public ResponseEntity<List<ProdutoMapper>> returnsThemeProducts(@PathVariable String tema) {
        try {
            LOG.info("[Produtos] Retornando produtos de tema {}...", tema);
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosTema(tema));
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/clothing/{roupa}")
    public ResponseEntity<List<ProdutoMapper>> returnsProductsByClothingModel(@PathVariable String roupa) {
        try {
            LOG.info("[Produtos] Retornando produtos de roupa {}...", roupa);
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosPorRoupa(roupa));
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/all-the-products")
    public ResponseEntity<List<ProdutoMapper>> returnsAllProducts() {
        try {
            LOG.info("[Produtos] Retornando todos os produtos...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarTodosProdutos());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/most-popular")
    public ResponseEntity<List<ProdutoMapper>> returnsMostPopularProducts() {
        try {
            LOG.info("[Produtos] Retornando produtos mais populares...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosMaisPopulares());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/less-popular")
    public ResponseEntity<List<ProdutoMapper>> returnsLessPopularProducts() {
        try {
            LOG.info("[Produtos] Retornando produtos menos populares...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosMenosPopulares());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/customized")
    public ResponseEntity<List<ProdutoMapper>> returnsCustomizedProducts() {
        try {
            LOG.info("[Produtos] Retornando produtos customizados...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosCustomizados());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/increasing-price")
    public ResponseEntity<List<ProdutoMapper>> returnsProductsIncreasingPrice() {
        try {
            LOG.info("[Produtos] Retornando produtos com preços na ordem crescente...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosPrecoCrescente());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }

    @GetMapping("/decreasing-price")
    public ResponseEntity<List<ProdutoMapper>> returnsProductsDecreasingPrice() {
        try {
            LOG.info("[Produtos] Retornando produtos com preços na ordem decrescente...");
            return ResponseEntity.status(HttpStatus.OK).body(produtoService.retornarProdutosPrecoDecrescente());
        } catch (ProdutoException e) {
            LOG.info(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
