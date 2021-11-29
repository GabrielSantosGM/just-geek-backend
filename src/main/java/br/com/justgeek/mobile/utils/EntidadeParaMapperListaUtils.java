package br.com.justgeek.mobile.utils;

import br.com.justgeek.mobile.entities.*;
import br.com.justgeek.mobile.mapper.artista.ArtistaFavoritadoMapper;
import br.com.justgeek.mobile.mapper.artista.ArtistaMapper;
import br.com.justgeek.mobile.mapper.produto.*;
import br.com.justgeek.mobile.service.impl.produto.QuantityProductServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class EntidadeParaMapperListaUtils {

    private EntidadeParaMapperListaUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<ProdutoMapper> listaProdutos(List<Produto> produtos) {
        List<ProdutoMapper> produtosRetornados = new ArrayList<>();

        produtos.forEach(produto -> produtosRetornados.add(ProdutoMapper.gerar(produto)));
        return produtosRetornados;
    }

    public static List<ProdutoCarrinhoMapper> listaProdutosNoCarrinho(int idUsuario, List<Produto> produtos, QuantityProductServiceImpl quantityProductService) {
        List<ProdutoCarrinhoMapper> produtosRetornados = new ArrayList<>();

        produtos.forEach(produto -> produtosRetornados.add(ProdutoCarrinhoMapper.gerar(idUsuario, produto, quantityProductService)));
        return produtosRetornados;
    }

    public static List<ProdutoPedidoMapper> listaProdutosDoPedido(int idUsuario, List<Produto> produtos, QuantityProductServiceImpl quantityProductService) {
        List<ProdutoPedidoMapper> produtosRetornados = new ArrayList<>();

        produtos.forEach(produto -> produtosRetornados.add(ProdutoPedidoMapper.gerar(idUsuario, produto, quantityProductService)));
        return produtosRetornados;
    }

    public static List<ProdutoFavoritoMapper> listaProdutosFavoritos(List<ProdutoFavorito> produtos) {
        List<ProdutoFavoritoMapper> produtosRetornados = new ArrayList<>();

        produtos.forEach(produto -> produtosRetornados.add(ProdutoFavoritoMapper.gerar(produto)));
        return produtosRetornados;
    }

    public static List<AvaliacaoProdutoMapper> listaAvaliacoes(List<AvaliacaoProduto> avaliacoes) {
        List<AvaliacaoProdutoMapper> avaliacoesAdaptadas = new ArrayList<>();

        avaliacoes.forEach(avaliacaoProduto -> avaliacoesAdaptadas.add(AvaliacaoProdutoMapper.gerar(avaliacaoProduto)));
        return avaliacoesAdaptadas;
    }

    public static List<ArtistaMapper> listaArtista(List<Artista> artistas) {
        List<ArtistaMapper> artistasRetornados = new ArrayList<>();

        artistas.forEach(artista -> artistasRetornados.add(ArtistaMapper.transformar(artista)));
        return artistasRetornados;
    }

    public static List<ArtistaFavoritadoMapper> listaArtistasFavoritados(List<ArtistaFavorito> artistasFavoritados) {
        List<ArtistaFavoritadoMapper> artistasRetornados = new ArrayList<>();

        artistasFavoritados.forEach(artista -> artistasRetornados.add(ArtistaFavoritadoMapper.transformar(artista)));
        return artistasRetornados;
    }
}
