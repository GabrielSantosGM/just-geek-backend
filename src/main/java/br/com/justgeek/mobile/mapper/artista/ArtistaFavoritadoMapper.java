package br.com.justgeek.mobile.mapper.artista;

import br.com.justgeek.mobile.entities.ArtistaFavorito;

public class ArtistaFavoritadoMapper {

    private final Integer idArtista;
    private final String nome;
    private final String imagemPerfil;

    public ArtistaFavoritadoMapper(ArtistaFavorito artista) {
        this.idArtista = artista.getFkArtista().getIdArtista();
        this.nome = artista.getFkArtista().getNomeCompleto();
        this.imagemPerfil = "/artist-image/perfil/" + idArtista;
    }

    public static ArtistaFavoritadoMapper transformar(ArtistaFavorito artista) {
        return new ArtistaFavoritadoMapper(artista);
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public String getNome() {
        return nome;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }
}
