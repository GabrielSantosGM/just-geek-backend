package br.com.justgeek.mobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import java.util.List;

@Entity
@Table(name = "produto")
public class Produto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_produto")
    private Integer idProduto;

    @NotNull
    @Column(name = "customizado")
    private Boolean customizado;

    @NotBlank
    @Column(name = "categoria")
    private String categoria;

    @NotBlank
    @Column(name = "descricao")
    private String descricao;

    @NotBlank
    @Column(name = "tema")
    private String tema;

    @NotBlank
    @Column(name = "personagem")
    private String personagem;

    @NotNull
    @PositiveOrZero
    @Column(name = "preco")
    private Double preco;

    @NotNull
    @PositiveOrZero
    @Column(name = "acessos")
    private Integer acessos;

    @OneToOne
    @JoinColumn(name = "fk_roupa")
    private Roupa fkRoupa;

    @OneToMany(mappedBy = "fkProduto")
    private List<ImagemProduto> imagens;

    @JsonIgnore
    @OneToMany(mappedBy = "fkProduto")
    private List<ItemCompra> itensCompras;

    public Produto() {
        this.acessos = 0;
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public void setIdProduto(Integer idProduto) {
        this.idProduto = idProduto;
    }

    public Boolean getCustomizado() {
        return customizado;
    }

    public void setCustomizado(Boolean customizado) {
        this.customizado = customizado;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public String getTema() {
        return tema;
    }

    public void setTema(String tema) {
        this.tema = tema;
    }

    public String getPersonagem() {
        return personagem;
    }

    public void setPersonagem(String personagem) {
        this.personagem = personagem;
    }

    public Double getPreco() {
        return preco;
    }

    public void setPreco(Double preco) {
        this.preco = preco;
    }

    public Integer getAcessos() {
        return acessos;
    }

    public void setAcessos(Integer acessos) {
        this.acessos = acessos;
    }

    public Roupa getFkRoupa() {
        return fkRoupa;
    }

    public void setFkRoupa(Roupa fkRoupa) {
        this.fkRoupa = fkRoupa;
    }

    public List<ImagemProduto> getImagens() {
        return imagens;
    }

    public void setImagens(List<ImagemProduto> imagens) {
        this.imagens = imagens;
    }

    public List<ItemCompra> getItensCompras() {
        return itensCompras;
    }

    public void setItensCompras(List<ItemCompra> itensCompras) {
        this.itensCompras = itensCompras;
    }
}
