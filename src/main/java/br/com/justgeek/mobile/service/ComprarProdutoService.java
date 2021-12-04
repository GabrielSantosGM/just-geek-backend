package br.com.justgeek.mobile.service;

public interface ComprarProdutoService {

    void moverProdutoParaCarrinho(int idUsuario, int idFavorito, String tamanho);

    void adicionarProdutoNoCarrinho(int idUsuario, int idProduto, int quantidade, String tamanho);
}
