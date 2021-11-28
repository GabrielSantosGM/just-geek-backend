package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.Pedido;

import java.util.List;

public interface PedidoComprasService {

    List<Pedido> returnAllOrders(int idUsuario);
}
