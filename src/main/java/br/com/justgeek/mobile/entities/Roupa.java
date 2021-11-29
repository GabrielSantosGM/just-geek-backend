package br.com.justgeek.mobile.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.List;

@Entity
@Table(name = "roupa")
public class Roupa {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_roupa")
    private Integer idRoupa;

    @NotBlank
    @Column(name = "modelo")
    private String modelo;

    @NotBlank
    @Column(name = "cor")
    private String cor;

    @NotBlank
    @Column(name = "material")
    private String material;

    @NotBlank
    @Column(name = "especificacoes")
    private String especificacoes;

    public Integer getIdRoupa() {
        return idRoupa;
    }

    public void setIdRoupa(Integer idRoupa) {
        this.idRoupa = idRoupa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getMaterial() {
        return material;
    }

    public void setMaterial(String material) {
        this.material = material;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }
}
