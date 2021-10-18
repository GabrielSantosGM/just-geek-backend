package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.mapper.produto.ProdutoFavoritoMapper;
import br.com.justgeek.mobile.entities.ProdutoFavorito;

import java.util.List;

public interface ProdutoFavoritoService {

    ProdutoFavorito favoritarProduto(int idUsuario, int idProduto);

    ProdutoFavorito desfavoritarProduto(int idUsuario, int idFavoritado);

    List<ProdutoFavoritoMapper> retornarProdutosFavoritosDoUsuario(int idUsuario);
}
