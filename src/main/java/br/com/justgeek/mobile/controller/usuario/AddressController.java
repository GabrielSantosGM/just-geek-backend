package br.com.justgeek.mobile.controller.usuario;

import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.mapper.usuario.DadosEnderecoMapper;
import br.com.justgeek.mobile.configs.Authenticated;
import br.com.justgeek.mobile.entities.Endereco;
import br.com.justgeek.mobile.dto.EnderecoDTO;
import br.com.justgeek.mobile.exceptions.EnderecoException;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.impl.perfil.EnderecoServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/addresses")
public class AddressController extends Authenticated {

    private static final Logger LOG = LoggerFactory.getLogger(AddressController.class);

    private final EnderecoServiceImpl serviceAddress;

    @Autowired
    public AddressController(UsuarioRepository usuarioRepository, EnderecoServiceImpl serviceUser) {
        super(usuarioRepository);
        this.serviceAddress = serviceUser;
    }

    @GetMapping("/address/{idUser}")
    public ResponseEntity<List<DadosEnderecoMapper>> returnAddressesLoggedAccount(@PathVariable int idUser) {
        if (authenticate(idUser)) {
            List<DadosEnderecoMapper> enderecos = serviceAddress.retornaEnderecosUsuarioLogado(idUser);
            if (enderecos.isEmpty()) {
                LOG.warn("Usuário de ID {} não possui endereços cadastrados.", idUser);
                return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
            }
            LOG.warn("Retornando endereços...");
            return ResponseEntity.status(HttpStatus.OK).body(enderecos);
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PostMapping("/register-address/{idUser}")
    public ResponseEntity<Endereco> registerAddress(@PathVariable int idUser,
                                                    @RequestBody @Validated EnderecoDTO endereco) {
        if(authenticate(idUser)){
            try {
                serviceAddress.cadastrarEndereco(idUser, endereco);
                return ResponseEntity.status(HttpStatus.CREATED).build();
            } catch (EnderecoException e) {
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }

    @PutMapping("/change/{idUser}/{idAddress}")
    public ResponseEntity<Endereco> changeAddress(@PathVariable int idUser,
                                                  @PathVariable int idAddress,
                                                  @RequestBody Endereco endereco){
        if(authenticate(idUser)){
            try{
                serviceAddress.atualizarEndereco(idUser, idAddress, endereco);
                return ResponseEntity.status(HttpStatus.OK).build();
            } catch (EnderecoException e){
                LOG.error(e.getMessage());
                return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
            }
        }
        LOG.warn(RespostasRequisicoesUsuarioEnum.MENSAGEM_UNAUTHORIZED.getResposta(), idUser);
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
    }
}
