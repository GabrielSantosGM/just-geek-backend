package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.AvaliacaoProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface AvaliacaoProdutoRepository extends JpaRepository<AvaliacaoProduto, Integer> {

    List<AvaliacaoProduto> findAllByFkProdutoIdProduto(int idProduto);
}
