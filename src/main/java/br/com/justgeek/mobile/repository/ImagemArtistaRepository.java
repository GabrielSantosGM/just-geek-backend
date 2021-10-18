package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.ImagemArtista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ImagemArtistaRepository extends JpaRepository<ImagemArtista, Integer> {

    List<ImagemArtista> findByFkArtistaIdArtista(Integer idArtista);

    int countByFkArtistaIdArtista(Integer idArtista);
}
