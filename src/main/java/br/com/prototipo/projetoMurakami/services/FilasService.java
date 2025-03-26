package br.com.prototipo.projetoMurakami.services;

import br.com.prototipo.projetoMurakami.domain.enuns.TiposDeFila;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FilasService {

    public List<String> listarFilas() {
        return List.of(TiposDeFila.SUS.toString(), TiposDeFila.CONVENIO.toString());
    }

}
