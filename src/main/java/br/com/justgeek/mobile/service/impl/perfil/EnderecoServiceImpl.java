package br.com.justgeek.mobile.service.impl.perfil;

import br.com.justgeek.mobile.mapper.usuario.DadosEnderecoMapper;
import br.com.justgeek.mobile.entities.Endereco;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.dto.EnderecoDTO;
import br.com.justgeek.mobile.exceptions.EnderecoException;
import br.com.justgeek.mobile.repository.EnderecoRepository;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.EnderecoService;
import br.com.justgeek.mobile.utils.EnderecoUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EnderecoServiceImpl implements EnderecoService {

    private static final Logger LOG = LoggerFactory.getLogger(EnderecoServiceImpl.class);

    private final UsuarioRepository usuarioRepository;
    private final EnderecoRepository enderecoRepository;

    public EnderecoServiceImpl(UsuarioRepository usuarioRepository, EnderecoRepository enderecoRepository) {
        this.usuarioRepository = usuarioRepository;
        this.enderecoRepository = enderecoRepository;
    }

    @Override
    public List<DadosEnderecoMapper> retornaEnderecosUsuarioLogado(int idUsuario) {
        List<Endereco> listaEnderecos = enderecoRepository.findByFkUsuarioIdUsuario(idUsuario);
        return EnderecoUtils.retornaListaEnderecos(listaEnderecos);
    }

    @Override
    public Endereco cadastrarEndereco(int idUsuario, EnderecoDTO endereco) {
        Optional<Usuario> usuarioLogado = usuarioRepository.findByIdUsuario(idUsuario);

        if (usuarioLogado.isEmpty()) {
            throw new EnderecoException("[Perfil] Erro ao verificar usuário de ID " + idUsuario);
        } else {
            try {
                LOG.info("[Endereço] Cadastrando endereço...");
                return enderecoRepository.save(EnderecoDTO.criar(endereco.gerar(), usuarioLogado.get()).gerar());
            } catch (EnderecoException e) {
                throw new EnderecoException(e.getMessage());
            }
        }
    }

    @Override
    public Endereco atualizarEndereco(int idUsuario, int idEndereco, Endereco endereco){
        Optional<Usuario> usuarioLogado = usuarioRepository.findByIdUsuario(idUsuario);
        Optional<Endereco> enderecoRecuperado = enderecoRepository.findByIdEndereco(idEndereco);

        if(usuarioLogado.isEmpty()){
            throw new EnderecoException("[Perfil] Erro ao verificar usuário de ID " + idUsuario);
        }else{
            if(enderecoRecuperado.isEmpty()){
                throw new EnderecoException("[Endereço] Nenhum endereço existente no ID " + idEndereco);
            }else{
                LOG.info("[Endereço] Atualizando o endereço...");
                endereco.setIdEndereco(enderecoRecuperado.get().getIdEndereco());
                endereco.setFkUsuario(enderecoRecuperado.get().getFkUsuario());
                return enderecoRepository.save(endereco);
            }
        }
    }
}
