package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.entities.ItemCompra;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QuantityProductServiceImpl {

    private final ItemCompraRepository itemCompraRepository;

    @Autowired
    public QuantityProductServiceImpl(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }

    public Integer retornaQuantidadeDoProdutoAposFinalizadaACompra(int idUsuario, int idProduto) {
        Optional<ItemCompra> itemCompra = itemCompraRepository.findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoTrueAndStatusTrueAndFkCarrinhoFkPedidoFkUsuarioIdUsuario(idUsuario, idProduto, idUsuario);
        if (itemCompra.isPresent()) {
            return itemCompra.get().getQuantidade();
        } else {
            throw new CarrinhoException("[CARRINHO] Falha ao recuperar a quantidade de itens do produto.");
        }
    }

    public Integer retornaQuantidadeDoProduto(int idUsuario, int idProduto) {
        Optional<ItemCompra> itemCompra = itemCompraRepository.findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(idUsuario, idProduto);
        if (itemCompra.isPresent()) {
            return itemCompra.get().getQuantidade();
        } else {
            throw new CarrinhoException("[CARRINHO] Falha ao recuperar a quantidade de itens do produto.");
        }
    }
}
