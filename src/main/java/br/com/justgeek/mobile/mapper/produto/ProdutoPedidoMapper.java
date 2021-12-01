package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.service.impl.produto.QuantityProductServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;

public class ProdutoPedidoMapper {

    private Integer idProduto;
    private String nomeProduto;
    private String cor;
    private Double preco;
    private Integer quantidade;
    private String imagem;

    @Autowired
    private ProdutoPedidoMapper(Integer idUsuario, Produto produto, QuantityProductServiceImpl quantityProductService) {
        this.idProduto = produto.getIdProduto();
        this.nomeProduto = produto.getFkRoupa().getModelo() + " " +
                produto.getTema() + " - " +
                produto.getPersonagem();
        this.cor = produto.getFkRoupa().getCor();
        this.preco = produto.getPreco();
//        this.quantidade = quantityProductService.retornaQuantidadeDoProdutoAposFinalizadaACompra(idUsuario, idProduto);
        this.imagem = produto.getImagens().get(0).getImagem();
    }

    public static ProdutoPedidoMapper gerar(Integer idUsuario, Produto produto, QuantityProductServiceImpl quantityProductService) {
        return new ProdutoPedidoMapper(idUsuario, produto, quantityProductService);
    }

    public Integer getIdProduto() {
        return idProduto;
    }

    public String getNomeProduto() {
        return nomeProduto;
    }

    public String getCor() {
        return cor;
    }

    public Double getPreco() {
        return preco;
    }

    public Integer getQuantidade() {
        return quantidade;
    }

    public String getImagem() {
        return imagem;
    }
}
