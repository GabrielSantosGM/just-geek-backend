package br.com.justgeek.mobile.dto;

import br.com.justgeek.mobile.entities.CupomDeDesconto;
import br.com.justgeek.mobile.enums.CupomDescontoEnum;

import java.time.LocalDate;

public class CupomDeDescontoDTO {

    private String nomeCupom;
    private LocalDate dataInicioVigencia;
    private LocalDate dataFimVigencia;

    public CupomDeDesconto transformEntity() {
        if (CupomDescontoEnum.valueFrom(nomeCupom).name().contains(nomeCupom)) {
            return CupomDeDesconto.Builder.criarCupom()
                    .withNomeCupom(CupomDescontoEnum.valueFrom(nomeCupom).name())
                    .withPorcentagemDesconto(CupomDescontoEnum.valueFrom(nomeCupom).getPorcentagemDesconto())
                    .withDataInicioVigencia(dataInicioVigencia)
                    .withDataFimVigencia(dataFimVigencia)
                    .build();
        }
        return null;
    }

    public String getNomeCupom() {
        return nomeCupom;
    }

    public LocalDate getDataInicioVigencia() {
        return dataInicioVigencia;
    }

    public LocalDate getDataFimVigencia() {
        return dataFimVigencia;
    }
}
