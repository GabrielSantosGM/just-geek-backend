package br.com.justgeek.mobile.entities;

import javax.persistence.*;

@Entity
@Table(name = "imagem_produto")
public class ImagemProduto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_imagem")
    private Integer idImagem;

    @Column(name = "imagem", length = 999_999_999)
    private byte[] imagem;

    @ManyToOne
    @JoinColumn(name = "fk_produto_imagem")
    private Produto fkProduto;

    public Integer getIdImagem() {
        return idImagem;
    }

    public void setIdImagem(Integer idImagem) {
        this.idImagem = idImagem;
    }

    public byte[] getImagem() {
        return imagem;
    }

    public void setImagem(byte[] imagem) {
        this.imagem = imagem;
    }

    public Produto getFkProduto() {
        return fkProduto;
    }

    public void setFkProduto(Produto fkProduto) {
        this.fkProduto = fkProduto;
    }
}
