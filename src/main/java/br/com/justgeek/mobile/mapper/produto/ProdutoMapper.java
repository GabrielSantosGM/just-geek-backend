package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.ImagemProduto;
import br.com.justgeek.mobile.entities.Produto;

import java.util.List;
import java.util.stream.Collectors;

public class ProdutoMapper {

    private final Integer idProduto;
    private final String nomeProduto;
    private final String categoria;
    private final Double preco;
    private final String especificacoes;
    private final String descricao;
    private final List<String> imagens;

    private ProdutoMapper(Produto produto) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getFkRoupa().getModelo() + " " +
                produto.getTema() + " - " +
                produto.getPersonagem();
        this.categoria = produto.getCategoria();
        this.preco = produto.getPreco();
        this.especificacoes = produto.getFkRoupa().getEspecificacoes();
        this.descricao = produto.getDescricao();
        this.imagens = produto.getImagens().stream().map(ImagemProduto::getImagem).collect(Collectors.toList());
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

    public String getCategoria() {
        return categoria;
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

    public List<String> getImagens() {
        return imagens;
    }
}
