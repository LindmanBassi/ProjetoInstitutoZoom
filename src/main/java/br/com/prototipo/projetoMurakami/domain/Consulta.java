package br.com.prototipo.projetoMurakami.domain;

import br.com.prototipo.projetoMurakami.domain.enuns.StatusConsulta;
import br.com.prototipo.projetoMurakami.domain.enuns.StatusSolicitacao;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.data.annotation.CreatedDate;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Table(name = "tb_consultas")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Consulta {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;

    @Column(name = "data_consulta")
    private LocalDateTime dataConsulta;

    @Column(name = "data_solicitacao")
    private LocalDateTime dataSolicitacao;

    @Column(name = "observacao")
    private String observacao;

    @Enumerated(EnumType.STRING)
    @Column(name = "status_consulta")
    private StatusConsulta statusConsulta;

}