package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.entities.Roupa;

public class ProdutoDTO {

    private Boolean customizado;
    private String categoria;
    private String descricao;
    private String tema;
    private String personagem;
    private Double preco;
    private Roupa fkRoupa;

    private ProdutoDTO() {
    }

    private ProdutoDTO(Produto produto, Roupa roupa) {
        this.customizado = produto.getCustomizado();
        this.categoria = produto.getCategoria();
        this.descricao = produto.getDescricao();
        this.tema = produto.getTema();
        this.personagem = produto.getPersonagem();
        this.preco = produto.getPreco();
        this.fkRoupa = roupa;
    }

    public static ProdutoDTO criar(Produto produto, Roupa roupa) {
        return new ProdutoDTO(produto, roupa);
    }

    public Produto gerar(){
        Produto produto = new Produto();
        produto.setCustomizado(this.customizado);
        produto.setCategoria(this.categoria);
        produto.setDescricao(this.descricao);
        produto.setTema(this.tema);
        produto.setPersonagem(this.personagem);
        produto.setPreco(this.preco);
        produto.setFkRoupa(this.fkRoupa);
        return produto;
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

    public void setFkRoupa(Roupa fkRoupa) {
        this.fkRoupa = fkRoupa;
    }
}
