package br.com.justgeek.mobile.utils;

import br.com.justgeek.mobile.entities.ImagemProduto;

import java.util.List;
import java.util.stream.Collectors;

public class ImagensUtils {

    private ImagensUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<String> retornaImagens(List<ImagemProduto> imagens) {
        if (imagens.isEmpty()) {
            throw new NullPointerException("Lista de imagens vazia!");
        }
        return imagens.stream().map(ImagemProduto::getImagem).collect(Collectors.toList());
    }
}
