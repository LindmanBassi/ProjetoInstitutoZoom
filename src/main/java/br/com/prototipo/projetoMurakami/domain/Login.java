package br.com.prototipo.projetoMurakami.domain;

import br.com.prototipo.projetoMurakami.domain.enuns.TipoConta;

import jakarta.persistence.*;
import lombok.*;

import java.util.UUID;

@Entity
@Table(name = "tb_logins")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Login {

    @Id
    @Column(name = "login_id")
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    @Column(name = "email",nullable = false, unique = true)
    private String email;

    @Column(name = "senha",nullable = false)
    private String senha;

    @Enumerated(EnumType.STRING)
    @Column(name = "tipo_conta",nullable = false)
    private TipoConta tipoConta;

    @OneToOne(mappedBy = "login")
    private Usuario usuario;
}
