package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.entities.PedidoCustomizado;
import br.com.justgeek.mobile.service.impl.pedido.customizado.PedidoCustomizadoServiceImpl;

public interface PedidoCustomizadoService {

    PedidoCustomizadoServiceImpl cadastrar(int idUsuario, int idArtista, PedidoCustomizadoDTO pedidoCustomizado);
}
