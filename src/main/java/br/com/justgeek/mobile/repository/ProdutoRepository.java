package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ProdutoRepository extends JpaRepository<Produto, Integer> {

    Optional<Produto> findByIdProduto(int id);

    List<Produto> findByCategoriaIgnoreCaseContains(String categoria);

    List<Produto> findTop4ByOrderByAcessosDesc();

    List<Produto> findTop4ByOrderByAcessos();

    List<Produto> findByCustomizadoTrue();

    List<Produto> findByTemaIgnoreCaseContains(String tema);

    List<Produto> findByFkRoupaModeloIgnoreCaseContains(String roupa);

    List<Produto> findByPrecoLessThan(Double value);

    List<Produto> findByOrderByPreco();

    List<Produto> findByOrderByPrecoDesc();

    List<Produto> findByItensComprasFkCarrinhoFkUsuarioIdUsuarioAndItensComprasStatusTrueAndItensComprasFkCarrinhoFinalizadoFalse(int idUsuario);

    List<Produto> findByItensComprasFkCarrinhoFkUsuarioIdUsuarioAndItensComprasFkCarrinhoFkPedidoIdPedido(int idUsuario, int idPedido);

    @Query("select p from Usuario u inner join Pedido p on u.idUsuario = p.fkUsuario inner join Carrinho c on " +
           "c.fkPedido = p.idPedido inner join ItemCompra it on it.fkCarrinho = c.idCarrinho inner join Produto pr on " +
            " it.fkProduto = pr.idProduto where u.idUsuario like ?1 and it.status = true and c.finalizado = false")
    List<Produto> returnsProductsInCart(int idUsuario);

    @Query("select x from Usuario u inner join Carrinho c on u.idUsuario = c.fkUsuario inner join ItemCompra i " +
            "on c.idCarrinho = i.fkCarrinho inner join Produto x on i.fkProduto = x.idProduto " +
            "where u.idUsuario like ?1 and i.status = true and c.finalizado = 0")
    List<Produto> searchForProductsInCart(int idUser);

    @Query("select p from Produto p inner join Roupa c on p.fkRoupa = c.idRoupa " +
            "where (:tema is null or p.tema like %:tema%)" +
            "or (:personagem is null or p.personagem like %:personagem%)" +
            "or (:peca is null or c.modelo like %:peca%)")
    List<Produto> searchProduct(@Param("tema") Optional<String> tema,
                                @Param("personagem") Optional<String> personagem,
                                @Param("peca") Optional<String> peca);

}
