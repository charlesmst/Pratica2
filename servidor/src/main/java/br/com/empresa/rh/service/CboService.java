package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Cbo;
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
public class CboService extends Service<Cbo>{

    public CboService() {
        classRef = Cbo.class;
    }
    
    @Transactional
    public List<Cbo> findForTable(TableRequest request) {

        
        String hql = "select t from Cbo t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Cbo> l = q.getResultList();
        return l;
    }
    

}