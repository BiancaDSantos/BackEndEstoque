package br.com.senac.BackEndEstoque.usuario;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Integer> {
    boolean existsByLogin(String login);

    Optional<Usuario> findByLogin(String login);
}
