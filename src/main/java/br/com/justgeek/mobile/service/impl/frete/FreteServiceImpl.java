package br.com.justgeek.mobile.service.impl.frete;

import br.com.caelum.stella.frete.CalculoFreteCorreio;
import br.com.caelum.stella.frete.entity.Encomenda;
import br.com.caelum.stella.frete.entity.Frete;
import br.com.caelum.stella.frete.enums.Servico;
import br.com.caelum.stella.frete.exception.CorreiosException;
import br.com.justgeek.mobile.dto.FreteDTO;
import br.com.justgeek.mobile.exceptions.CompraException;
import br.com.justgeek.mobile.service.FreteService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class FreteServiceImpl implements FreteService {

    private static final Logger LOG = LoggerFactory.getLogger(FreteServiceImpl.class);

    @Override
    public FreteDTO calcularFrete(String cep) {
        try {
            LOG.info("CRIANDO ENCOMENDA.");
            Encomenda encomenda = criarEncomenda(cep);

            LOG.info("CALCULANDO FRETE.");
            Frete frete = CalculoFreteCorreio.calcularFrete(encomenda, Servico.PAC);
            return FreteDTO.Builder.aFreteDTO()
                    .withServico("PAC")
                    .withPrazoParaEntrega(frete.getPrazoEntrega())
                    .withValorAPagar(frete.getValor())
                    .withEntregaDomiciliar(frete.getEntregaDomicilar())
                    .withEntregaSabado(frete.getEntregaSabado())
                    .build();
        } catch (CorreiosException e) {
            throw new CompraException(e.getMessage());
        }
    }

    @Override
    public Encomenda criarEncomenda(String cep) {
        return new Encomenda()
                .doCep("01414-001")
                .paraOCep(cep)
                .comAltura("005")
                .comComprimento("050")
                .comLargura("050")
                .comPeso(0.25)
                .comAvisoDeRecebimento()
                .semMaoPropria();
    }
}
