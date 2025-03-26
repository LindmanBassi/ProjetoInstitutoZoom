package br.com.prototipo.projetoMurakami.services;

import br.com.prototipo.projetoMurakami.domain.Login;
import br.com.prototipo.projetoMurakami.domain.UsuarioPrincipal;
import br.com.prototipo.projetoMurakami.repositories.LoginRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private LoginRepository loginRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Login login = loginRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário de email: " + email + " não encontrado"));
        return new UsuarioPrincipal(login);
    }
}