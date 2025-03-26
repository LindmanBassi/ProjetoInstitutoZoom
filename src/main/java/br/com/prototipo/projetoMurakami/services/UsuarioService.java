package br.com.prototipo.projetoMurakami.services;

import br.com.prototipo.projetoMurakami.domain.Login;
import br.com.prototipo.projetoMurakami.domain.Usuario;
import br.com.prototipo.projetoMurakami.domain.enuns.TipoConta;
import br.com.prototipo.projetoMurakami.dtos.login.CriarContaDTO;
import br.com.prototipo.projetoMurakami.exceptions.UserNotFoundException;
import br.com.prototipo.projetoMurakami.repositories.LoginRepository;
import br.com.prototipo.projetoMurakami.repositories.UsuarioRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UsuarioService {

    private final UsuarioRepository usuarioRepository;
    private final LoginRepository loginRepository;
    private final PasswordEncoder passwordEncoder;


    public Usuario criarConta(CriarContaDTO dto, TipoConta tipoConta) {
        Login login = new Login();
        login.setEmail(dto.email());
        login.setSenha(passwordEncoder.encode(dto.senha()));
        login.setTipoConta(tipoConta);
        var createdLogin = loginRepository.save(login);

        Usuario usuario = new Usuario();

        usuario.setNomeCompleto(dto.nomeCompleto());
        usuario.setTelefone(dto.telefone());
        usuario.setLogin(createdLogin);
        return usuarioRepository.save(usuario);
    }

    public Optional<Usuario> findByEmailAndSenha(String email, String senha) {
        return loginRepository.findByEmail(email)
                .filter(login -> passwordEncoder.matches(senha, login.getSenha()))
                .flatMap(usuarioRepository::findUsuarioByLogin);
    }

    public Usuario findByEmail(String email) {
        var loginOptional = loginRepository.findByEmail(email);

        if (loginOptional.isPresent()) {
            var login = loginOptional.get();
            var usuario = usuarioRepository.findUsuarioByLogin(login);
            return usuario.get();
        }
        throw new UserNotFoundException("Usuário não encontrado.");
    }

}
