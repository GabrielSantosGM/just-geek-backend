package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Roupa;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class RoupaDTO {

    private String modelo;
    private String material;
    private String cor;
    private String especificacoes;

    private RoupaDTO(Roupa roupa) {
        this.modelo = roupa.getModelo();
        this.material = roupa.getMaterial();
        this.cor = roupa.getCor();
        this.especificacoes = roupa.getEspecificacoes();
    }

    public Roupa gerar(){
        Roupa roupa = new Roupa();
        roupa.setModelo(this.modelo);
        roupa.setMaterial(this.material);
        roupa.setCor(this.cor);
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

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }

    public String getEspecificacoes() {
        return especificacoes;
    }

    public void setEspecificacoes(String especificacoes) {
        this.especificacoes = especificacoes;
    }
}
