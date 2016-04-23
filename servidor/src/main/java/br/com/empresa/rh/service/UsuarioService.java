package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo
 */
@Repository
public class UsuarioService extends Service<Usuario>{

    public UsuarioService() {
        classRef = Usuario.class;
    }
    
    @Transactional
    public List<Usuario> findForTable(TableRequest request) {

        
        String hql = "select t from Usuario t ";
        hql+= request.applyFilter("id","usuario");     
        hql+= request.applyOrder("id","usuario");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Usuario> l = q.getResultList();
        return l;
    }
    

}