package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.dto.AvaliacaoProdutoDTO;
import br.com.justgeek.mobile.entities.AvaliacaoProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.entities.Roupa;
import br.com.justgeek.mobile.dto.ProdutoDTO;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.mapper.produto.AvaliacaoProdutoMapper;
import br.com.justgeek.mobile.repository.AvaliacaoProdutoRepository;
import br.com.justgeek.mobile.repository.ProdutoRepository;
import br.com.justgeek.mobile.repository.RoupaRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.ProdutoService;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ProdutoServiceImpl implements ProdutoService {

    private final AvaliacaoProdutoRepository avaliacaoProdutoRepository;
    private final ProdutoRepository produtoRepository;
    private final RoupaRepository roupaRepository;
    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ProdutoServiceImpl(AvaliacaoProdutoRepository avaliacaoProdutoRepository,
                              ProdutoRepository produtoRepository,
                              RoupaRepository roupaRepository,
                              UsuarioRepository usuarioRepository) {
        this.avaliacaoProdutoRepository = avaliacaoProdutoRepository;
        this.produtoRepository = produtoRepository;
        this.roupaRepository = roupaRepository;
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Produto cadastrarProduto(int idRoupa, ProdutoDTO product) {
        Optional<Roupa> roupa = roupaRepository.findByIdRoupa(idRoupa);

        if (roupa.isPresent()) {
            return produtoRepository.save(ProdutoDTO.criar(product.gerar(), roupa.get()).gerar());
        } else {
            throw new ProdutoException("[Roupa] Não foi possível cadastrar o produto pois não existe esse modelo de roupa.");
        }
    }

    @Override
    public AvaliacaoProduto fazerAvaliacaoDoProduto(int idUsuario, int idProduto, AvaliacaoProduto avaliacaoProduto) {
        Usuario usuario = verificaUsuario(idUsuario);
        Produto produto = verificaProduto(idProduto);

        try {
            return avaliacaoProdutoRepository.save(AvaliacaoProdutoDTO.gerar(usuario, produto, avaliacaoProduto).transformarParaEntidade());
        } catch (Exception e) {
            throw new ProdutoException("[AVALIACAO_PRODUTO] Falha ao registrar sua avaliacao do Produto de ID " + idProduto);
        }
    }

    @Override
    public List<AvaliacaoProdutoMapper> retornarAvaliacoesDoProduto(int idProduto) {
        List<AvaliacaoProduto> avaliacoes = avaliacaoProdutoRepository.findAllByFkProdutoIdProduto(idProduto);

        if (avaliacoes.isEmpty()) {
            throw new ProdutoException("[AVALIACOES] Não foram encontrados avaliações do produto de ID " + idProduto);
        } else {
            return EntidadeParaMapperListaUtils.listaAvaliacoes(avaliacoes);
        }
    }

    private Usuario verificaUsuario(int idUsuario) {
        return usuarioRepository.findByIdUsuario(idUsuario).orElseThrow(() -> {
            throw new ProdutoException("[Verificacao Usuario] Falha ao recuperar dados do produto de ID " + idUsuario);
        });
    }

    private Produto verificaProduto(int idProduto) {
        return produtoRepository.findByIdProduto(idProduto).orElseThrow(() -> {
            throw new ProdutoException("[Verificação Produto] Falha ao recuperar dados do produto de ID " + idProduto);
        });
    }
}
