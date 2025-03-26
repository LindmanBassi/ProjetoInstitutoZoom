package br.com.prototipo.projetoMurakami.repositories;

import br.com.prototipo.projetoMurakami.domain.Login;
import br.com.prototipo.projetoMurakami.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface UsuarioRepository extends JpaRepository<Usuario, UUID> {

    Optional<Usuario> findUsuarioByLogin(Login login);


}
