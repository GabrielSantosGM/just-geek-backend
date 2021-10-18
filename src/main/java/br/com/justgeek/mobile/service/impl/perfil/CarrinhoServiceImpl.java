package br.com.justgeek.mobile.service.impl.perfil;

import br.com.justgeek.mobile.mapper.produto.ProdutoCarrinhoMapper;
import br.com.justgeek.mobile.entities.Carrinho;
import br.com.justgeek.mobile.entities.ItemCompra;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.repository.CarrinhoRepository;
import br.com.justgeek.mobile.repository.ItemCompraRepository;
import br.com.justgeek.mobile.repository.ProdutoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.CarrinhoService;
import br.com.justgeek.mobile.service.impl.produto.QuantityProductServiceImpl;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CarrinhoServiceImpl implements CarrinhoService {

    private static final Logger LOG = LoggerFactory.getLogger(CarrinhoServiceImpl.class);

    private final CarrinhoRepository carrinhoRepository;
    private final ProdutoRepository produtoRepository;
    private final UsuarioRepository usuarioRepository;
    private final ItemCompraRepository itemCompraRepository;

    @Autowired
    public CarrinhoServiceImpl(CarrinhoRepository carrinhoRepository,
                               ProdutoRepository produtoRepository,
                               UsuarioRepository usuarioRepository, ItemCompraRepository itemCompraRepository) {
        this.carrinhoRepository = carrinhoRepository;
        this.produtoRepository = produtoRepository;
        this.usuarioRepository = usuarioRepository;
        this.itemCompraRepository = itemCompraRepository;
    }

    @Override
    public List<ProdutoCarrinhoMapper> retornarProdutosNoCarrinho(int idUsuario) {
        List<Produto> produtos = produtoRepository.findByItensComprasFkCarrinhoFkUsuarioIdUsuarioAndItensComprasStatusTrueAndItensComprasFkCarrinhoFinalizadoFalse(idUsuario);

        if (produtos.isEmpty()) {
            throw new CarrinhoException("[CARRINHO] Não foram encontrados produtos no carrinho.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutosNoCarrinho(produtos, new QuantityProductServiceImpl(itemCompraRepository));
        }
    }

    @Override
    public void deletarProduto(int idUsuario, int idProduto, int quantidade) {
        Carrinho carrinho = new Carrinho();
        ItemCompra itemComprado = new ItemCompra();

        LOG.info("Recuperando valores do produto.");
        Produto produto = verificaProduto(idProduto);

        LOG.info("Verificando usuário.");
        Usuario usuario = verificaUsuario(idUsuario);

        LOG.info("Verificando carrinho.");
        Carrinho carrinhoVerificado = verificaCarrinho(idUsuario);

        LOG.info("Verificando item comprado.");
        Optional<ItemCompra> itemJaExistente = itemCompraRepository.findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(idUsuario, idProduto);

        try {
            if (itemJaExistente.isPresent()) {
                removerProduto(quantidade, carrinho, itemJaExistente.get(), produto, usuario, carrinhoVerificado);
            } else {
                removerProduto(quantidade, carrinho, itemComprado, produto, usuario, carrinhoVerificado);
            }
            carrinhoRepository.save(carrinho);
        } catch (CarrinhoException e) {
            LOG.warn(e.getMessage());
            throw new CompraException("[CARRINHO] Erro ao excluir produto do carrinho.");
        }

    }

    private void removerProduto(int quantidade, Carrinho carrinho, ItemCompra itemComprado, Produto produto, Usuario usuario, Carrinho carrinhoVerificado) {
        carrinho.setIdCarrinho(carrinhoVerificado.getIdCarrinho());
        carrinho.setFkUsuario(usuario);

        if ((itemComprado.getQuantidade() - quantidade) > 0) {
            itemComprado.setQuantidade(itemComprado.getQuantidade() - quantidade);
            itemComprado.setFkCarrinho(carrinhoVerificado);
            itemComprado.setFkProduto(produto);
            carrinho.setValorTotal(carrinhoVerificado.getValorTotal() - (produto.getPreco() * quantidade));
        } else if ((itemComprado.getQuantidade() - quantidade) == 0) {
            carrinho.setValorTotal(carrinhoVerificado.getValorTotal() - (produto.getPreco() * quantidade));
            itemComprado.setQuantidade(itemComprado.getQuantidade() - quantidade);
            itemComprado.setStatus(false);
            itemComprado.setFkCarrinho(null);
            itemComprado.setFkProduto(null);
        } else if ((itemComprado.getQuantidade() - quantidade) < 0) {
            throw new CarrinhoException("[CARRINHO] Quantidade de produtos a ser retirados excedendo a quantidade existente.");
        }
        itemCompraRepository.save(itemComprado);
    }

    private Carrinho verificaCarrinho(int idUsuario) {
        return carrinhoRepository
                .findByFinalizadoFalseAndFkUsuarioIdUsuario(idUsuario)
                .orElseThrow(() -> {
                    throw new CarrinhoException("[Verificação Carrinho] Falha ao recuperar os dados do carrinho.");
                });
    }

    private ItemCompra verificaItemComprado(int idUsuario, int idProduto) {
        return itemCompraRepository
                .findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(idUsuario, idProduto)
                .orElseThrow(() -> {
                    throw new ProdutoException("[Verificação Produto] Falha ao recuperar os dados do item.");
                });
    }

    private Usuario verificaUsuario(int idUsuario) {
        return usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(() -> {
            throw new ProdutoException("[Verificação Usuário] Falha ao recuperar dados do produto de ID " + idUsuario);
        });
    }

    private Produto verificaProduto(int idProduto) {
        return produtoRepository.findByIdProduto(idProduto).orElseThrow(() -> {
            throw new ProdutoException("[Verificação Produto] Falha ao recuperar dados do produto de ID " + idProduto);
        });
    }
}
