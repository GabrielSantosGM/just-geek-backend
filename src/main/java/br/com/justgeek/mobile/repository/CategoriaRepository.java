package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoriaRepository extends JpaRepository<Roupa, Integer> {

    List<Roupa> findByModelo(String modelo);
}
