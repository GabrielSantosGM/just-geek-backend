package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "item_compra")
public class ItemCompra {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_item")
    private Integer idItem;

    @ManyToOne
    @JoinColumn(name="fk_produto")
    private Produto fkProduto;

    @ManyToOne
    @JoinColumn(name="fk_carrinho")
    private Carrinho fkCarrinho;

    @NotNull
    @Column(name = "tamanho")
    private String tamanho;

    @NotNull
    @Column(name = "quantidade")
    private Integer quantidade;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public ItemCompra() {
        this.quantidade = 0;
        this.status = false;
        this.dataHora = LocalDateTime.now();
    }

    public Integer getIdItem() {
        return idItem;
    }

    public void setIdItem(Integer idItem) {
        this.idItem = idItem;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Carrinho getFkCarrinho() {
        return fkCarrinho;
    }

    public void setFkCarrinho(Carrinho fkCarrinho) {
        this.fkCarrinho = fkCarrinho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(Integer quantidade) {
        this.quantidade = quantidade;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
