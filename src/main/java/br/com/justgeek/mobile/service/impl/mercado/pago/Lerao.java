package br.com.justgeek.mobile.service.impl.mercado.pago;

import br.com.justgeek.mobile.dto.CompraDTO;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.exceptions.MPException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;

@Service
public class Lerao {

    private final String ACESS_TOKEN = "TEST-8099014547724788-090223-0570f323f435ddcb629ed6f612aa3952-434716941";

    public void setarTokenDeAcesso() {
        try {
            MercadoPago.SDK.setAccessToken(ACESS_TOKEN);
        } catch (MPConfException e) {
            e.printStackTrace();
        }
    }

    public Item criarItem(CompraDTO pedido) {
        Item itemPedido = new Item();
        setarInformacoesItemPedido(itemPedido, pedido);
        return itemPedido;
    }

    public void setarInformacoesItemPedido(Item itemPedido, CompraDTO pedido) {
        itemPedido.setTitle(pedido.getProtocoloPedido())
                .setQuantity(1)
                .setUnitPrice(pedido.getValorCompra().floatValue());
    }

    public Preference criarPreferencia(CompraDTO pedido) {
        Preference preference = new Preference();
        preference.setAutoReturn(Preference.AutoReturn.approved);
        setarInformacoesPreferencia(preference, pedido);
        return preference;
    }

    public BackUrls criarBackUrls(CompraDTO pedido) {
        BackUrls backUrls = new BackUrls();
        backUrls.setSuccess("http://54.144.215.240/pedidos/sucesso/%22" + pedido.getProtocoloPedido());
        backUrls.setFailure("http://54.144.215.240/pedidos/falha/%22" + pedido.getProtocoloPedido());
        backUrls.setPending("http://54.144.215.240/pedidos/pendente/%22" + pedido.getProtocoloPedido());
        return backUrls;
    }

    public void setarInformacoesPreferencia(Preference preference, CompraDTO pedido) {
        preference.appendItem(criarItem(pedido));
        try {
            preference.save();
        } catch (MPException e) {
            e.printStackTrace();
        }
    }

    public void salvarPreferencia(Preference preference) {
        try {
            preference.save();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
}

