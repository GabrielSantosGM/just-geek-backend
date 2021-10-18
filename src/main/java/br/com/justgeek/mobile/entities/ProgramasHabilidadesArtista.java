package br.com.justgeek.mobile.entities;

import javax.persistence.*;

@Entity
@Table(name = "programas_habilidades_artista")
public class ProgramasHabilidadesArtista {

    @Id
    @Column(name = "id_programas_habilidades")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProgramasHabilidades;

    @Column(name = "habilidade")
    private String habilidade;

    @Column(name = "programa")
    private String programa;

    @ManyToOne
    @JoinColumn(name = "fkArtista")
    private Artista fkArtista;

    public Integer getIdProgramasHabilidades() {
        return idProgramasHabilidades;
    }

    public void setIdProgramasHabilidades(Integer idProgramasHabilidades) {
        this.idProgramasHabilidades = idProgramasHabilidades;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public void setHabilidade(String habilidade) {
        this.habilidade = habilidade;
    }

    public String getPrograma() {
        return programa;
    }

    public void setPrograma(String programa) {
        this.programa = programa;
    }

    public Artista getFkArtista() {
        return fkArtista;
    }

    public void setFkArtista(Artista fkArtista) {
        this.fkArtista = fkArtista;
    }
}
