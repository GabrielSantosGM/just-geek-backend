package br.com.justgeek.mobile.configs;

import br.com.justgeek.mobile.entities.Usuario;
import br.com.justgeek.mobile.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

public class Authenticated {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public Authenticated(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    public Boolean authenticate(int idUsuario){
        Optional<Usuario> conectado = usuarioRepository.findById(idUsuario);

        return conectado.isPresent() && conectado.get().getAutenticado();
    }
}
