package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Carrinho;
import lombok.NoArgsConstructor;

import java.util.concurrent.ThreadLocalRandom;

@NoArgsConstructor
public class CompraDTO {

    private String nomeComprador;
    private String protocoloPedido;
    private Double valorTotal;
    private Double valorProdutos;
    private Double valorCupom;
    private Double valorFrete;

    public CompraDTO(Carrinho carrinho, Double valorProdutos, Double valorCupom, Double valorFrete) {
        this.nomeComprador = carrinho.getFkUsuario().getNome() + " " + carrinho.getFkUsuario().getSobrenome();
        this.protocoloPedido = "CMP-JG#" + ThreadLocalRandom.current().nextInt(1,1000);
        this.valorTotal = carrinho.getValorTotal();
        this.valorProdutos = valorProdutos;
        this.valorCupom = valorCupom;
        this.valorFrete = valorFrete;
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

    public Double getValorProdutos() {
        return valorProdutos;
    }

    public Double getValorCupom() {
        return valorCupom;
    }

    public Double getValorFrete() {
        return valorFrete;
    }
}
