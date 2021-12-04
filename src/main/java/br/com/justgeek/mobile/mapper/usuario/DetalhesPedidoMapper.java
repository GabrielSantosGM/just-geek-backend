package br.com.justgeek.mobile.mapper.usuario;

import br.com.justgeek.mobile.entities.Carrinho;

import java.time.LocalDateTime;

public class DetalhesPedidoMapper {

    private Integer idPedido;
    private String protocoloPedido;
    private Double valorProdutos;
    private Double valorFrete;
    private Double valorCupom;
    private String statusPedido;
    private LocalDateTime dataStatus;

    public DetalhesPedidoMapper(Carrinho carrinho) {
        this.idPedido = carrinho.getFkPedido().getIdPedido();
        this.protocoloPedido = carrinho.getFkPedido().getCodCompra();
        this.valorProdutos = carrinho.getFkPedido().getValorProdutos();
        this.valorFrete = carrinho.getFkPedido().getValorFrete();
        this.valorCupom = carrinho.getFkPedido().getValorCupom();
        this.statusPedido = carrinho.getFkPedido().getSituacao();
        this.dataStatus = carrinho.getFkPedido().getDataHora();
    }

    public Integer getIdPedido() {
        return idPedido;
    }

    public String getProtocoloPedido() {
        return protocoloPedido;
    }

    public Double getValorProdutos() {
        return valorProdutos;
    }

    public Double getValorFrete() {
        return valorFrete;
    }

    public Double getValorCupom() {
        return valorCupom;
    }

    public String getStatusPedido() {
        return statusPedido;
    }

    public LocalDateTime getDataStatus() {
        return dataStatus;
    }
}
