package br.com.justgeek.mobile.service;

import br.com.justgeek.mobile.mapper.usuario.DadosEnderecoMapper;
import br.com.justgeek.mobile.entities.Endereco;
import br.com.justgeek.mobile.dto.EnderecoDTO;

import java.util.List;

public interface EnderecoService {

    List<DadosEnderecoMapper> retornaEnderecosUsuarioLogado(int idUsuario);

    Endereco cadastrarEndereco(int idUsuario, EnderecoDTO endereco);

    Endereco atualizarEndereco(int idUsuario, int idEndereco, Endereco endereco);
}
