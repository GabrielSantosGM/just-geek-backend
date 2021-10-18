package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.AvaliacaoProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.entities.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class AvaliacaoProdutoDTO {

    private Usuario usuario;
    private Produto produto;
    private Double nota;
    private String comentario;

    public AvaliacaoProdutoDTO(Usuario usuario, Produto produto, AvaliacaoProduto avaliacaoProduto) {
        this.usuario = usuario;
        this.produto = produto;
        this.nota = avaliacaoProduto.getNota();
        this.comentario = avaliacaoProduto.getComentario();
    }

    public static AvaliacaoProdutoDTO gerar(Usuario usuario, Produto produto, AvaliacaoProduto avaliacaoProduto) {
        return new AvaliacaoProdutoDTO(usuario, produto, avaliacaoProduto);
    }

    public AvaliacaoProduto transformarParaEntidade() {
        return new AvaliacaoProduto(this.usuario, this.produto, this.nota, this.comentario);
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public Produto getProduto() {
        return produto;
    }

    public Double getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}
