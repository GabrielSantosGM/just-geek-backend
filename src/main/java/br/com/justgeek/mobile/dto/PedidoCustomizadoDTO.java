package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.PedidoCustomizado;
import br.com.justgeek.mobile.entities.Usuario;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class PedidoCustomizadoDTO {

    private String tamanho;
    private String cor;

    private PedidoCustomizadoDTO(PedidoCustomizado pedidoCustomizado) {
        this.tamanho = pedidoCustomizado.getTamanho();
        this.cor = pedidoCustomizado.getCor();
    }

    public static PedidoCustomizadoDTO getDto(PedidoCustomizado pedidoCustomizado) {
        return new PedidoCustomizadoDTO(pedidoCustomizado);
    }

    public PedidoCustomizado dtoToEntity(Usuario usuario, Artista artista) {
        PedidoCustomizado pedidoCustomizado = new PedidoCustomizado();
        pedidoCustomizado.setFkUsuario(usuario);
        pedidoCustomizado.setFkArtista(artista);
        pedidoCustomizado.setTamanho(this.tamanho);
        pedidoCustomizado.setCor(this.cor);
        return pedidoCustomizado;
    }

    public String getTamanho() {
        return tamanho;
    }

    public void setTamanho(String tamanho) {
        this.tamanho = tamanho;
    }

    public String getCor() {
        return cor;
    }

    public void setCor(String cor) {
        this.cor = cor;
    }
}
