package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.entities.Roupa;
import br.com.justgeek.mobile.dto.RoupaDTO;

public interface RoupaService {

    Roupa cadastrarRoupa(RoupaDTO roupa);
}
