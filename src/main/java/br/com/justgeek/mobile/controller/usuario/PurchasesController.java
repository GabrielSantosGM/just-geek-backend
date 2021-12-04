package br.com.justgeek.mobile.controller.usuario;

import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.mapper.produto.ProdutoCarrinhoMapper;
import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.exceptions.CarrinhoException;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.perfil.CarrinhoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/purchases")
public class PurchasesController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(PurchasesController.class);

    private final CarrinhoServiceImpl carrinhoService;

    @Autowired
    public PurchasesController(UsuarioRepository usuarioRepository, CarrinhoServiceImpl carrinhoService) {
        super(usuarioRepository);
        this.carrinhoService = carrinhoService;
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<List<ProdutoCarrinhoMapper>> returnProductsInCart(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            try {
                LOG.info("[Produtos] Retornando produtos no carrinho...");
                return ResponseEntity.status(HttpStatus.OK).body(carrinhoService.retornarProdutosNoCarrinho(idUser));
            } catch (CarrinhoException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_AUTENTICACAO.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }

    @DeleteMapping("/{idUser}/{idProduct}/{quantidade}/{tamanho}")
    public ResponseEntity<String> deleteProductCart(@PathVariable int idUser,
                                                    @PathVariable int idProduct,
                                                    @PathVariable int quantidade,
                                                    @PathVariable String tamanho){
        if (authenticate(idUser)) {
            try {
                carrinhoService.deletarProduto(idUser, idProduct, quantidade, tamanho);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (CarrinhoException e) {
                LOG.warn(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_AUTENTICACAO.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
        }
    }
}
