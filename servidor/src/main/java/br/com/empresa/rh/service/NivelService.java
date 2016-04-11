package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Nivel;
import org.springframework.stereotype.Repository;

@Repository
public class NivelService extends Service<Nivel>{

    public NivelService() {
        classRef = Nivel.class;
    }
}
