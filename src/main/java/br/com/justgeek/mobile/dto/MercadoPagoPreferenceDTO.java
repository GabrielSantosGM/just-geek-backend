package br.com.justgeek.mobile.dto;

import com.mercadopago.resources.Preference;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class MercadoPagoPreferenceDTO {

    private String id;
    private String url;

    public MercadoPagoPreferenceDTO(Preference preference) {
        this.id = preference.getId();
        this.url = preference.getInitPoint();
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
