package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.entities.ItemCompra;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.repository.CarrinhoRepository;
import br.com.justgeek.mobile.repository.ItemCompraRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class QuantidadeETamanhoProdutoImpl {

    private final ItemCompraRepository itemCompraRepository;
    private CarrinhoRepository carrinhoRepository;

    @Autowired
    public QuantidadeETamanhoProdutoImpl(ItemCompraRepository itemCompraRepository, CarrinhoRepository carrinhoRepository) {
        this.itemCompraRepository = itemCompraRepository;
        this.carrinhoRepository = carrinhoRepository;
    }

    @Autowired
    public QuantidadeETamanhoProdutoImpl(ItemCompraRepository itemCompraRepository) {
        this.itemCompraRepository = itemCompraRepository;
    }

    public Integer retornaQuantidadeDoProdutoAposFinalizadaACompra(int idUsuario, int idProduto) {
        Optional<ItemCompra> itemCompra = itemCompraRepository.findByStatusTrueAndFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoTrueAndFkCarrinhoFkPedidoFkUsuarioIdUsuario(idUsuario, idProduto, idUsuario);
        if (itemCompra.isPresent()) {
            return itemCompra.get().getQuantidade();
        } else {
            throw new CarrinhoException("[CARRINHO] Falha ao recuperar a quantidade de itens do produto.");
        }
    }

    public String retornaTamanhoProduto(int idUsuario, int idProduto) {
        return itemCompraRepository
                .findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(idUsuario, idProduto)
                .orElseThrow()
                .getTamanho();
    }

    public Integer retornaQuantidadeDoProduto(int idUsuario, int idProduto, String tamanho) {
        return itemCompraRepository
                .findByTamanhoAndFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(tamanho, idUsuario, idProduto)
                .orElseThrow()
                .getQuantidade();
    }
}
