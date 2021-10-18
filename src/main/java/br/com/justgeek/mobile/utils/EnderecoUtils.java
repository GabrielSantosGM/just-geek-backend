package br.com.justgeek.mobile.utils;

import br.com.justgeek.mobile.mapper.usuario.DadosEnderecoMapper;
import br.com.justgeek.mobile.entities.Endereco;

import java.util.ArrayList;
import java.util.List;

public class EnderecoUtils {

    private EnderecoUtils() {
        throw new IllegalStateException("Utility class");
    }

    public static List<DadosEnderecoMapper> retornaListaEnderecos(List<Endereco> enderecos){
        List<DadosEnderecoMapper> listaEnderecos = new ArrayList<>();

        enderecos.forEach(endereco -> listaEnderecos.add(DadosEnderecoMapper.gerar(endereco)));
        return listaEnderecos;
    }
}
