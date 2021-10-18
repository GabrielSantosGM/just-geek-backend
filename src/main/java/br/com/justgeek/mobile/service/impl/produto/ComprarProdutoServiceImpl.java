package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.api.legada.Frete;
import br.com.justgeek.mobile.entities.*;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.repository.*;
import br.com.justgeek.mobile.service.ComprarProdutoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ComprarProdutoServiceImpl implements ComprarProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ComprarProdutoServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final CarrinhoRepository carrinhoRepository;
    private final ItemCompraRepository itemCompraRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoFavoritoRepository produtoFavoritoRepository;

    @Autowired
    public ComprarProdutoServiceImpl(UsuarioRepository usuarioRepository,
                                     CarrinhoRepository carrinhoRepository,
                                     ItemCompraRepository itemCompraRepository,
                                     ProdutoRepository produtoRepository,
                                     ProdutoFavoritoRepository produtoFavoritoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.carrinhoRepository = carrinhoRepository;
        this.itemCompraRepository = itemCompraRepository;
        this.produtoRepository = produtoRepository;
        this.produtoFavoritoRepository = produtoFavoritoRepository;
    }

    @Override
    public void moverProdutoParaCarrinho(int idUsuario, int idFavorito) {
        Produto produtoFavorito = verificaItemFavoritado(idFavorito).getFkProduto();
        this.adicionarProdutoNoCarrinho(idUsuario, produtoFavorito.getIdProduto(), 1);
    }

    @Override
    public void adicionarProdutoNoCarrinho(int idUsuario, int idProduto, int quantidade) {
        LOG.info("Verificando carrinho.");
        Optional<Carrinho> carrinhoVerificado = carrinhoRepository.findByFinalizadoFalseAndFkUsuarioIdUsuario(idUsuario);

        LOG.info("Verificando o produto a ser adicionado.");
        Produto produtoAdicionado = verificaProduto(idProduto);

        ItemCompra itemVerificado = verificaItemComprado(idUsuario, idProduto);

        LOG.info("Recuperando dados do usuário logado.");
        Usuario usuarioLogado = verificaUsuario(idUsuario);

        ItemCompra itemComprado = new ItemCompra();

        if (itemVerificado.getStatus()) {
            adicionando(idUsuario, quantidade, carrinhoVerificado, produtoAdicionado, usuarioLogado, itemVerificado);
        } else {
            adicionando(idUsuario, quantidade, carrinhoVerificado, produtoAdicionado, usuarioLogado, itemComprado);
        }

    }

    private void adicionando(int idUsuario,
                             int quantidade,
                             Optional<Carrinho> carrinhoVerificado,
                             Produto produtoAdicionado,
                             Usuario usuarioLogado,
                             ItemCompra itemComprado) {
        if (carrinhoVerificado.isPresent()) {
            carrinhoVerificado.get().setValorTotal(carrinhoVerificado.get().getValorTotal() + (produtoAdicionado.getPreco() * quantidade));
            itemComprado.setFkCarrinho(carrinhoVerificado.get());
            itemComprado.setQuantidade(itemComprado.getQuantidade() + quantidade);
        } else {
            Carrinho carrinho = criarCarrinho(idUsuario);
            carrinho.setValorTotal(carrinho.getValorTotal() + (produtoAdicionado.getPreco() * quantidade));
            carrinho.setFkUsuario(usuarioLogado);
            itemComprado.setFkCarrinho(carrinho);
            itemComprado.setQuantidade(itemComprado.getQuantidade() + quantidade);
        }
        itemComprado.setStatus(true);
        itemComprado.setFkProduto(produtoAdicionado);
        itemCompraRepository.save(itemComprado);
    }

    @Override
    public Double calcularFrete(String cep) {
        try {
            Frete freight = new Frete("01414-001", cep);
            return Double.parseDouble(freight.getValorFrete().replace(",", "."));
        } catch (IllegalStateException e) {
            throw new IllegalStateException("[CEP] Parâmetros informados inválidos para o cálculo.");
        }
    }

    public Carrinho criarCarrinho(int idUsuario) {
        Carrinho carrinho = new Carrinho();
        Usuario usuario = verificaUsuario(idUsuario);
        carrinho.setFkUsuario(usuario);
        carrinhoRepository.save(carrinho);
        return carrinho;
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

    private ItemCompra verificaItemComprado(int idUsuario, int idProduto) {
        Optional<ItemCompra> itemCompra = itemCompraRepository.findByFkCarrinhoFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFkCarrinhoFinalizadoFalseAndStatusTrue(idUsuario, idProduto);
        if (itemCompra.isPresent()) {
            return itemCompra.get();
        } else {
            return new ItemCompra();
        }
    }

    private ProdutoFavorito verificaItemFavoritado(int idProdutoFavoritado) {
        return produtoFavoritoRepository.findByIdFavorito(idProdutoFavoritado).orElseThrow(() -> {
            throw new ProdutoException("[Verificação Produto Favorito] Falha ao recuperar dados do produto favoritado.");
        });
    }
}
