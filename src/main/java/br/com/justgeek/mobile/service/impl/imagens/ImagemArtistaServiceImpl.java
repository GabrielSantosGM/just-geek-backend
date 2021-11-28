package br.com.justgeek.mobile.service.impl.imagens;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ImagemArtista;
import br.com.justgeek.mobile.entities.UploadArtista;
import br.com.justgeek.mobile.exceptions.ImagemException;
import br.com.justgeek.mobile.repository.ArtistaRepository;
import br.com.justgeek.mobile.repository.ImagemArtistaRepository;
import br.com.justgeek.mobile.repository.UploadArtistaRepository;
import br.com.justgeek.mobile.service.ImagemArtistaService;
import br.com.justgeek.mobile.utils.ImagensUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ImagemArtistaServiceImpl implements ImagemArtistaService {

    private static final Logger LOG = LoggerFactory.getLogger(ImagemArtistaServiceImpl.class);

    private final ArtistaRepository artistaRepository;
    private final ImagemArtistaRepository imagemArtistaRepository;
    private final UploadArtistaRepository uploadArtistaRepository;

    @Autowired
    public ImagemArtistaServiceImpl(ArtistaRepository artistaRepository,
                                    ImagemArtistaRepository imagemArtistaRepository,
                                    UploadArtistaRepository uploadArtistaRepository) {
        this.artistaRepository = artistaRepository;
        this.imagemArtistaRepository = imagemArtistaRepository;
        this.uploadArtistaRepository = uploadArtistaRepository;
    }

    @Override
    public List<String> retornarImagensArtista(int idArtista) {
        List<UploadArtista> imagens = uploadArtistaRepository.findByFkArtistaIdArtista(idArtista);
        if (imagens.isEmpty()) {
            throw new NullPointerException("NENHUMA IMAGEM FOI ENCONTRADA!");
        }
        return ImagensUtils.retornaImagensArtista(imagens);
    }

    @Override
    public String retornarImagemPerfil(int idArtista) {
        return imagemArtistaRepository.findByFkArtistaIdArtistaOrderByIdImagemDesc(idArtista)
                .orElseThrow(() -> {
                    throw new NullPointerException("Nenhuma imagem do Artista de ID " + idArtista + " foi recuperada.");
                }).getImagem();
    }

    @Override
    public List<String> uploadImagemArtista(int idArtista,
                                            String imagem1,
                                            String imagem2,
                                            String imagem3) {
        Artista artista = artistaRepository.findByIdArtista(idArtista).orElseThrow();

        try {
            LOG.info("SALVANDO IMAGENS");
            return uploadImagem(imagem1, imagem2, imagem3, artista);
        } catch (Exception e) {
            throw new ImagemException(e.getMessage());
        }
    }

    private List<String> uploadImagem(String imagem1,
                                      String imagem2,
                                      String imagem3,
                                      Artista artista) {
        List<String> imagensArtista = new ArrayList<>();
        imagensArtista.add(imagem1);
        imagensArtista.add(imagem2);
        imagensArtista.add(imagem3);

        imagensArtista.forEach(imagem -> {
            if (!imagem.isEmpty()) {
                UploadArtista novaImagem = new UploadArtista();
                novaImagem.setImagem(imagem);
                novaImagem.setFkArtista(artista);
                uploadArtistaRepository.save(novaImagem);
            }
        });
        return imagensArtista;
    }
}
