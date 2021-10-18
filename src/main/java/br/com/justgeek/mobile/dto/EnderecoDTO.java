package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Endereco;
import br.com.justgeek.mobile.entities.Usuario;

public class EnderecoDTO {

    private String cep;
    private String logradouro;
    private String numero;
    private String complemento;
    private String referencia;
    private String bairro;
    private String cidade;
    private String uf;
    private Usuario fkUsuario;

    private EnderecoDTO() {
    }

    private EnderecoDTO(Endereco endereco, Usuario usuario) {
        this.cep = endereco.getCep();
        this.logradouro = endereco.getLogradouro();
        this.numero = endereco.getNumero();
        this.complemento = endereco.getComplemento();
        this.referencia = endereco.getReferencia();
        this.bairro = endereco.getBairro();
        this.cidade = endereco.getCidade();
        this.uf = endereco.getEstado();
        this.fkUsuario = usuario;
    }

    public static EnderecoDTO criar(Endereco endereco, Usuario usuario) {
        return new EnderecoDTO(endereco, usuario);
    }

    public Endereco gerar(){
        Endereco endereco = new Endereco();
        endereco.setCep(this.cep);
        endereco.setLogradouro(this.logradouro);
        endereco.setNumero(this.numero);
        endereco.setComplemento(this.complemento);
        endereco.setReferencia(this.referencia);
        endereco.setBairro(this.bairro);
        endereco.setCidade(this.cidade);
        endereco.setEstado(this.uf);
        endereco.setFkUsuario(this.fkUsuario);
        return endereco;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public String getLogradouro() {
        return logradouro;
    }

    public void setLogradouro(String logradouro) {
        this.logradouro = logradouro;
    }

    public String getNumero() {
        return numero;
    }

    public void setNumero(String numero) {
        this.numero = numero;
    }

    public String getComplemento() {
        return complemento;
    }

    public void setComplemento(String complemento) {
        this.complemento = complemento;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public String getBairro() {
        return bairro;
    }

    public void setBairro(String bairro) {
        this.bairro = bairro;
    }

    public String getCidade() {
        return cidade;
    }

    public void setCidade(String cidade) {
        this.cidade = cidade;
    }

    public String getUf() {
        return uf;
    }

    public void setUf(String uf) {
        this.uf = uf;
    }

    public void setFkUsuario(Usuario fkUsuario) {
        this.fkUsuario = fkUsuario;
    }
}
