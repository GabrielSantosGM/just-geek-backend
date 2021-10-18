package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "avaliacao_produto")
public class AvaliacaoProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_avaliacao")
    private Integer idAvaliacao;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_avaliando")
    private Usuario fkUsuario;

    @ManyToOne
    @JoinColumn(name = "fk_produto_avaliado")
    private Produto fkProduto;

    @NotNull
    @Column(name = "nota")
    private Double nota;

    @NotBlank
    @Column(name = "comentario")
    private String comentario;

    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public AvaliacaoProduto() {
        this.dataHora = LocalDateTime.now();
    }

    public AvaliacaoProduto(Usuario fkUsuario,
                            Produto fkProduto,
                            Double nota,
                            String comentario) {
        this.fkUsuario = fkUsuario;
        this.fkProduto = fkProduto;
        this.nota = nota;
        this.comentario = comentario;
        this.dataHora = LocalDateTime.now();
    }

    public Integer getIdAvaliacao() {
        return idAvaliacao;
    }

    public void setIdAvaliacao(Integer idAvaliacao) {
        this.idAvaliacao = idAvaliacao;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }

    public Double getNota() {
        return nota;
    }

    public void setNota(Double nota) {
        this.nota = nota;
    }

    public String getComentario() {
        return comentario;
    }

    public void setComentario(String comentario) {
        this.comentario = comentario;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
