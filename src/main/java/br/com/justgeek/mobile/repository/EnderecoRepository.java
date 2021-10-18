package br.com.justgeek.mobile.repository;

import br.com.justgeek.mobile.entities.Endereco;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EnderecoRepository extends JpaRepository<Endereco, Integer> {

    Optional<Endereco> findByIdEndereco(int idEndereco);

    List<Endereco> findByFkUsuarioIdUsuario(int idUsuario);

    int countByFkUsuarioIdUsuario(int idUsuario);
}
