package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.Produto;

public class ProdutoMapper {

    private Integer idProduto;
    private String nomeProduto;
    private Double preco;
    private String especificacoes;
    private String descricao;
    private String imagens;

    private ProdutoMapper(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getFkRoupa().getModelo() + " " +
                produto.getTema() + " - " +
                produto.getPersonagem();
        this.preco = produto.getPreco();
        this.especificacoes = produto.getFkRoupa().getEspecificacoes();
        this.descricao = produto.getDescricao();
        this.imagens = "/products-images/" + idProduto;
    }

    public static ProdutoMapper gerar(Produto produto) {
        return new ProdutoMapper(produto);
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public Double getPreco() {
        return preco;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public String getDescricao() {
        return descricao;
    }

    public String getImagens() {
        return imagens;
    }
}
