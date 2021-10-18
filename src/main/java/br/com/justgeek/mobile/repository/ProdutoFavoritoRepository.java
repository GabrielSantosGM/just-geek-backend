package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.ProdutoFavorito;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface ProdutoFavoritoRepository extends JpaRepository<ProdutoFavorito, Integer> {

    Optional<ProdutoFavorito> findByIdFavorito(int id);

    List<ProdutoFavorito> findByFkUsuarioIdUsuarioAndFavoritoTrue(int idUsuario);

    Optional<ProdutoFavorito> findByIdFavoritoAndFavoritoTrue(int idProdutoFavoritado);

    Optional<ProdutoFavorito> findByFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFavoritoTrue(int idUsuario, int idArtista);

}
