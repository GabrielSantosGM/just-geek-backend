package br.com.justgeek.mobile.service.impl.imagens;

import br.com.justgeek.mobile.entities.ImagemProduto;
import br.com.justgeek.mobile.entities.Produto;
import br.com.justgeek.mobile.exceptions.ImagemException;
import br.com.justgeek.mobile.exceptions.ProdutoException;
import br.com.justgeek.mobile.repository.ImagemProdutoRepository;
import br.com.justgeek.mobile.repository.ProdutoRepository;
import br.com.justgeek.mobile.service.ImagemProdutoService;
import br.com.justgeek.mobile.utils.ImagensUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemProdutoServiceImpl implements ImagemProdutoService {

    private static final Logger LOG = LoggerFactory.getLogger(ImagemProdutoServiceImpl.class);

    private final ImagemProdutoRepository imagemRepository;
    private final ProdutoRepository produtoRepository;

    @Autowired
    public ImagemProdutoServiceImpl(ImagemProdutoRepository imagemRepository, ProdutoRepository produtoRepository) {
        this.imagemRepository = imagemRepository;
        this.produtoRepository = produtoRepository;
    }

    @Override
    public List<byte[]> retornaImagensProduto(int idProduto) {
        List<ImagemProduto> imagens = imagemRepository.findByFkProdutoIdProduto(idProduto);
        if (imagens.isEmpty()) {
            throw new ImagemException("Nenhuma imagem associada ao produto de ID" + idProduto);
        }
        return ImagensUtils.retornaImagens(imagens);
    }

    @Override
    public List<MultipartFile> uploadImagemProduto(int idProduto,
                                                   MultipartFile imagem1,
                                                   MultipartFile imagem2,
                                                   MultipartFile imagem3) {
        Optional<Produto> produto = produtoRepository.findByIdProduto(idProduto);

        if (produto.isPresent()) {
            try {
                LOG.info("Salvando imagens...");
                return uploadImagem(idProduto, imagem1, imagem2, imagem3, produto);
            } catch (Exception e) {
                throw new ImagemException(e.getMessage());
            }
        } else {
            throw new ProdutoException("Produto de ID " + idProduto + "inexistente");
        }
    }

    private List<MultipartFile> uploadImagem(int idProduto,
                                             MultipartFile imagem1,
                                             MultipartFile imagem2,
                                             MultipartFile imagem3,
                                             Optional<Produto> produto) throws IOException {
        List<MultipartFile> imagens = new ArrayList<>();
        imagens.add(imagem1);
        imagens.add(imagem2);
        imagens.add(imagem3);

        for (int i = 0; i < imagens.size(); i++) {
            if (imagens.get(i) != null) {
                if (imagemRepository.countByFkProdutoIdProduto(idProduto) == 3) {
                    throw new ImagemException("[Imagem] Excedido a quantidade de upload de imagens desse produto");
                } else {
                    ImagemProduto imagem = new ImagemProduto();
                    imagem.setImagem(imagens.get(i).getBytes());
                    imagem.setFkProduto(produto.get());
                    imagemRepository.save(imagem);
                }
            } else {
                LOG.warn("[Imagem] O indíce [ {} ] aponta que está nulo o campo.", i);
            }
        }
        return imagens;
    }
}
