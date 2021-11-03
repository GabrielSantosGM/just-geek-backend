package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.CupomDeDesconto;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;

public interface CupomDeDescontoRepository extends JpaRepository<CupomDeDesconto, Integer> {

//    @Query("select cd from CupomDeDesconto cd where cd.nomeCupom = ?1 and cd.dataInicioVigencia < ?2 and cd.dataFimVigencia > ?2")
//    Optional<CupomDeDesconto> findByVigencia(String nomeCupom, LocalDate dataAtual);

    Optional<CupomDeDesconto> findByNomeCupomAndDataInicioVigenciaLessThanAndDataFimVigenciaGreaterThan(String nomeCupom, LocalDate data1, LocalDate data2);

    Optional<CupomDeDesconto> findByNomeCupom(String nomeCupom);
}
