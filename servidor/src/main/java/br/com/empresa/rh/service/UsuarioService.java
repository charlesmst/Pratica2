package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Usuario;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.ws.rs.QueryParam;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class UsuarioService extends Service<Usuario> {

    public UsuarioService() {
        classRef = Usuario.class;
    }


   
//
//    public List<Usuario> findFilter(@QueryParam("filter") String filter, @QueryParam("order") String order, @QueryParam("limit") int limit, @QueryParam("page") int page) {
//        entityManager.create
//    }

}
