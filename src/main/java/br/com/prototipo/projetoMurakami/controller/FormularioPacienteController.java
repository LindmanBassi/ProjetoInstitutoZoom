package br.com.prototipo.projetoMurakami.controller;

import br.com.prototipo.projetoMurakami.domain.FormularioPaciente;
import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import br.com.prototipo.projetoMurakami.dtos.formularioPaciente.FormularioPacienteDTO;
import br.com.prototipo.projetoMurakami.services.FormularioPacienteService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/formulario")
@RequiredArgsConstructor
@Tag(name = "Formulario Controller")
public class FormularioPacienteController {

    private final FormularioPacienteService formularioPacienteService;

    @Operation(
            summary = "Cadastrar formulario",
            description = "Endpoint para cadastrar um formulario"
    )
    @PostMapping
    public ResponseEntity<Void> cadastrarFormulario(@RequestBody FormularioPacienteDTO dto,
                                                    @RequestParam TiposDeFila tipoFila){
        formularioPacienteService.cadastrarFormulario(dto,tipoFila);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Listar Formularios",
            description = "Endpoint para listar todos formularios"
    )
    @GetMapping
    public ResponseEntity<List<FormularioPaciente>> listarFormularios(@RequestParam(required = false) TiposDeFila tipoFila){
        var formularios = formularioPacienteService.buscarFormularios(tipoFila);
        return ResponseEntity.ok().body(formularios);
    }

    @Operation(
            summary = "Atualizar Formulario",
            description = "Endpoint para agendar"
    )
    @PutMapping
    public ResponseEntity<Void> atualizarFormulario(@RequestBody FormularioPacienteDTO dto,
                                                    @RequestParam TiposDeFila tipoFila,
                                                    @RequestParam Long formularioId){
        formularioPacienteService.atualizarFormulario(formularioId,dto,tipoFila);
        return ResponseEntity.ok().build();
    }

    @Operation(
            summary = "Excluir Formulario",
            description = "Endpoint para excluir um formulario"
    )
    @DeleteMapping
    public ResponseEntity<Void> excluirFormulario(@RequestParam Long formularioId){
        formularioPacienteService.excluirFormulario(formularioId);
        return ResponseEntity.ok().build();
    }

}