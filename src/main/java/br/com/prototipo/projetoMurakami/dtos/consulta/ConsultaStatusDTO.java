package br.com.prototipo.projetoMurakami.dtos.consulta;

import br.com.prototipo.projetoMurakami.domain.enuns.StatusConsulta;

public record ConsultaStatusDTO(
        Long id,
        StatusConsulta status
) {}