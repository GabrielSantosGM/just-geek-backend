package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.dto.CompraDTO;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;

public interface MercadoPagoService {

    void setarTokenAcesso();

    Item criarItem(CompraDTO compraDTO);

    void setarInformacoesItemPedido(Item item, CompraDTO compraDTO);

    void setarInformacoesPreferences(Preference preference, CompraDTO compraDTO);

    void salvarPreference(Preference preference);

    BackUrls criarBackUrls(CompraDTO compraDTO);

    Preference criarPreferencia(CompraDTO compraDTO);
}
