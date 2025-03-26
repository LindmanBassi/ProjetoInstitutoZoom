package br.com.prototipo.projetoMurakami.services;

import br.com.prototipo.projetoMurakami.domain.Consulta;
import br.com.prototipo.projetoMurakami.domain.enuns.StatusConsulta;
import br.com.prototipo.projetoMurakami.domain.enuns.StatusSolicitacao;
import br.com.prototipo.projetoMurakami.dtos.consulta.ConsultaDTO;
import br.com.prototipo.projetoMurakami.dtos.consulta.ConsultaMostrarDTO;
import br.com.prototipo.projetoMurakami.repositories.ConsultaRepository;
import br.com.prototipo.projetoMurakami.utils.UsuarioUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ConsultaService {

    private final ConsultaRepository consultaRepository;
    private final UsuarioService usuarioService;
    private final UsuarioUtil usuarioUtil;

    public Consulta agendarConsulta(ConsultaDTO dto) {
        var usuario = usuarioService.findByEmail(usuarioUtil.getUsuarioLogado());

        Consulta consulta = new Consulta();
        consulta.setUsuario(usuario);
        consulta.setDataConsulta(dto.dataConsulta());
        consulta.setDataSolicitacao(LocalDateTime.now());
        consulta.setObservacao(dto.observacao());
        consulta.setStatusConsulta(StatusConsulta.AGENDADA);

        return consultaRepository.save(consulta);
    }

    public List<ConsultaMostrarDTO> listarConsultas(){
        var consultas = consultaRepository.findAll();
        List<ConsultaMostrarDTO> consultasMostrar = new ArrayList<>();
        consultas.forEach(consulta -> {
            var mostrarConsulta = new ConsultaMostrarDTO(consulta.getId()
                    ,consulta.getUsuario().getNomeCompleto(),
                    consulta.getUsuario().getTelefone(),
                    consulta.getStatusConsulta(),
                    consulta.getDataConsulta(),
                    consulta.getDataSolicitacao(),
                    consulta.getObservacao());
            consultasMostrar.add(mostrarConsulta);
        });

        return consultasMostrar;
    }

    public List<ConsultaMostrarDTO> listarConsultasCliente(){


        var usuario = usuarioService.findByEmail(usuarioUtil.getUsuarioLogado());

        var consultas = consultaRepository.findAllByUsuario(usuario);
        List<ConsultaMostrarDTO> consultasMostrar = new ArrayList<>();
        consultas.forEach(consulta -> {
            var mostrarConsulta = new ConsultaMostrarDTO(consulta.getId(),
                    consulta.getUsuario().getNomeCompleto(),
                    consulta.getUsuario().getTelefone(),
                    consulta.getStatusConsulta(),
                    consulta.getDataConsulta(),
                    consulta.getDataSolicitacao(),
                    consulta.getObservacao());
            consultasMostrar.add(mostrarConsulta);
        });

        return consultasMostrar;
    }

    public void cancelarConsulta(Long consultaId) {

        var consulta = consultaRepository.findById(consultaId).orElseThrow(() -> new RuntimeException("Consulta não encontrada"));
        consulta.setStatusConsulta(StatusConsulta.CANCELADA);
        consultaRepository.delete(consulta);
    }
}
