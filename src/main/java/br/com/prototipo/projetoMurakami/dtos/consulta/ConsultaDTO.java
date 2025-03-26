package br.com.prototipo.projetoMurakami.dtos.consulta;

import br.com.prototipo.projetoMurakami.domain.enuns.StatusConsulta;
import br.com.prototipo.projetoMurakami.domain.enuns.StatusSolicitacao;

import java.time.LocalDateTime;
import java.util.Date;

public record ConsultaDTO(LocalDateTime dataConsulta,
                          String observacao) {}