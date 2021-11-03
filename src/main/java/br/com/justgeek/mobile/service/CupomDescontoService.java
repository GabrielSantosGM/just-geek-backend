package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.CupomDeDesconto;

public interface CupomDescontoService {

    CupomDeDesconto cadastrarCupom(CupomDeDesconto cupomDeDesconto);

    CupomDeDesconto retornarCupom(String nomeCupom);
}
