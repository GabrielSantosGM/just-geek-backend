package br.com.justgeek.mobile.mapper.produto;

import br.com.justgeek.mobile.entities.AvaliacaoProduto;

public class AvaliacaoProdutoMapper {

    private String nomeAvaliador;
    private Double nota;
    private String comentario;

    public AvaliacaoProdutoMapper(AvaliacaoProduto avaliacaoProduto) {
        this.nomeAvaliador = avaliacaoProduto.getFkUsuario().getNome() + " " + avaliacaoProduto.getFkUsuario().getSobrenome();
        this.nota = avaliacaoProduto.getNota();
        this.comentario = avaliacaoProduto.getComentario();
    }

    public static AvaliacaoProdutoMapper gerar(AvaliacaoProduto avaliacaoProduto){
        return new AvaliacaoProdutoMapper(avaliacaoProduto);
    }

    public String getNomeAvaliador() {
        return nomeAvaliador;
    }

    public Double getNota() {
        return nota;
    }

    public String getComentario() {
        return comentario;
    }
}
