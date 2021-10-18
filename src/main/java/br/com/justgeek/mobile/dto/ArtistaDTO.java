package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Artista;

public class ArtistaDTO {

    private String nomeCompleto;
    private String apelido;
    private Integer idade;
    private String biografia;
    private String contato;
    private String redeSocial1;
    private String redeSocial2;
    private String categoria;

    public Artista transformarParaEntidade(){
        return new Artista(nomeCompleto, apelido, idade, biografia, contato, redeSocial1, redeSocial2, categoria);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public String getApelido() {
        return apelido;
    }

    public Integer getIdade() {
        return idade;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getContato() {
        return contato;
    }

    public String getRedeSocial1() {
        return redeSocial1;
    }

    public String getRedeSocial2() {
        return redeSocial2;
    }

    public String getCategoria() {
        return categoria;
    }
}
