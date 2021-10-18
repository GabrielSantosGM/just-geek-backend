package br.com.justgeek.mobile.service;

public interface ComprarProdutoService {

    void moverProdutoParaCarrinho(int idUsuario, int idFavorito);

    void adicionarProdutoNoCarrinho(int idUsuario, int idProduto, int quantidade);

    Double calcularFrete(String cep);
}
