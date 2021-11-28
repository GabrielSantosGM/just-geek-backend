package br.com.justgeek.mobile.controller.pedido.compras;

import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.service.PedidoComprasService;
import br.com.justgeek.mobile.service.impl.pedido.compras.PedidoComprasServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final PedidoComprasService pedidoComprasService;

    @Autowired
    public OrderController(PedidoComprasServiceImpl pedidoComprasService) {
        this.pedidoComprasService = pedidoComprasService;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Pedido>> returnAllOrders(@PathVariable int idUser) {
        try {
            LOG.info("RETORNANDO TODOS OS PEDIDOS DO USUARIO DE ID {}", idUser);
            return ResponseEntity.status(HttpStatus.OK).body(pedidoComprasService.returnAllOrders(idUser));
        } catch (NullPointerException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        }
    }
}
