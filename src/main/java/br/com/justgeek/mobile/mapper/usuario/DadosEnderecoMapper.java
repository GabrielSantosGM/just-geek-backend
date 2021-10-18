package br.com.justgeek.mobile.mapper.usuario;

import br.com.justgeek.mobile.entities.Endereco;

public class DadosEnderecoMapper {

    private Integer idEndereco;
    private String nomeDestinatario;
    private String endereco;
    private String bairroCidade;
    private String cep;

    private DadosEnderecoMapper(Endereco endereco) {
        this.idEndereco = endereco.getIdEndereco();
        this.nomeDestinatario = endereco.getFkUsuario().getNome() + " " + endereco.getFkUsuario().getSobrenome();
        this.endereco = endereco.getLogradouro() + ", " + endereco.getNumero();
        this.bairroCidade = endereco.getBairro() + ", " + endereco.getCidade();
        this.cep = "CEP " + endereco.getCep();
    }

    public static DadosEnderecoMapper gerar(Endereco endereco){
        return new DadosEnderecoMapper(endereco);
    }

    public Integer getIdEndereco() {
        return idEndereco;
    }

    public void setIdEndereco(Integer idEndereco) {
        this.idEndereco = idEndereco;
    }

    public String getNomeDestinatario() {
        return nomeDestinatario;
    }

    public void setNomeDestinatario(String nomeDestinatario) {
        this.nomeDestinatario = nomeDestinatario;
    }

    public String getEndereco() {
        return endereco;
    }

    public void setEndereco(String endereco) {
        this.endereco = endereco;
    }

    public String getBairroCidade() {
        return bairroCidade;
    }

    public void setBairroCidade(String bairroCidade) {
        this.bairroCidade = bairroCidade;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }
}
