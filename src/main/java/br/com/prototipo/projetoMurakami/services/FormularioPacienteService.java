package br.com.prototipo.projetoMurakami.services;

import br.com.prototipo.projetoMurakami.domain.FormularioPaciente;
import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import br.com.prototipo.projetoMurakami.dtos.formularioPaciente.FormularioPacienteDTO;
import br.com.prototipo.projetoMurakami.repositories.FormularioPacienteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

import static org.springframework.util.StringUtils.hasText;

@Service
@RequiredArgsConstructor
public class FormularioPacienteService {

    private final FormularioPacienteRepository formularioPacienteRepository;

    public void cadastrarFormulario(FormularioPacienteDTO dto, TiposDeFila tipoFila) {
        FormularioPaciente formularioPaciente = new FormularioPaciente();
        formularioPaciente.setCpf(dto.cpf());
        formularioPaciente.setNome(dto.nome());
        formularioPaciente.setTipoFila(tipoFila);

        formularioPacienteRepository.save(formularioPaciente);
    }

    public void atualizarFormulario(Long formularioId,FormularioPacienteDTO dto, TiposDeFila tipoFila) {
        var formularioPaciente = formularioPacienteRepository.findById(formularioId).orElseThrow(() -> new RuntimeException("Formulario não encontrado"));
        if(hasText(formularioPaciente.getCpf())){
            formularioPaciente.setCpf(dto.cpf());
        }
        if(hasText(formularioPaciente.getNome())){
            formularioPaciente.setNome(dto.nome());
        }
        if(tipoFila != null){
            formularioPaciente.setTipoFila(tipoFila);
        }

        formularioPacienteRepository.save(formularioPaciente);
    }

    public List<FormularioPaciente> buscarFormularios(TiposDeFila tipoFila) {
        if(tipoFila != null){
            return formularioPacienteRepository.findAllByTipoFila(tipoFila);
        }
        return formularioPacienteRepository.findAll();
    }

    public void excluirFormulario(Long formularioId) {
        var formulario = formularioPacienteRepository.findById(formularioId).orElseThrow(() -> new RuntimeException("Formulario não encontrado"));
        formularioPacienteRepository.delete(formulario);
    }
}
