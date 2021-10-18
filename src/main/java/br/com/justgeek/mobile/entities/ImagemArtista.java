package br.com.justgeek.mobile.entities;

import javax.persistence.*;

@Entity
@Table(name = "imagem_artista")
public class ImagemArtista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_imagem")
    private Integer idImagem;

    @Column(name = "imagem", length = 999_999_999)
    private byte[] imagem;

    @OneToOne
    @JoinColumn(name = "fk_imagem_artista")
    private Artista fkArtista;

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Artista getFkArtista() {
        return fkArtista;
    }

    public void setFkArtista(Artista fkArtista) {
        this.fkArtista = fkArtista;
    }
}
