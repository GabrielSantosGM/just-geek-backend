package br.com.justgeek.mobile.controller.pedido.compras;

import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.entities.Pedido;
import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.mapper.produto.ProdutoCarrinhoMapper;
import br.com.justgeek.mobile.mapper.produto.ProdutoPedidoMapper;
import br.com.justgeek.mobile.mapper.usuario.DetalhesPedidoMapper;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.PedidoComprasService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/orders")
public class OrderController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(OrderController.class);

    private final PedidoComprasService pedidoComprasService;

    @Autowired
    public OrderController(UsuarioRepository usuarioRepository, PedidoComprasService pedidoComprasService) {
        super(usuarioRepository);
        this.pedidoComprasService = pedidoComprasService;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<Pedido>> returnAllOrders(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            try {
                LOG.info("RETORNANDO TODOS OS PEDIDOS DO USUARIO DE ID {}", idUser);
                return ResponseEntity.status(HttpStatus.OK).body(pedidoComprasService.retornaTodosOsPedidos(idUser));
            } catch (NullPointerException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/specific/{idUser}/{idOrder}")
    public ResponseEntity<DetalhesPedidoMapper> returnAllOrders(@PathVariable int idUser, @PathVariable int idOrder) {
        if (authenticate(idUser)) {
            try {
                LOG.info("RETORNANDO INFORMACOES DO USUARIO DE ID {} E SEU PEDIDO DE ID {}", idUser, idOrder);
                return ResponseEntity.status(HttpStatus.OK).body(pedidoComprasService.retornaDetalhesPedido(idUser, idOrder));
            } catch (NullPointerException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @GetMapping("/{idUser}/{idOrder}")
    public ResponseEntity<List<ProdutoPedidoMapper>> returnOrderProducts(@PathVariable int idUser, @PathVariable int idOrder) {
        if (authenticate(idUser)) {
            try {
                LOG.info("RETORNANDO TODOS OS PRODUTOS DO PEDIDO DE ID {} DO USUARIO DE ID {}", idOrder, idUser);
                return ResponseEntity.status(HttpStatus.OK).body(pedidoComprasService.retornarTodosOsProdutosDoPedido(idUser, idOrder));
            } catch (NullPointerException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

}
