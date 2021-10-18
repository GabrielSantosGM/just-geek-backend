package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Usuario;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
public class UsuarioDTO {

    private String nome;
    private String sobrenome;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    private String cpf;
    private String celular;
    private String email;
    private String senha;

    public Usuario gerar() {
        Usuario usuario = new Usuario();
        usuario.setNome(this.nome);
        usuario.setSobrenome(this.sobrenome);
        usuario.setDataNascimento(this.dataNascimento);
        usuario.setCpf(this.cpf);
        usuario.setCelular(this.celular);
        usuario.setEmail(this.email);
        usuario.setSenha(this.senha);
        return usuario;
    }

    public String getNome() {
        return nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public String getCelular() {
        return celular;
    }

    public String getEmail() {
        return email;
    }

    public String getSenha() {
        return senha;
    }
}
