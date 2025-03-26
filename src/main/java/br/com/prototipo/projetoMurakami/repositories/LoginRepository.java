package br.com.prototipo.projetoMurakami.repositories;

import br.com.prototipo.projetoMurakami.domain.Login;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface LoginRepository extends JpaRepository<Login, UUID> {
    Optional<Login> findByEmail(String email);
}