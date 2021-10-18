package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "produto_favorito")
public class ProdutoFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_favorito")
    private Integer idFavorito;

    @ManyToOne
    @JoinColumn(name = "fk_produto_favorito")
    private Produto fkProduto;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_favorito")
    private Usuario fkUsuario;

    @NotNull
    private Boolean favorito;

    @NotNull
    private LocalDateTime dataHora;

    public ProdutoFavorito() {
        this.dataHora = LocalDateTime.now();
    }

    public Integer getIdFavorito() {
        return idFavorito;
    }

    public void setIdFavorito(Integer idFavorito) {
        this.idFavorito = idFavorito;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
