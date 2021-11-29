package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.mapper.produto.ProdutoPedidoMapper;

import java.util.List;

public interface PedidoComprasService {

    List<Pedido> retornaTodosOsPedidos(int idUsuario);

    List<ProdutoPedidoMapper> retornarTodosOsProdutosDoPedido(int idUser, int idOrder);
}
