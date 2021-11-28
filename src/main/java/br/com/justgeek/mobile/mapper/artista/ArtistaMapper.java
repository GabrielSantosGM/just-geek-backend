package br.com.justgeek.mobile.mapper.artista;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ProgramasHabilidadesArtista;
import br.com.justgeek.mobile.entities.UploadArtista;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistaMapper {

    private final Integer idArtista;
    private final String imagemPerfil;
    private final String nome;
    private final String apelido;
    private final String biografia;
    private final String contato;
    private final String redeSocial1;
    private final String redeSocial2;
    private final String categoria;
    private final String artes;
    private final List<String> skills;
    private final List<String> programas;

    private ArtistaMapper(Artista artista) {
        this.idArtista = artista.getIdArtista();
        this.imagemPerfil = "/artist-image/perfil/" + idArtista;
        this.nome = artista.getNomeCompleto();
        this.apelido = artista.getApelido();
        this.biografia = artista.getBiografia();
        this.contato = artista.getContato();
        this.redeSocial1 = artista.getRedeSocial1();
        this.redeSocial2 = artista.getRedeSocial2();
        this.categoria = artista.getCategoria();
        this.artes = "/artist-image/images/" + artista.getIdArtista();
        this.skills = artista.getProgramasHabilidadesArtistas().stream().map(ProgramasHabilidadesArtista::getHabilidade).collect(Collectors.toList());
        this.programas = artista.getProgramasHabilidadesArtistas().stream().map(ProgramasHabilidadesArtista::getPrograma).collect(Collectors.toList());
    }

    public static ArtistaMapper transformar(Artista artista) {
        return new ArtistaMapper(artista);
    }

    public Integer getIdArtista() {
        return idArtista;
    }

    public String getImagemPerfil() {
        return imagemPerfil;
    }

    public String getNome() {
        return nome;
    }

    public String getApelido() {
        return apelido;
    }

    public String getBiografia() {
        return biografia;
    }

    public String getContato() {
        return contato;
    }

    public String getRedeSocial1() {
        return redeSocial1;
    }

    public String getRedeSocial2() {
        return redeSocial2;
    }

    public String getCategoria() {
        return categoria;
    }

    public String getArtes() {
        return artes;
    }

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getProgramas() {
        return programas;
    }
}
