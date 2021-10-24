package br.com.justgeek.mobile.dto;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class CompraDTO {

    private String nomeComprador;
    private String protocoloPedido;
    private String nomeProduto;
    private Double valorFrete;
    private Double valorCompra;
    private Integer quantidade;

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

    public String getNomeProduto() {
        return nomeProduto;
    }

    public void setNomeProduto(String nomeProduto) {
        this.nomeProduto = nomeProduto;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public void setValorFrete(Double valorFrete) {
        this.valorFrete = valorFrete;
    }

    public Double getValorCompra() {
        return valorCompra;
    }

    public void setValorCompra(Double valorCompra) {
        this.valorCompra = valorCompra;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }
}
