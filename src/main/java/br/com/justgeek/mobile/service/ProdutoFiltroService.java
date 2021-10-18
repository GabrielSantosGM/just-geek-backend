package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.mapper.produto.ProdutoMapper;

import java.util.List;

public interface ProdutoFiltroService {

    ProdutoMapper retornarProdutoEspecifico(int idProduto);

    List<ProdutoMapper> retornarProdutosSemelhantes(int idProduto);

    List<ProdutoMapper> retornarTodosProdutos();

    List<ProdutoMapper> retornarProdutosCategoria(String categoria);

    List<ProdutoMapper> retornarProdutosTema(String tema);

    List<ProdutoMapper> retornarProdutosPorRoupa(String roupa);

    List<ProdutoMapper> retornarProdutosMaisPopulares();

    List<ProdutoMapper> retornarProdutosMenosPopulares();

    List<ProdutoMapper> retornarProdutosCustomizados();

    List<ProdutoMapper> retornarProdutosPrecoCrescente();

    List<ProdutoMapper> retornarProdutosPrecoDecrescente();
}
