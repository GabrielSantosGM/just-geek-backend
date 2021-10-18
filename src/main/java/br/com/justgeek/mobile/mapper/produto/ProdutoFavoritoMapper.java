package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.ProdutoFavorito;

public class ProdutoFavoritoMapper {

    private Integer idProduto;
    private String nomeProduto;
    private Double preco;
    private String imagens;

    private ProdutoFavoritoMapper(ProdutoFavorito produto) {
        this.idProduto = produto.getFkProduto().getIdProduto();
        this.nomeProduto = produto.getFkProduto().getFkRoupa().getModelo() + " " +
                produto.getFkProduto().getTema() + " - " +
                produto.getFkProduto().getPersonagem();
        this.preco = produto.getFkProduto().getPreco();
        this.imagens = "/products-images/" + idProduto;
    }

    public static ProdutoFavoritoMapper gerar(ProdutoFavorito produto) {
        return new ProdutoFavoritoMapper(produto);
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

    public String getImagens() {
        return imagens;
    }
}
