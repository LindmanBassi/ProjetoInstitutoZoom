package br.com.prototipo.projetoMurakami.dtos.consulta;

import java.time.LocalDateTime;

public record SolicitarConsultaDTO(String email,
                                   LocalDateTime horarioConsulta) {
}
