package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Carrinho;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CarrinhoRepository extends JpaRepository<Carrinho, Integer> {

    Optional<Carrinho> findByIdCarrinho(int idCarrinho);

    Optional<Carrinho> findByFinalizadoFalseAndFkUsuarioIdUsuario(int idUsuario);

    Optional<Carrinho> findByFkUsuarioIdUsuarioAndFkPedidoIdPedido(int idUsuario, int idPedido);

    @Query("select c from Usuario u inner join Carrinho c on u.idUsuario = c.fkUsuario where u.idUsuario like ?1")
    List<Carrinho> buscarPedidoPeloUsuario(int id);

    @Query(value = "select c.valor_total from usuario u inner join carrinho c on u.id_usuario = c.fk_usuario_carrinho " +
            " where c.fk_usuario_carrinho = :idUser and c.finalizado = 0", nativeQuery = true)
    Double valorTotal(int idUser);

    @Query(value = "select c.* from usuario u inner join carrinho c on u.id_usuario = c.fk_usuario " +
            " where c.fk_usuario = :idUser and c.finalizado = 0", nativeQuery = true)
    Optional<Carrinho> carrinhoUsuario(int idUser);
}
