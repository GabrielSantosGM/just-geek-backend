package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.ItemCompra;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface ItemCompraRepository extends JpaRepository<ItemCompra, Integer> {

    Optional<ItemCompra> findByIdItem(int idItem);

    Optional<ItemCompra> findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(int idUsuario, int idProduto);

    Optional<ItemCompra> findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoTrueAndStatusTrueAndFkCarrinhoFkPedidoFkUsuarioIdUsuario(int idUsuario, int idProduto, int idUsuario2);

    Optional<ItemCompra> findByFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(int idProduto);

    @Query("select i from Usuario u inner join Carrinho c on u.idUsuario = c.fkUsuario inner join ItemCompra i " +
            " on c.idCarrinho = i.fkCarrinho inner join Produto x on i.fkProduto = x.idProduto " +
            " where u.idUsuario like ?1 and x.idProduto like ?2 and c.finalizado = 0 and i.status = 1")
    Optional<ItemCompra> pesquisaProduto(int idUsuario, int idProduto);
}
