package br.com.justgeek.mobile.controller.pedido.customizado;

import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.entities.PedidoCustomizado;
import br.com.justgeek.mobile.service.PedidoCustomizadoService;
import br.com.justgeek.mobile.service.impl.pedido.customizado.PedidoCustomizadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom-order")
public class CustomOrderController {

    private static final Logger LOG = LoggerFactory.getLogger(CustomOrderController.class);

    private final PedidoCustomizadoService pedidoCustomizadoService;

    @Autowired
    public CustomOrderController(PedidoCustomizadoServiceImpl pedidoCustomizadoService) {
        this.pedidoCustomizadoService = pedidoCustomizadoService;
    }

    @PostMapping("/{idUser}/{idArtist}")
    public ResponseEntity<PedidoCustomizado> registerOrder(@PathVariable int idUser,
                                                           @PathVariable int idArtist,
                                                           @RequestBody PedidoCustomizadoDTO pedidoCustomizado) {
        try {
            LOG.info("REGISTRANDO PEDIDO DO USUARIO DE ID [ {} ]", idUser);
            pedidoCustomizadoService.cadastrar(idUser, idArtist, pedidoCustomizado);
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (Exception e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
