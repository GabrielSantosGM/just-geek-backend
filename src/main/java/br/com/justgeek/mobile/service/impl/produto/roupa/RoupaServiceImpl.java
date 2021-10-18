package br.com.justgeek.mobile.service.impl.produto.roupa;

import br.com.justgeek.mobile.entities.Roupa;
import br.com.justgeek.mobile.dto.RoupaDTO;
import br.com.justgeek.mobile.repository.RoupaRepository;
import br.com.justgeek.mobile.service.RoupaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoupaServiceImpl implements RoupaService {

    private final RoupaRepository roupaRepository;

    @Autowired
    public RoupaServiceImpl(RoupaRepository roupaRepository) {
        this.roupaRepository = roupaRepository;
    }

    @Override
    public Roupa cadastrarRoupa(RoupaDTO roupa){
        return roupaRepository.save(roupa.gerar());
    }
}
