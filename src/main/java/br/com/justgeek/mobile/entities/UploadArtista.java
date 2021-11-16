package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "upload_artista")
public class UploadArtista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_upload")
    private Integer idUpload;

    @NotNull
    @Column(name = "imagem")
    private String imagem;

    @ManyToOne
    @JoinColumn(name = "fk_artista")
    private Artista fkArtista;

    public Integer getIdUpload() {
        return idUpload;
    }

    public void setIdUpload(Integer idUpload) {
        this.idUpload = idUpload;
    }

    public String getImagem() {
        return imagem;
    }

    public void setImagem(String imagem) {
        this.imagem = imagem;
    }

    public Artista getFkArtista() {
        return fkArtista;
    }

    public void setFkArtista(Artista fkArtista) {
        this.fkArtista = fkArtista;
    }
}
