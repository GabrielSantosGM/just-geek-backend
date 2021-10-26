package br.com.justgeek.mobile.controller.pagamento;

import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.service.impl.mercado.pago.MercadoPagoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/payment")
public class PayController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);

    @Autowired
    private MercadoPagoServiceImpl mercadoPagoService;

    @PostMapping("/{idUsuario}")
    public ResponseEntity<MercadoPagoPreferenceDTO> pay(@PathVariable int idUsuario) {
        try {
            LOG.info("FINALIZANDO COMPRA");
            return ResponseEntity.status(201).body(mercadoPagoService.finalizarCompra(idUsuario).retornarPreferenceMP());
        } catch (CompraException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
