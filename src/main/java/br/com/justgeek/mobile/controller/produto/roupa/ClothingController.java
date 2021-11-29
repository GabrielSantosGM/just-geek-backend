package br.com.justgeek.mobile.controller.produto.roupa;

import br.com.justgeek.mobile.entities.Roupa;
import br.com.justgeek.mobile.dto.RoupaDTO;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.service.impl.produto.roupa.RoupaServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/clothing")
public class ClothingController {

    private static final Logger LOG = LoggerFactory.getLogger(ClothingController.class);

    private final RoupaServiceImpl roupaService;

    @Autowired
    public ClothingController(RoupaServiceImpl roupaService) {
        this.roupaService = roupaService;
    }

    @PostMapping
    public ResponseEntity<Roupa> registerClothing(@RequestBody @Validated RoupaDTO roupa) {
        try {
            LOG.info("[Roupa] Cadastrando uma roupa...");
            roupaService.cadastrarRoupa(roupa);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ProdutoException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

}
