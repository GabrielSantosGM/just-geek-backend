package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {

    Optional<Usuario> findByIdUsuario(int id);

    Optional<Usuario> findByIdUsuarioAndAutenticadoTrue(int idUsuario);

    Optional<Usuario> findByEmailAndSenha(String email, String senha);

    Optional<Usuario> findByEmail(String email);

    List<Usuario> findBySenha(String senha);
}
