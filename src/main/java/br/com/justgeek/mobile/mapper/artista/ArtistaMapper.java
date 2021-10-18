package br.com.justgeek.mobile.mapper.artista;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ProgramasHabilidadesArtista;

import java.util.List;
import java.util.stream.Collectors;

public class ArtistaMapper {

    private Integer idArtista;
    private String imagemPerfil;
    private String nome;
    private String apelido;
    private String biografia;
    private String contato;
    private List<String> skills;
    private List<String> programas;

    private ArtistaMapper(Artista artista) {
        this.idArtista = artista.getIdArtista();
        this.imagemPerfil = "/perfil/" + idArtista;
        this.nome = artista.getNomeCompleto();
        this.apelido = artista.getApelido();
        this.biografia = artista.getBiografia();
        this.contato = artista.getContato();
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

    public List<String> getSkills() {
        return skills;
    }

    public List<String> getProgramas() {
        return programas;
    }
}
