package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.ImagemProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.service.impl.produto.QuantidadeETamanhoProdutoImpl;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoCarrinhoMapper {

    private final Integer idUsuario;
    private final Integer idProduto;
    private final String nomeProduto;
    private final Double preco;
    private final String tamanho;
    private final List<String> imagens;
    private final Integer quantidade;

    @Autowired
    private ProdutoCarrinhoMapper(Integer idUsuario, Produto produto, QuantidadeETamanhoProdutoImpl quantityProductService) {
        this.idUsuario = idUsuario;
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getFkRoupa().getModelo() + " " +
                produto.getTema() + " - " +
                produto.getPersonagem();
        this.preco = produto.getPreco();
        this.tamanho = quantityProductService.retornaTamanhoProduto(idUsuario, idProduto);
        this.imagens = produto.getImagens().stream().map(ImagemProduto::getImagem).collect(Collectors.toList());
        this.quantidade = quantityProductService.retornaQuantidadeDoProduto(idUsuario, idProduto, tamanho);
    }

    public static ProdutoCarrinhoMapper gerar(Integer idUsuario, Produto produto, QuantidadeETamanhoProdutoImpl quantityProductService) {
        return new ProdutoCarrinhoMapper(idUsuario, produto, quantityProductService);
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

    public String getTamanho() {
        return tamanho;
    }

    public List<String> getImagens() {
        return imagens;
    }

    public Integer getQuantidade() {
        return quantidade;
    }
}
