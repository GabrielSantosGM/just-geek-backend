package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.Artista;
import br.com.justgeek.mobile.entities.ProgramasHabilidadesArtista;
import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ProgramasHabilidadesDTO {

    private String habilidade;
    private String programa;
    private Artista artista;

    private ProgramasHabilidadesDTO(String habilidade, String programa, Artista artista) {
        this.habilidade = habilidade;
        this.programa = programa;
        this.artista = artista;
    }

    public static ProgramasHabilidadesDTO getInstance(String habilidade, String programa, Artista artista) {
        return new ProgramasHabilidadesDTO(habilidade, programa, artista);
    }

    public ProgramasHabilidadesArtista dtoToEntity() {
        ProgramasHabilidadesArtista programasHabilidadesArtista = new ProgramasHabilidadesArtista();
        programasHabilidadesArtista.setFkArtista(this.artista);
        programasHabilidadesArtista.setHabilidade(this.habilidade);
        programasHabilidadesArtista.setPrograma(this.programa);
        return programasHabilidadesArtista;
    }

    public String getHabilidade() {
        return habilidade;
    }

    public String getPrograma() {
        return programa;
    }

    public Artista getArtista() {
        return artista;
    }
}
