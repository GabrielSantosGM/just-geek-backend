package br.com.justgeek.mobile.entities;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@Entity
@Table(name = "cupom_de_desconto")
public class CupomDeDesconto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_cupom")
    private Integer idCupom;

    @NotBlank
    @Column(name = "nomeCupom")
    private String nomeCupom;

    @NotNull
    @Column(name = "porcentagem_desconto")
    private Integer porcentagemDesconto;

    @NotNull
    @Column(name = "data_inicio_vigencia")
    private LocalDate dataInicioVigencia;

    @NotNull
    @Column(name = "data_fim_vigencia")
    private LocalDate dataFimVigencia;

    public CupomDeDesconto() {

    }

    public Integer getIdCupom() {
        return idCupom;
    }

    public void setIdCupom(Integer idCupom) {
        this.idCupom = idCupom;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public void setNomeCupom(String cupom) {
        this.nomeCupom = cupom;
    }

    public Integer getPorcentagemDesconto() {
        return porcentagemDesconto;
    }

    public void setPorcentagemDesconto(Integer porcentagemDesconto) {
        this.porcentagemDesconto = porcentagemDesconto;
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public void setDataInicioVigencia(LocalDate dataInicioVigencia) {
        this.dataInicioVigencia = dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }

    public void setDataFimVigencia(LocalDate dataFimVigencia) {
        this.dataFimVigencia = dataFimVigencia;
    }

    public static final class Builder {
        private String nomeCupom;
        private Integer porcentagemDesconto;
        private LocalDate dataInicioVigencia;
        private LocalDate dataFimVigencia;

        private Builder() {
        }

        public static Builder criarCupom() {
            return new Builder();
        }

        public Builder withNomeCupom(String nomeCupom) {
            this.nomeCupom = nomeCupom;
            return this;
        }

        public Builder withPorcentagemDesconto(Integer porcentagemDesconto) {
            this.porcentagemDesconto = porcentagemDesconto;
            return this;
        }

        public Builder withDataInicioVigencia(LocalDate dataInicioVigencia) {
            this.dataInicioVigencia = dataInicioVigencia;
            return this;
        }

        public Builder withDataFimVigencia(LocalDate dataFimVigencia) {
            this.dataFimVigencia = dataFimVigencia;
            return this;
        }

        public CupomDeDesconto build() {
            CupomDeDesconto cupomDeDesconto = new CupomDeDesconto();
            cupomDeDesconto.setNomeCupom(nomeCupom);
            cupomDeDesconto.setPorcentagemDesconto(porcentagemDesconto);
            cupomDeDesconto.setDataInicioVigencia(dataInicioVigencia);
            cupomDeDesconto.setDataFimVigencia(dataFimVigencia);
            return cupomDeDesconto;
        }
    }
}
