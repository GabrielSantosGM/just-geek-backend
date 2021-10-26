package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.mapper.usuario.PerfilMapper;
import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.dto.UsuarioDTO;

public interface UsuarioService {

    Usuario cadastrar(Usuario usuario);

    Usuario login(String email, String senha);

    Usuario sair(int idUsuario);

    PerfilMapper retornarDadosUsuarioLogado(int idUsuario);

    Usuario atualizarConta(int idUsuario, Usuario usuario);

    Usuario mudarSenha(int idUsuario, String novaSenha);
}
