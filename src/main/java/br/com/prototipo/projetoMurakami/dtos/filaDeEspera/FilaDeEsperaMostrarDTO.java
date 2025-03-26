package br.com.prototipo.projetoMurakami.dtos.filaDeEspera;

import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;

public record FilaDeEsperaMostrarDTO(
        Long id,
        TiposDeFila tipoFila,
        Long formularioId
) {}