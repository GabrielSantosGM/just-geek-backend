package br.com.justgeek.mobile.controller.produto;

import br.com.justgeek.mobile.dto.CupomDeDescontoDTO;
import br.com.justgeek.mobile.entities.CupomDeDesconto;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.service.impl.produto.CupomDescontoServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coupons")
public class DiscountCouponController {

    private final CupomDescontoServiceImpl cupomDescontoService;

    @Autowired
    public DiscountCouponController(CupomDescontoServiceImpl cupomDescontoService) {
        this.cupomDescontoService = cupomDescontoService;
    }

    @PostMapping
    public ResponseEntity<String> registerCoupon(@RequestBody @Validated CupomDeDescontoDTO cupomDeDescontoDTO) {
        try {
            cupomDescontoService.cadastrarCupom(cupomDeDescontoDTO.transformEntity());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        }
    }

    @GetMapping("/{couponName}")
    public ResponseEntity<CupomDeDesconto> returnSpecifyCoupon(@PathVariable String couponName) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(cupomDescontoService.retornarCupom(couponName));
        } catch (CompraException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
