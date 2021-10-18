package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.mapper.produto.ProdutoFavoritoMapper;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.entities.ProdutoFavorito;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.repository.ProdutoFavoritoRepository;
import br.com.justgeek.mobile.repository.ProdutoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.ProdutoFavoritoService;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoFavoritoServiceImpl implements ProdutoFavoritoService {

    private final UsuarioRepository usuarioRepository;
    private final ProdutoRepository produtoRepository;
    private final ProdutoFavoritoRepository produtoFavoritoRepository;

    @Autowired
    public ProdutoFavoritoServiceImpl(UsuarioRepository usuarioRepository,
                                      ProdutoRepository produtoRepository,
                                      ProdutoFavoritoRepository produtoFavoritoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.produtoRepository = produtoRepository;
        this.produtoFavoritoRepository = produtoFavoritoRepository;
    }

    @Override
    public ProdutoFavorito favoritarProduto(int idUsuario, int idProduto) {
        Optional<ProdutoFavorito> produtoFavorito = produtoFavoritoRepository.findByFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFavoritoTrue(idUsuario, idProduto);
        Usuario usuario = verificaUsuario(idUsuario);
        Produto produto = verificaProduto(idProduto);

        if (produtoFavorito.isPresent()) {
            throw new ProdutoException("[PRODUTO] Esse produto já foi favoritado.");
        } else {
            return produtoFavoritoRepository.save(favoritar(usuario, produto));
        }
    }

    @Override
    public ProdutoFavorito desfavoritarProduto(int idUsuario, int idProduto) {
        ProdutoFavorito produtoFavorito = verificarProdutoFavoritado(idUsuario, idProduto);
        produtoFavorito.setIdFavorito(produtoFavorito.getIdFavorito());
        produtoFavorito.setFkUsuario(null);
        produtoFavorito.setFavorito(false);
        return produtoFavoritoRepository.save(produtoFavorito);
    }

    @Override
    public List<ProdutoFavoritoMapper> retornarProdutosFavoritosDoUsuario(int idUsuario) {
        List<ProdutoFavorito> produtosFavoritos = produtoFavoritoRepository.findByFkUsuarioIdUsuarioAndFavoritoTrue(idUsuario);

        if (produtosFavoritos.isEmpty()) {
            throw new ProdutoException("[Produtos] Lista de produtos vazia.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutosFavoritos(produtosFavoritos);
        }
    }

    private ProdutoFavorito favoritar(Usuario usuario, Produto produto) {
        ProdutoFavorito produtoFavorito = new ProdutoFavorito();
        produtoFavorito.setFkUsuario(usuario);
        produtoFavorito.setFkProduto(produto);
        produtoFavorito.setFavorito(true);
        return produtoFavorito;
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

    private ProdutoFavorito verificarProdutoFavoritado(int idUsuario, int idProduto) {
        return produtoFavoritoRepository
                .findByFkUsuarioIdUsuarioAndFkProdutoIdProdutoAndFavoritoTrue(idUsuario, idProduto)
                .orElseThrow(() -> {
                    throw new ContaException("[Verificacao Produto Favoritado] Nenhum produto favoritado recuperado.");
                });
    }
}
