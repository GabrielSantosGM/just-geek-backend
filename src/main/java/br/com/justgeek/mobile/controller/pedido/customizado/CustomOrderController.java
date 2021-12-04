package br.com.justgeek.mobile.controller.pedido.customizado;

import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.dto.MercadoPagoPreferenceDTO;
import br.com.justgeek.mobile.dto.PedidoCustomizadoDTO;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.PedidoCustomizadoService;
import br.com.justgeek.mobile.service.impl.pedido.customizado.PedidoCustomizadoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/custom-order")
public class CustomOrderController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(CustomOrderController.class);

    private final PedidoCustomizadoService pedidoCustomizadoService;

    @Autowired
    public CustomOrderController(UsuarioRepository usuarioRepository, PedidoCustomizadoService pedidoCustomizadoService) {
        super(usuarioRepository);
        this.pedidoCustomizadoService = pedidoCustomizadoService;
    }

    @PostMapping("/{idUser}/{idArtist}")
    public ResponseEntity<MercadoPagoPreferenceDTO> registerOrder(@PathVariable int idUser,
                                                                  @PathVariable int idArtist,
                                                                  @RequestBody PedidoCustomizadoDTO pedidoCustomizado) {
        if (authenticate(idUser)) {
            try {
                LOG.info("REGISTRANDO PEDIDO DO USUARIO DE ID [ {} ]", idUser);
                return ResponseEntity.status(HttpStatus.CREATED).body(pedidoCustomizadoService.cadastrar(idUser, idArtist, pedidoCustomizado).retornarPreferenceMP());
            } catch (Exception e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
