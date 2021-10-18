package br.com.justgeek.mobile.mapper.usuario;

import br.com.justgeek.mobile.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;

import java.time.LocalDate;

public class PerfilMapper {

    private String nomeCompleto;

    private String cpf;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String email;

    private String celular;

    private PerfilMapper(Usuario usuario) {
        this.nomeCompleto = usuario.getNome() + " " + usuario.getSobrenome();
        this.cpf = usuario.getCpf();
        this.dataNascimento = usuario.getDataNascimento();
        this.email = usuario.getEmail();
        this.celular = usuario.getCelular();
    }

    public static PerfilMapper gerar(Usuario usuario){
        return new PerfilMapper(usuario);
    }

    public String getNomeCompleto() {
        return nomeCompleto;
    }

    public void setNomeCompleto(String nomeCompleto) {
        this.nomeCompleto = nomeCompleto;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }
}
