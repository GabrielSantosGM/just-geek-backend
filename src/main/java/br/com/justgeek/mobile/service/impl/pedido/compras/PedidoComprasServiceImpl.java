package br.com.justgeek.mobile.service.impl.pedido.compras;

import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.mapper.produto.ProdutoPedidoMapper;
import br.com.justgeek.mobile.repository.ItemCompraRepository;
import br.com.justgeek.mobile.repository.PedidoRepository;
import br.com.justgeek.mobile.repository.ProdutoRepository;
import br.com.justgeek.mobile.service.PedidoComprasService;
import br.com.justgeek.mobile.service.impl.produto.QuantityProductServiceImpl;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PedidoComprasServiceImpl implements PedidoComprasService {

    private final PedidoRepository pedidoRepository;
    private final ProdutoRepository produtoRepository;
    private final ItemCompraRepository itemCompraRepository;

    @Autowired
    public PedidoComprasServiceImpl(PedidoRepository pedidoRepository, ProdutoRepository produtoRepository, ItemCompraRepository itemCompraRepository) {
        this.pedidoRepository = pedidoRepository;
        this.produtoRepository = produtoRepository;
        this.itemCompraRepository = itemCompraRepository;
    }

    @Override
    public List<Pedido> retornaTodosOsPedidos(int idUsuario) {
        List<Pedido> pedidos = pedidoRepository.findAllByFkUsuarioIdUsuario(idUsuario);
        if (pedidos.isEmpty()) {
            throw new NullPointerException("LISTA DE PEDIDOS VAZIA!");
        }
        return pedidos;
    }

    @Override
    public List<ProdutoPedidoMapper> retornarTodosOsProdutosDoPedido(int idUsuario, int idPedido) {
        List<Produto> produtos = produtoRepository.findByItensComprasFkCarrinhoFkUsuarioIdUsuarioAndItensComprasFkCarrinhoFkPedidoIdPedido(idUsuario, idPedido);

        if (produtos.isEmpty()) {
            throw new NullPointerException("LISTA DE PRODUTOS DO PEDIDO VAZIA!");
        }
        return EntidadeParaMapperListaUtils.listaProdutosDoPedido(idUsuario, produtos, new QuantityProductServiceImpl(itemCompraRepository));
    }
}
