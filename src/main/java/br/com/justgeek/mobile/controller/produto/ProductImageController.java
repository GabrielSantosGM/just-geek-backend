package br.com.justgeek.mobile.controller.produto;

import br.com.justgeek.mobile.exceptions.ImagemException;
import br.com.justgeek.mobile.service.impl.imagens.ImagemProdutoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/products-images")
public class ProductImageController {

    private static final Logger LOG = LoggerFactory.getLogger(ProductImageController.class);

    private final ImagemProdutoServiceImpl imagemProdutoService;

    @Autowired
    public ProductImageController(ImagemProdutoServiceImpl imagemProdutoService) {
        this.imagemProdutoService = imagemProdutoService;
    }

    @PostMapping("/{idProduct}")
    public ResponseEntity<List<MultipartFile>> uploadImagemProduto(@PathVariable int idProduct,
                                                                   @RequestParam MultipartFile imagem1,
                                                                   @RequestParam MultipartFile imagem2,
                                                                   @RequestParam MultipartFile imagem3) {
        try {
            imagemProdutoService.uploadImagemProduto(idProduct, imagem1, imagem2, imagem3);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ImagemException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @GetMapping("/images/{idProduct}")
    public ResponseEntity<List<String>> returnFirstImage(@PathVariable int idProduct) {
        try {
            LOG.info("[IMAGEM DO PRODUTO] RETORNANDO IMAGENS!");
            return ResponseEntity.status(200).body(imagemProdutoService.retornarImagens(idProduct));
        } catch (NullPointerException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(204).build();
        }
    }
}
