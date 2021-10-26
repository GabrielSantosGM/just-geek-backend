package br.com.justgeek.mobile.service.impl.perfil;

import br.com.justgeek.mobile.enums.respostas.requisicoes.RespostasRequisicoesUsuarioEnum;
import br.com.justgeek.mobile.mapper.usuario.PerfilMapper;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.exceptions.ContaException;
import br.com.justgeek.mobile.exceptions.EnderecoException;
import br.com.justgeek.mobile.messages.ContaUsuarioMensagens;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import br.com.justgeek.mobile.service.UsuarioService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    private static final Logger LOG = LoggerFactory.getLogger(UsuarioServiceImpl.class);

    private final UsuarioRepository usuarioRepository;

    public UsuarioServiceImpl(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public Usuario cadastrar(Usuario usuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByEmail(usuario.getEmail());

        if (optionalUsuario.isPresent()) {
            throw new ContaException("[Perfil] Já existe uma conta cadastrada com esse email.");
        } else {
            try {
                LOG.info("[Perfil] Cadastrando usuário...");
                return usuarioRepository.save(usuario);
            } catch (Exception e) {
                throw new ContaException(RespostasRequisicoesUsuarioEnum.MENSAGEM_VALIDACAO.getResposta());
            }
        }
    }

    @Override
    public Usuario login(String email, String senha) {
        Optional<Usuario> usuarioOptional = usuarioRepository.findByEmailAndSenha(email, senha);

        if (usuarioOptional.isPresent()) {
            LOG.info("[Perfil] Fazendo login...");
            Usuario usuario = usuarioOptional.get();
            usuario.setAutenticado(true);
            usuarioRepository.save(usuario);
            return usuario;
        }
        throw new ContaException("[Perfil] Email ou senha incorretos.");
    }

    @Override
    public Usuario sair(int idUsuario) {
        Optional<Usuario> usuarioConectado = usuarioRepository.findByIdUsuarioAndAutenticadoTrue(idUsuario);

        if (usuarioConectado.isEmpty()) {
            throw new ContaException("Erro ao recuperar usuário de ID " + idUsuario);
        } else {
            LOG.info("[Perfil] Fazendo logout...");
            usuarioConectado.get().setAutenticado(false);
            return usuarioRepository.save(usuarioConectado.get());
        }
    }

    @Override
    public PerfilMapper retornarDadosUsuarioLogado(int idUsuario) {
        Optional<Usuario> optionalUsuario = usuarioRepository.findByIdUsuario(idUsuario);

        if (optionalUsuario.isEmpty()) {
            throw new ContaException("[Perfil] Não foi possível recuperar os dados do usuário com ID " + idUsuario);
        } else {
            LOG.info("[Perfil] Retornando dados do usuário {}", optionalUsuario.get().getIdUsuario());
            return PerfilMapper.gerar(optionalUsuario.get());
        }
    }

    @Override
    public Usuario atualizarConta(int idUsuario, Usuario usuario) {
        Optional<Usuario> usuarioConectado = usuarioRepository.findByIdUsuario(idUsuario);

        if (usuarioConectado.isEmpty()) {
            throw new EnderecoException("[Perfil] Erro ao verificar usuário de ID " + idUsuario);
        } else {
            try {
                LOG.info("[Perfil] Atualizando a conta do usuário...");
                usuario.setIdUsuario(usuarioConectado.get().getIdUsuario());
                return usuarioRepository.save(usuario);
            } catch (Exception e) {
                throw new ContaException(ContaUsuarioMensagens.MENSAGEM_VALIDACAO);
            }
        }
    }

    @Override
    public Usuario mudarSenha(int idUsuario, String novaSenha) {
        Optional<Usuario> usuarioConectado = usuarioRepository.findByIdUsuario(idUsuario);

        if (usuarioConectado.isPresent()) {
            try {
                LOG.info("[Perfil] Alterando a senha...");
                Usuario usuario = usuarioConectado.get();
                usuario.setSenha(novaSenha);
                usuarioRepository.save(usuario);
                return usuario;
            } catch (Exception e) {
                throw new ContaException(ContaUsuarioMensagens.MENSAGEM_VALIDACAO);
            }
        }
        throw new ContaException("[Perfil] Não foi possível recuperar o ID " + idUsuario + " do usuário.");
    }
}

