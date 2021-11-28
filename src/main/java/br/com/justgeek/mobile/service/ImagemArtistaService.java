package br.com.justgeek.mobile.service;

import java.util.List;

public interface ImagemArtistaService {

    List<String> retornarImagensArtista(int idArtista);

    List<String> uploadImagemArtista(int idArtista, String imagem1, String imagem2, String imagem3);

    String retornarImagemPerfil(int idArtista);
}
