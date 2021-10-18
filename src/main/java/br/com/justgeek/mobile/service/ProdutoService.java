package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.AvaliacaoProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.dto.ProdutoDTO;
import br.com.justgeek.mobile.mapper.produto.AvaliacaoProdutoMapper;

import java.util.List;

public interface ProdutoService {

    Produto cadastrarProduto(int idRoupa, ProdutoDTO product);

    AvaliacaoProduto fazerAvaliacaoDoProduto(int idUsuario, int idProduto, AvaliacaoProduto avaliacaoProduto);

    List<AvaliacaoProdutoMapper> retornarAvaliacoesDoProduto(int idProduto);

}
