package br.com.justgeek.mobile.service;

import br.com.caelum.stella.frete.entity.Encomenda;
import br.com.justgeek.mobile.dto.FreteDTO;

public interface FreteService {

    FreteDTO calcularFrete(String cep);

    Encomenda criarEncomenda(String cep);
}
