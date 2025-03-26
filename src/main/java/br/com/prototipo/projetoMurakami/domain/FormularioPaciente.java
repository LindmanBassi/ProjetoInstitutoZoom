package br.com.prototipo.projetoMurakami.domain;

import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Table(name = "tb_formularios_pacientes")
@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FormularioPaciente {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "formulario_id")
    private Long id;

    @NotBlank(message = "O nome é obrigatório")
    @Column(name = "nome", nullable = false)
    private String nome;

    @Column(name = "cpf", unique = true, nullable = false)
    private String cpf;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipoFila")
    private TiposDeFila tipoFila;
}
