package br.com.justgeek.mobile.service.impl.pedido.compras;

import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.repository.PedidoRepository;
import br.com.justgeek.mobile.service.PedidoComprasService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoComprasServiceImpl implements PedidoComprasService {

    private final PedidoRepository pedidoRepository;

    @Autowired
    public PedidoComprasServiceImpl(PedidoRepository pedidoRepository) {
        this.pedidoRepository = pedidoRepository;
    }

    @Override
    public List<Pedido> returnAllOrders(int idUsuario) {
        List<Pedido> pedidos = pedidoRepository.findAllByFkUsuarioIdUsuario(idUsuario);
        if (pedidos.isEmpty()) {
            throw new NullPointerException("LISTA DE PEDIDOS VAZIA!");
        }
        return pedidos;
    }
}
