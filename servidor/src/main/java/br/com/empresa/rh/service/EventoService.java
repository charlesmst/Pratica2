package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author charles
 */
@Repository
public class EventoService extends Service<Evento>{

    public EventoService() {
        classRef = Evento.class;
    }
    
    @Transactional
    public List<Evento> findForTable(TableRequest request) {

        
        String hql = "select t from Evento t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Evento> l = q.getResultList();
        return l;
    }
    

}