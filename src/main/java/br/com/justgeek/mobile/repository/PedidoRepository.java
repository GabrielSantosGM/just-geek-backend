package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
    List<Pedido> findAllByFkUsuarioIdUsuario(int idUsuario);

    List<Pedido> findByIdPedidoAndFkUsuarioIdUsuario(int idPedido, int idUsuario);
}
