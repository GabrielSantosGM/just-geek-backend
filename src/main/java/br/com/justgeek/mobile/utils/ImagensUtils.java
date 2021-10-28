package br.com.justgeek.mobile.utils;

import br.com.justgeek.mobile.entities.ImagemProduto;

import java.util.ArrayList;
import java.util.List;

public class ImagensUtils {

    private ImagensUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<byte[]> retornaImagens(List<ImagemProduto> imagens) {
        List<byte[]> imagensRetornadas = new ArrayList<>();

        for (int i = 0; i < imagens.size(); i++) {
            byte[] imagem = imagens.get(i).getImagem();
            imagensRetornadas.add(imagem);
        }

        return imagensRetornadas;
    }

    public static byte[] retornaUma(List<ImagemProduto> imagens) {
        if (imagens.size() > 0) {
            return imagens.get(0).getImagem();
        }
        return null;
    }
}
