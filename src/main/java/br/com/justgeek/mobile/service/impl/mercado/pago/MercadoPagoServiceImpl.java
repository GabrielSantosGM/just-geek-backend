package br.com.justgeek.mobile.service.impl.mercado.pago;

import br.com.justgeek.mobile.dto.CompraDTO;
import br.com.justgeek.mobile.service.MercadoPagoService;
import com.mercadopago.MercadoPago;
import com.mercadopago.exceptions.MPConfException;
import com.mercadopago.resources.Preference;
import com.mercadopago.resources.datastructures.preference.BackUrls;
import com.mercadopago.resources.datastructures.preference.Item;
import org.springframework.stereotype.Service;

@Service
public class MercadoPagoServiceImpl implements MercadoPagoService {

    private static final String ACCESS_TOKEN = "TEST-8099014547724788-090223-0570f323f435ddcb629ed6f612aa3952-434716941";

    @Override
    public void setarTokenAcesso() {
        try {
            MercadoPago.SDK.setAccessToken(ACCESS_TOKEN);
        } catch (MPConfException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Item criarItem(CompraDTO compraDTO) {
        Item item = new Item();
        setarInformacoesItemPedido(item, compraDTO);
        return item;
    }

    @Override
    public void setarInformacoesItemPedido(Item item, CompraDTO compraDTO) {
        item.setTitle(compraDTO.getNomeProduto())
                .setQuantity(1)
                .setUnitPrice(compraDTO.getValorCompra().floatValue());
    }

    @Override
    public void setarInformacoesPreferences(Preference preference, CompraDTO compraDTO) {
        preference.appendItem(criarItem(compraDTO));
        salvarPreference(preference);
    }

    @Override
    public void salvarPreference(Preference preference) {
        try {
            preference.save();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public BackUrls criarBackUrls(CompraDTO compraDTO) {
        BackUrls backUrls = new BackUrls();
        backUrls.setSuccess("localhost:8080/success") //TODO: endpoint para sucesso, falha, e pendencia
                .setFailure("localhost:8080/success")
                .setPending("localhost:8080/success");
        return backUrls;
    }

    @Override
    public Preference criarPreferencia(CompraDTO compraDTO) {
        Preference preference = new Preference();
        preference.setAutoReturn(Preference.AutoReturn.approved);
        preference.setBackUrls(criarBackUrls(compraDTO));
        setarInformacoesPreferences(preference, compraDTO);
        return preference;
    }
}
