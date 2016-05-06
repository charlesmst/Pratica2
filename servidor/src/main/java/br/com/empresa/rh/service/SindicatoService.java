package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Sindicato;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Bottega  - dl.bottega@hotmail.com  
 */
@Repository
public class SindicatoService extends Service<Sindicato>{

    public SindicatoService() {
        classRef = Sindicato.class;
    }
    
    @Transactional
    public List<Sindicato> findForTable(TableRequest request) {

        
        String hql = "select t from Sindicato t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Sindicato> l = q.getResultList();
        return l;
    }
    

}