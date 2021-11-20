package br.com.justgeek.mobile.entities;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.List;

@Entity
@Table(name = "usuario")
public class Usuario {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_usuario")
    private Integer idUsuario;

    @NotNull
    @Size(min = 2, max= 30)
    @Column(name = "nome")
    private String nome;

    @NotNull
    @Size(min = 2, max= 30)
    @Column(name = "sobrenome")
    private String sobrenome;

    @NotNull
    @Column(name = "data_nascimento")
    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "dd-MM-yyyy")
    private LocalDate dataNascimento;

    @NotBlank
    @Column(name = "cpf")
    private String cpf;

    @NotBlank
    @Size(min = 10)
    @Column(name = "celular")
    private String celular;

    @Email
    @NotBlank
    @Column(length = 65, name="email")
    private String email;

    @NotBlank
    @Size(min = 6, max= 12)
    @Column(name = "senha")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String senha;

    @NotNull
    @Column(name="autenticado")
    private Boolean autenticado;

    @NotNull
    @Column(name = "primeiro_acesso")
    private Boolean primeiroAcesso;

    @NotNull
    @Column(name = "primeira_compra")
    private Boolean primeiraCompra;

    @JsonIgnore
    @OneToOne(mappedBy = "fkUsuario")
    private Endereco endereco;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUsuario", fetch = FetchType.EAGER)
    private List<Pedido> pedidos;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUsuario")
    private List<ProdutoFavorito> favoritos;

    @JsonIgnore
    @OneToMany(mappedBy = "fkUsuario")
    private List<ArtistaFavorito> artistasFavoritos;

    public Usuario() {
        this.primeiraCompra = true;
        this.primeiroAcesso = true;
        this.autenticado = false;
    }

    public Usuario(String nome, String sobrenome, LocalDate dataNascimento, String cpf, String celular, String email, String senha) {
        this.nome = nome;
        this.sobrenome = sobrenome;
        this.dataNascimento = dataNascimento;
        this.cpf = cpf;
        this.celular = celular;
        this.email = email;
        this.senha = senha;
        this.primeiraCompra = true;
        this.primeiroAcesso = true;
        this.autenticado = false;
    }

    public Integer getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(Integer idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getSobrenome() {
        return sobrenome;
    }

    public void setSobrenome(String sobrenome) {
        this.sobrenome = sobrenome;
    }

    public LocalDate getDataNascimento() {
        return dataNascimento;
    }

    public void setDataNascimento(LocalDate dataNascimento) {
        this.dataNascimento = dataNascimento;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        this.senha = senha;
    }

    public Boolean getAutenticado() {
        return autenticado;
    }

    public void setAutenticado(Boolean autenticado) {
        this.autenticado = autenticado;
    }

    public Boolean getPrimeiroAcesso() {
        return primeiroAcesso;
    }

    public void setPrimeiroAcesso(Boolean primeiroAcesso) {
        this.primeiroAcesso = primeiroAcesso;
    }

    public Boolean getPrimeiraCompra() {
        return primeiraCompra;
    }

    public void setPrimeiraCompra(Boolean primeiraCompra) {
        this.primeiraCompra = primeiraCompra;
    }

    public Endereco getEndereco() {
        return endereco;
    }

    public void setEndereco(Endereco endereco) {
        this.endereco = endereco;
    }

    public List<Pedido> getPedidos() {
        return pedidos;
    }

    public void setPedidos(List<Pedido> pedidos) {
        this.pedidos = pedidos;
    }

    public List<ProdutoFavorito> getFavoritos() {
        return favoritos;
    }

    public void setFavoritos(List<ProdutoFavorito> favoritos) {
        this.favoritos = favoritos;
    }

    public List<ArtistaFavorito> getArtistasFavoritos() {
        return artistasFavoritos;
    }

    public void setArtistasFavoritos(List<ArtistaFavorito> artistasFavoritos) {
        this.artistasFavoritos = artistasFavoritos;
    }
}
