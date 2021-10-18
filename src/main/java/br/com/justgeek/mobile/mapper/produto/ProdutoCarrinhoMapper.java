package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.service.impl.produto.QuantityProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoCarrinhoMapper {

    private Integer idProduto;
    private String nomeProduto;
    private Double preco;
    private String imagens;
    private Integer quantidade;

    @Autowired
    private ProdutoCarrinhoMapper(Produto produto, QuantityProductServiceImpl quantityProductService) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getFkRoupa().getModelo() + " " +
                produto.getTema() + " - " +
                produto.getPersonagem();
        this.preco = produto.getPreco();
        this.imagens = "/products-images/" + idProduto;
        this.quantidade = quantityProductService.retornaQuantidadeDoProduto(idProduto);
    }

    public static ProdutoCarrinhoMapper gerar(Produto produto, QuantityProductServiceImpl quantityProductService) {
        return new ProdutoCarrinhoMapper(produto, quantityProductService);
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

    public Integer getQuantidade() {
        return quantidade;
    }
}
