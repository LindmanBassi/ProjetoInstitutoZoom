package br.com.prototipo.projetoMurakami.repositories;

import br.com.prototipo.projetoMurakami.domain.Consulta;
import br.com.prototipo.projetoMurakami.domain.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Date;
import java.util.List;

public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    List<Consulta> findAllByUsuario(Usuario usuario);

}
