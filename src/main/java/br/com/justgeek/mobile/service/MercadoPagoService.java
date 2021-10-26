package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.service.impl.mercado.pago.MercadoPagoServiceImpl;

public interface MercadoPagoService {

    MercadoPagoServiceImpl finalizarCompra(int idUsuario);

    MercadoPagoPreferenceDTO retornarPreferenceMP();
}
