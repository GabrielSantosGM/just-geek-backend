package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.service.impl.mercado.pago.MercadoPagoServiceImpl;

import java.util.Optional;

public interface MercadoPagoService {

    MercadoPagoServiceImpl finalizarCompra(int idUsuario, Double valorFrete, Optional<String> cupom);

    MercadoPagoPreferenceDTO retornarPreferenceMP();
}
