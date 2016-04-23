package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Curriculo;
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
public class CurriculoService extends Service<Curriculo>{

    public CurriculoService() {
        classRef = Curriculo.class;
    }
    
    @Transactional
    public List<Curriculo> findForTable(TableRequest request) {

        
        String hql = "select t from Curriculo t ";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Curriculo> l = q.getResultList();
        return l;
    }
    

}