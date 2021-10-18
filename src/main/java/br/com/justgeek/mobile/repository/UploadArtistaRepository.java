package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.UploadArtista;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UploadArtistaRepository extends JpaRepository<UploadArtista, Integer> {

    List<UploadArtista> findByFkArtistaIdArtista(Integer idArtista);

    int countByFkArtistaIdArtista(Integer idArtista);
}
