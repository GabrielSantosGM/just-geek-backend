package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Usuario;

import java.util.concurrent.ThreadLocalRandom;

public class CompraCustomizadaDTO {

    private String nomeComprador;
    private String protocoloPedido;
    private Double valorTotal;

    public CompraCustomizadaDTO(Usuario usuario) {
        this.nomeComprador = usuario.getNome() + " " + usuario.getSobrenome();
        this.protocoloPedido = "PED-JG#" + ThreadLocalRandom.current().nextInt(1,1000);;
        this.valorTotal = 84.99;
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public String getProtocoloPedido() {
        return protocoloPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }
}
