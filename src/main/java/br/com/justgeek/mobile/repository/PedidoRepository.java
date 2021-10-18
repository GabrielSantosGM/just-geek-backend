package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Pedido;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoRepository extends JpaRepository<Pedido, Integer> {
}
