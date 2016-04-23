package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Entrevista;
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
public class EntrevistaService extends Service<Entrevista>{

    public EntrevistaService() {
        classRef = Entrevista.class;
    }
    
    @Transactional
    public List<Entrevista> findForTable(TableRequest request) {

        
        String hql = "select t from Entrevista t ";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Entrevista> l = q.getResultList();
        return l;
    }
    

}