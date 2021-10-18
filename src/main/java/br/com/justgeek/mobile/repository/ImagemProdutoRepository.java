package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.ImagemProduto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemProdutoRepository extends JpaRepository<ImagemProduto, Integer> {

    List<ImagemProduto> findByFkProdutoIdProduto(Integer idProduto);

    int countByFkProdutoIdProduto(Integer idProduto);
}
