package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.mapper.produto.ProdutoMapper;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.enums.CategoriaProdutoEnum;
import br.com.justgeek.mobile.enums.RoupaEnum;
import br.com.justgeek.mobile.enums.TemaProdutoEnum;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.repository.*;
import br.com.justgeek.mobile.service.ProdutoFiltroService;
import br.com.justgeek.mobile.service.impl.imagens.ImagemProdutoServiceImpl;
import br.com.justgeek.mobile.utils.EntidadeParaMapperListaUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Locale;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class ProdutoFiltroServiceImpl implements ProdutoFiltroService {

    private final ProdutoRepository produtoRepository;

    @Autowired
    public ProdutoFiltroServiceImpl(ProdutoRepository productRepository, ImagemProdutoServiceImpl imagemProdutoService) {
        this.produtoRepository = productRepository;
    }

    @Override
    public List<ProdutoMapper> retornarProdutosPesquisados(Optional<String> pesquisa) {
        List<ProdutoMapper> produtos = EntidadeParaMapperListaUtils.listaProdutos(produtoRepository.findAll());
        if (pesquisa.get().trim().isEmpty()) {
            throw new IllegalArgumentException("ERRO AO PESQUISAR POR [ " + pesquisa.get() + " ].");
        } else {
            List<ProdutoMapper> produtosRetornados = produtos.stream()
                    .filter(produto -> produto.getNomeProduto().toLowerCase().contains(pesquisa.get().toLowerCase()))
                    .collect(Collectors.toList());
            if (produtosRetornados.isEmpty()) {
                throw new NoSuchElementException("NENHUM PRODUTO ENCONTRADO!");
            }
            return produtosRetornados;
        }
    }

    @Override
    public ProdutoMapper retornarProdutoEspecifico(int idProduto) {
        Produto produto = verificaProduto(idProduto);
        try {
            produto.setAcessos(produto.getAcessos() + 1);
            produtoRepository.save(produto);
            return ProdutoMapper.gerar(produto);
        } catch (ProdutoException e) {
            throw new ProdutoException("Nenhum produto encontrado");
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosSemelhantes(int idProduto) {
        Produto produto = verificaProduto(idProduto);
        return this.retornarProdutosTema(produto.getTema());
    }

    @Override
    public List<ProdutoMapper> retornarProdutosEmPromocao() {
        List<Produto> produtos = produtoRepository.findByPrecoLessThan(42.99);
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Nenhum produto em promocao recuperado.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarTodosProdutos() {
        List<Produto> produtos = produtoRepository.findAll();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Nenhum produto inserido no banco de dados.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosCategoria(String categoria) {
        List<Produto> produtos = produtoRepository.findByCategoriaIgnoreCaseContains(CategoriaProdutoEnum.valueFrom(categoria).getCategoria());
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados produtos com a categoria " + categoria + ".");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosTema(String tema) {
        List<Produto> produtos = produtoRepository.findByTemaIgnoreCaseContains(tema);
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados produtos com o tema " + tema + ".");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosPorRoupa(String roupa) {
        List<Produto> produtos = produtoRepository.findByFkRoupaModeloIgnoreCaseContains(RoupaEnum.valueFrom(roupa).getModeloRoupa());
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados produtos do modelo de roupa " + roupa + ".");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosMaisPopulares() {
        List<Produto> produtos = produtoRepository.findTop4ByOrderByAcessosDesc();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados os produtos mais populares.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosMenosPopulares() {
        List<Produto> produtos = produtoRepository.findTop4ByOrderByAcessos();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados os produtos menos populares.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosCustomizados() {
        List<Produto> produtos = produtoRepository.findByCustomizadoTrue();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados os produtos customizados.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosPrecoCrescente() {
        List<Produto> produtos = produtoRepository.findByOrderByPreco();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados os produtos em ordem crescente.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    @Override
    public List<ProdutoMapper> retornarProdutosPrecoDecrescente() {
        List<Produto> produtos = produtoRepository.findByOrderByPrecoDesc();
        if (produtos.isEmpty()) {
            throw new ProdutoException("[FiltroProdutos] Não foram encontrados os produtos em ordem decrescente.");
        } else {
            return EntidadeParaMapperListaUtils.listaProdutos(produtos);
        }
    }

    private Produto verificaProduto(int idProduto) {
        Optional<Produto> produtoVerificado = produtoRepository.findByIdProduto(idProduto);
        return produtoVerificado.orElseThrow(() -> {
            throw new ProdutoException("[Verificação Produto] Falha ao recuperar dados do produto de ID " + idProduto);
        });
    }
}
