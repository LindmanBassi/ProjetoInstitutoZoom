package br.com.prototipo.projetoMurakami.controller;

import br.com.prototipo.projetoMurakami.dtos.consulta.ConsultaDTO;
import br.com.prototipo.projetoMurakami.dtos.consulta.ConsultaMostrarDTO;
import br.com.prototipo.projetoMurakami.services.ConsultaService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("/consulta")
@Tag(name = "Consulta Controller")
@RequiredArgsConstructor
public class ConsultaController {

    private final ConsultaService consultaService;

    @Operation(
            summary = "Agendar Consulta",
            description = "Endpoint para agendar"
    )
    @PostMapping
    public ResponseEntity<Void> agendarConsulta(@RequestBody ConsultaDTO dto) {
        var consulta =  consultaService.agendarConsulta(dto);
        return ResponseEntity.created(URI.create("/consulta/" + consulta.getId())).build();
    }

    @Operation(
            summary = "Listar Todas Consultas",
            description = "Endpoint para listar as consultas"
    )
    @GetMapping
    public ResponseEntity<List<ConsultaMostrarDTO>> listarTodasConsulta(){
        return ResponseEntity.ok(consultaService.listarConsultas());
    }

    @Operation(
            summary = "Lista Consulta do Cliente",
            description = "Endpoint listar as consultas de um cliente"
    )
    @GetMapping("/cliente")
    public ResponseEntity<List<ConsultaMostrarDTO>> listarTodasConsultaDoCliente(){
        return ResponseEntity.ok(consultaService.listarConsultasCliente());
    }

    @Operation(
            summary = "Deletar Consulta",
            description = "Endpoint para excluir uma consulta"
    )
    @DeleteMapping("/{consultaId}")
    public ResponseEntity<Void> cancelarConsulta(@PathVariable Long consultaId) {
        consultaService.cancelarConsulta(consultaId);
        return ResponseEntity.noContent().build();
    }

}
