package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "tamanho_roupa")
public class TamanhoRoupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_tamanho")
    private Integer idTamanho;

    @NotNull
    @Column(name = "tamanho")
    private String tamanho;

    @ManyToOne
    @JoinColumn(name = "fk_roupa_tamanho")
    private Roupa fkRoupa;

    public Integer getIdTamanho() {
        return idTamanho;
    }

    public void setIdTamanho(Integer idTamanho) {
        this.idTamanho = idTamanho;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public Roupa getFkRoupa() {
        return fkRoupa;
    }

    public void setFkRoupa(Roupa fkRoupa) {
        this.fkRoupa = fkRoupa;
    }
}
