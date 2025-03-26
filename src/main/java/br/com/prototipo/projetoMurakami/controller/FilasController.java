package br.com.prototipo.projetoMurakami.controller;

import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import br.com.prototipo.projetoMurakami.services.FilasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/filas")
@Tag(name = "Fila Controller")
public class FilasController {

    private final FilasService filasService;

    @Operation(
            summary = "Listar Filas",
            description = "Endpoint para listar todas as filas"
    )
    @GetMapping
    public ResponseEntity<List<String>> listarFilas() {
        return ResponseEntity.ok().body(filasService.listarFilas());
    }
}
