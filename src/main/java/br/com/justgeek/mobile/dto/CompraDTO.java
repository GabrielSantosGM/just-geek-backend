package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Carrinho;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class CompraDTO {

    private String nomeComprador;
    private String protocoloPedido;
    private Double valorTotal;

    public CompraDTO(Carrinho carrinho) {
        this.nomeComprador = carrinho.getFkUsuario().getNome() + " " + carrinho.getFkUsuario().getSobrenome();
        this.protocoloPedido = "CMP-JG#" + ThreadLocalRandom.current().nextInt(1,1000);
        this.valorTotal = carrinho.getValorTotal();
    }

    public String getNomeComprador() {
        return nomeComprador;
    }

    public void setNomeComprador(String nomeComprador) {
        this.nomeComprador = nomeComprador;
    }

    public String getProtocoloPedido() {
        return protocoloPedido;
    }

    public void setProtocoloPedido(String protocoloPedido) {
        this.protocoloPedido = protocoloPedido;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }
}
