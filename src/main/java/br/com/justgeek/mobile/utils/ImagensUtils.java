package br.com.justgeek.mobile.utils;

import br.com.justgeek.mobile.entities.ImagemProduto;
import br.com.justgeek.mobile.entities.UploadArtista;

import java.util.List;
import java.util.stream.Collectors;

public class ImagensUtils {

    private ImagensUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> retornaImagensProduto(List<ImagemProduto> imagens) {
        if (imagens.isEmpty()) {
            throw new NullPointerException("Lista de imagens vazia!");
        }
        return imagens.stream().map(ImagemProduto::getImagem).collect(Collectors.toList());
    }

    public static List<String> retornaImagensArtista(List<UploadArtista> imagens) {
        if (imagens.isEmpty()) {
            throw new NullPointerException("Lista de imagens vazia!");
        }
        return imagens.stream().map(UploadArtista::getImagem).collect(Collectors.toList());
    }
}
