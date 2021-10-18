package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.mapper.produto.ProdutoCarrinhoMapper;

import java.util.List;

public interface CarrinhoService {

    List<ProdutoCarrinhoMapper> retornarProdutosNoCarrinho(int idUsuario);

    void deletarProduto(int idUsuario, int idProduto, int quantidade);
}
