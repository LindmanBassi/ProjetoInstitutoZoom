package br.com.prototipo.projetoMurakami.utils;

import br.com.prototipo.projetoMurakami.domain.Usuario;
import br.com.prototipo.projetoMurakami.dtos.login.UsuarioDTO;
import br.com.prototipo.projetoMurakami.repositories.UsuarioRepository;
import br.com.prototipo.projetoMurakami.services.UsuarioService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class UsuarioUtil {

    private final UsuarioService usuarioService;

    public UsuarioDTO getUsuario() {
        var usuario = usuarioService.findByEmail(getUsuarioLogado());
        return new UsuarioDTO(usuario.getNomeCompleto(),usuario.getLogin().getTipoConta().toString());
    }


    public String getUsuarioLogado() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        if (authentication != null && authentication.getPrincipal() instanceof UserDetails) {
            return ((UserDetails) authentication.getPrincipal()).getUsername();
        }
        return null;
    }
}
