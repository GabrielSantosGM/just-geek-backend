package br.com.justgeek.mobile.controller.usuario;

import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.mapper.LoginUsuarioMapper;
import br.com.justgeek.mobile.mapper.usuario.PerfilMapper;
import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.dto.UsuarioDTO;
import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.perfil.UsuarioServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/account")
public class AccountController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(AccountController.class);

    private final UsuarioServiceImpl serviceUser;

    @Autowired
    public AccountController(UsuarioRepository usuarioRepository, UsuarioServiceImpl serviceUser) {
        super(usuarioRepository);
        this.serviceUser = serviceUser;
    }

    @PostMapping("/register")
    public ResponseEntity<Usuario> registerUser(@RequestBody @Validated UsuarioDTO usuario) {
        try {
            serviceUser.cadastrar(usuario.dtoParaEntidade());
            return ResponseEntity.status(HttpStatus.CREATED).build();
        } catch (ContaException e) {
            LOG.warn(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
    }

    @PostMapping("/login")
    public ResponseEntity<Usuario> login(@RequestBody LoginUsuarioMapper dados) {
        try {
            return ResponseEntity.status(HttpStatus.OK).body(serviceUser.login(dados.getEmail(), dados.getSenha()));
        } catch (ContaException e) {
            LOG.error(e.getMessage());
            return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
        }
    }

    @PostMapping("/logout/{idUser}")
    public ResponseEntity<Usuario> logout(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            try {
                serviceUser.sair(idUser);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (ContaException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        LOG.warn("O usu??rio de ID {} n??o est?? logado", idUser);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }

    @GetMapping("/{idUser}")
    public ResponseEntity<PerfilMapper> returnsLoggedAccountData(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            try {
                PerfilMapper usuario = serviceUser.retornarDadosUsuarioLogado(idUser);
                return ResponseEntity.status(HttpStatus.OK).body(usuario);
            } catch (ContaException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/{idUser}")
    public ResponseEntity<String> changeAccountData(@PathVariable int idUser,
                                                    @RequestBody Usuario usuario) {
        if (authenticate(idUser)) {
            try {
                serviceUser.atualizarConta(idUser, usuario);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (ContaException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PatchMapping("/{idUser}")
    public ResponseEntity<String> changePassword(@PathVariable int idUser,
                                                 @RequestParam String password) {
        if (authenticate(idUser)) {
            try {
                serviceUser.mudarSenha(idUser, password);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (ContaException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();

    }

    @DeleteMapping("/{idUser}")
    public ResponseEntity<String> deleteAccount(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            serviceUser.deletarConta(idUser);
            return ResponseEntity.status(HttpStatus.OK).body("CONTA EXCLUIDA COM SUCESSO.");
        } else {
            LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("USUARIO NAO AUTENTICADO.");
        }
    }
}
