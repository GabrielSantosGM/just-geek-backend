package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.entities.PedidoCustomizado;

public interface PedidoCustomizadoService {

    PedidoCustomizado cadastrar(int idUsuario, int idArtista, PedidoCustomizadoDTO pedidoCustomizado);
}
