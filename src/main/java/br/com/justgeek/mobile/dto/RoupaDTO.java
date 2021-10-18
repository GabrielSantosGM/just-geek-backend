package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Roupa;

public class RoupaDTO {

    private String modelo;
    private String material;
    private String especificacoes;

    private RoupaDTO() {
    }

    private RoupaDTO(Roupa roupa) {
        this.modelo = roupa.getModelo();
        this.material = roupa.getMaterial();
        this.especificacoes = roupa.getEspecificacoes();
    }

    public static RoupaDTO create(Roupa roupa){
        return new RoupaDTO(roupa);
    }

    public Roupa gerar(){
        Roupa roupa = new Roupa();
        roupa.setModelo(this.modelo);
        roupa.setMaterial(this.material);
        roupa.setEspecificacoes(this.especificacoes);
        return roupa;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
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
