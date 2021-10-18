package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Artista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ArtistaRepository extends JpaRepository<Artista, Integer> {

    Optional<Artista> findByIdArtista(int idArtista);

    Optional<Artista> findByApelido(String apelido);

    List<Artista> findByCategoria(String categoria);

    List<Artista> findByCategoriaIgnoreCaseContains(String categoria);
}
