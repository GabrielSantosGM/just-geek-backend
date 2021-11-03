package br.com.justgeek.mobile.service.impl.produto;

import br.com.justgeek.mobile.entities.CupomDeDesconto;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.repository.CupomDeDescontoRepository;
import br.com.justgeek.mobile.service.CupomDescontoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class CupomDescontoServiceImpl implements CupomDescontoService {

    private static final Logger LOG = LoggerFactory.getLogger(CupomDescontoServiceImpl.class);

    private final CupomDeDescontoRepository cupomDeDescontoRepository;

    @Autowired
    public CupomDescontoServiceImpl(CupomDeDescontoRepository cupomDeDescontoRepository) {
        this.cupomDeDescontoRepository = cupomDeDescontoRepository;
    }

    @Override
    public CupomDeDesconto cadastrarCupom(CupomDeDesconto cupomDeDesconto) {
        return cupomDeDescontoRepository.save(cupomDeDesconto);
    }

    @Override
    public CupomDeDesconto retornarCupom(String nomeCupom) {
        return cupomDeDescontoRepository
                .findByNomeCupomAndDataInicioVigenciaLessThanAndDataFimVigenciaGreaterThan(nomeCupom, LocalDate.now(), LocalDate.now())
                .orElseThrow(() -> {
                    throw new CompraException("NENHUM CUPOM ENCONTRADO!");
                });
    }
}
