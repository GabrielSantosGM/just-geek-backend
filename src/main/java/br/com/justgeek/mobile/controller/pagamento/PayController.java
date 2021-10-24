package br.com.justgeek.mobile.controller.pagamento;

import br.com.justgeek.mobile.dto.CompraDTO;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.service.impl.mercado.pago.Lerao;
import br.com.justgeek.mobile.service.impl.mercado.pago.MercadoPagoServiceImpl;
import com.mercadopago.resources.Preference;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/payment")
public class PayController {

    private static final Logger LOG = LoggerFactory.getLogger(PayController.class);

//    private final MercadoPagoServiceImpl mercadoPagoService;

    private final Lerao lero;

    @Autowired
    public PayController(MercadoPagoServiceImpl mercadoPagoService, Lerao lero) {
//        this.mercadoPagoService = mercadoPagoService;
        this.lero = lero;
    }

    @PostMapping
    public ResponseEntity<String> pay(@RequestBody CompraDTO compraDTO) {
        try {
            lero.setarTokenDeAcesso();
            Preference preference = lero.criarPreferencia(compraDTO);
            return ResponseEntity.status(201).body(preference.getInitPoint());
        } catch (CompraException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }
}
