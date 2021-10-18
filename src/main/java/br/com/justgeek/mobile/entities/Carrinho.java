package br.com.justgeek.mobile.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "carrinho")
public class Carrinho {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_carrinho")
    private Integer idCarrinho;

    @NotNull
    @Column(name = "valor_total")
    private Double valorTotal;

    @NotNull
    @Column(name = "finalizado")
    private Boolean finalizado;

    @NotNull
    @Column(name = "data_hora")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime dataHora;

    @ManyToOne
    @JoinColumn(name = "fk_pedido")
    private Pedido fkPedido;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "fk_usuario_carrinho")
    private Usuario fkUsuario;

    @JsonIgnore
    @OneToMany(mappedBy = "fkCarrinho", fetch = FetchType.EAGER)
    private List<ItemCompra> itemCompra;

    public Carrinho() {
        this.valorTotal = 0.0;
        this.dataHora = LocalDateTime.now();
        this.finalizado = false;
    }

    public Integer getIdCarrinho() {
        return idCarrinho;
    }

    public void setIdCarrinho(Integer idCarrinho) {
        this.idCarrinho = idCarrinho;
    }

    public Double getValorTotal() {
        return valorTotal;
    }

    public void setValorTotal(Double valorTotal) {
        this.valorTotal = valorTotal;
    }

    public Boolean getFinalizado() {
        return finalizado;
    }

    public void setFinalizado(Boolean finalizado) {
        this.finalizado = finalizado;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public Pedido getFkPedido() {
        return fkPedido;
    }

    public void setFkPedido(Pedido fkPedido) {
        this.fkPedido = fkPedido;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public List<ItemCompra> getItemCompra() {
        return itemCompra;
    }

    public void setItemCompra(List<ItemCompra> itemCompra) {
        this.itemCompra = itemCompra;
    }
}
