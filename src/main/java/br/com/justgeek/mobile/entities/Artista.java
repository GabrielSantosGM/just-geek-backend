package br.com.justgeek.mobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;
import java.util.List;

@Entity
@Table(name = "artista")
public class Artista {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_artista")
    private Integer idArtista;

    @NotBlank
    @Size(min = 2, max = 75)
    @Column(name = "nome_completo")
    private String nomeCompleto;

    @NotBlank
    @Size(min = 2)
    @Column(name = "apelido")
    private String apelido;

    @Positive
    @Column(name = "idade")
    private Integer idade;

    @NotNull
    @Column(name = "biografia")
    private String biografia;

    @NotNull
    @Column(name = "status")
    private Boolean status;

    @NotBlank
    @Column(name = "contato")
    private String contato;

    @Column(name = "rede_social1")
    private String redeSocial1;

    @Column(name = "rede_social2")
    private String redeSocial2;

    @NotBlank
    @Column(name = "categoria")
    private String categoria;

    @OneToMany(mappedBy = "fkArtista")
    @JsonIgnore
    private List<ProgramasHabilidadesArtista> programasHabilidadesArtistas;

    @JsonIgnore
    @OneToMany(mappedBy = "fkArtista")
    private List<UploadArtista> uploads;

    @JsonIgnore
    @OneToOne(mappedBy = "fkArtista")
    private ImagemArtista imagem;

    public Artista() {
        this.status = true;
    }

    public Artista(String nomeCompleto,
                   String apelido,
                   Integer idade,
                   String biografia,
                   String contato,
                   String redeSocial1,
                   String redeSocial2,
                   String categoria) {
        this.nomeCompleto = nomeCompleto;
        this.apelido = apelido;
        this.idade = idade;
        this.biografia = biografia;
        this.contato = contato;
        this.redeSocial1 = redeSocial1;
        this.redeSocial2 = redeSocial2;
        this.categoria = categoria;
        this.status = true;
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public void setIdArtista(Integer idArtista) {
        this.idArtista = idArtista;
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public void setApelido(String apelido) {
        this.apelido = apelido;
    }

    public Integer getIdade() {
        return idade;
    }

    public void setIdade(Integer idade) {
        this.idade = idade;
    }

    public String getBiografia() {
        return biografia;
    }

    public void setBiografia(String biografia) {
        this.biografia = biografia;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getContato() {
        return contato;
    }

    public void setContato(String contato) {
        this.contato = contato;
    }

    public String getRedeSocial1() {
        return redeSocial1;
    }

    public void setRedeSocial1(String redeSocial1) {
        this.redeSocial1 = redeSocial1;
    }

    public String getRedeSocial2() {
        return redeSocial2;
    }

    public void setRedeSocial2(String redeSocial2) {
        this.redeSocial2 = redeSocial2;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public List<ProgramasHabilidadesArtista> getProgramasHabilidadesArtistas() {
        return programasHabilidadesArtistas;
    }

    public void setProgramasHabilidadesArtistas(List<ProgramasHabilidadesArtista> programasHabilidadesArtistas) {
        this.programasHabilidadesArtistas = programasHabilidadesArtistas;
    }

    public List<UploadArtista> getUploads() {
        return uploads;
    }

    public void setUploads(List<UploadArtista> uploads) {
        this.uploads = uploads;
    }

    public ImagemArtista getImagem() {
        return imagem;
    }

    public void setImagem(ImagemArtista imagem) {
        this.imagem = imagem;
    }
}
