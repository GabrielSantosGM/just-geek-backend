package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.ImagemProduto;
import br.com.justgeek.mobile.entities.ProdutoFavorito;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoFavoritoMapper {

    private final Integer idProduto;
    private final String nomeProduto;
    private final Double preco;
    private final List<String> imagens;

    private ProdutoFavoritoMapper(ProdutoFavorito produto) {
        this.idProduto = produto.getFkProduto().getIdProduto();
        this.nomeProduto = produto.getFkProduto().getFkRoupa().getModelo() + " " +
                produto.getFkProduto().getTema() + " - " +
                produto.getFkProduto().getPersonagem();
        this.preco = produto.getFkProduto().getPreco();
        this.imagens = produto.getFkProduto().getImagens().stream().map(ImagemProduto::getImagem).collect(Collectors.toList());
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

    public List<String> getImagens() {
        return imagens;
    }
}
