package br.com.prototipo.projetoMurakami.repositories;

import br.com.prototipo.projetoMurakami.domain.FormularioPaciente;
import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FormularioPacienteRepository extends JpaRepository<FormularioPaciente, Long> {


    List<FormularioPaciente> findAllByTipoFila(TiposDeFila tiposDeFila);
}
