package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.ContaException;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.NoArgsConstructor;
import org.apache.commons.validator.routines.EmailValidator;

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

    public Usuario dtoParaEntidade() {
        if (EmailValidator.getInstance().isValid(email)) {
            return new Usuario(nome, sobrenome, dataNascimento, cpf, celular, email, senha);
        } else {
            throw new ContaException("[CADASTRO] Email inserido inválido!");
        }
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
