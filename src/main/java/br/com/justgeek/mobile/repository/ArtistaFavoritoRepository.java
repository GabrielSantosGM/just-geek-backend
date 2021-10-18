package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.ArtistaFavorito;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface ArtistaFavoritoRepository extends JpaRepository<ArtistaFavorito, Integer> {

    List<ArtistaFavorito> findAllByFkUsuarioIdUsuarioAndFavoritoTrue(int idUsuario);

    Optional<ArtistaFavorito> findByIdArtistaFavoritoAndFavoritoTrue(int idArtistaFavoritado);

    Optional<ArtistaFavorito> findByFkUsuarioIdUsuarioAndFkArtistaIdArtistaAndFavoritoTrue(int idUsuario, int idArtista);
}
