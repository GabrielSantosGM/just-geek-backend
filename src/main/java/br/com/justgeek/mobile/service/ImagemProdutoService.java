package br.com.justgeek.mobile.service;

import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface ImagemProdutoService {

    List<String> retornarImagens(int idProduto);

    List<MultipartFile> uploadImagemProduto(int idProduto,
                                            MultipartFile imagem1,
                                            MultipartFile imagem2,
                                            MultipartFile imagem3);
}
