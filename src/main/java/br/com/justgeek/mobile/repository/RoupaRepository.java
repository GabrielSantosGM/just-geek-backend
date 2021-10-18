package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Roupa;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RoupaRepository extends JpaRepository<Roupa, Integer> {

    Optional<Roupa> findByIdRoupa(int idRoupa);
}
