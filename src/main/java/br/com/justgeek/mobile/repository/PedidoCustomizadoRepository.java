package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.PedidoCustomizado;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PedidoCustomizadoRepository extends JpaRepository<PedidoCustomizado, Integer> {
}
