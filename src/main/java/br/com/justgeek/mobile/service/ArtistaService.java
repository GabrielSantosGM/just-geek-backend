package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.mapper.artista.ArtistaFavoritadoMapper;
import br.com.justgeek.mobile.mapper.artista.ArtistaMapper;

import java.util.List;

public interface ArtistaService {

    Artista cadastrar(Artista artista);

    ArtistaMapper retornarArtistaEspecifico(int idArtista);

    List<Artista> retornarTodosOsArtistas();

    List<ArtistaMapper> retornarArtistasSemelhantes(int idArtista);

    List<ArtistaFavoritadoMapper> artistasFavoritados(int idUsuario);

    void favoritarArtista(int idUsuario, int idArtista);

    void desfavoritarArtista(int idUsuario, int idArtistaFavoritado);
}
