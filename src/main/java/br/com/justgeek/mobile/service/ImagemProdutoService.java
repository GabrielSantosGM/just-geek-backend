package br.com.justgeek.mobile.service;

import java.util.List;

public interface ImagemProdutoService {

    List<String> retornarImagens(int idProduto);

    List<String> uploadImagemProduto(int idProduto,
                                     String imagem1,
                                     String imagem2,
                                     String imagem3,
                                     String imagem4);
}
