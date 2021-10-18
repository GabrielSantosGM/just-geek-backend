package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "artista_favorito")
public class ArtistaFavorito {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista_favorito")
    private Integer idArtistaFavorito;

    @ManyToOne
    @JoinColumn(name = "fk_artista_favorito")
    private Artista fkArtista;

    @ManyToOne
    @JoinColumn(name = "fk_usuario_artista")
    private Usuario fkUsuario;

    @NotNull
    @Column(name = "favorito")
    private Boolean favorito;

    @NotNull
    @Column(name = "data_hora")
    private LocalDateTime dataHora;

    public ArtistaFavorito() {
        this.dataHora = LocalDateTime.now();
    }

    public Integer getIdArtistaFavorito() {
        return idArtistaFavorito;
    }

    public void setIdArtistaFavorito(Integer idArtistaFavorito) {
        this.idArtistaFavorito = idArtistaFavorito;
    }

    public Artista getFkArtista() {
        return fkArtista;
    }

    public void setFkArtista(Artista fkArtista) {
        this.fkArtista = fkArtista;
    }

    public Usuario getFkUsuario() {
        return fkUsuario;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }

    public Boolean getFavorito() {
        return favorito;
    }

    public void setFavorito(Boolean favorito) {
        this.favorito = favorito;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }
}
