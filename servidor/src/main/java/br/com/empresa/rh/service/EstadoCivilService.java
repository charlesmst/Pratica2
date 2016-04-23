package br.com.empresa.rh.service;


import br.com.empresa.rh.model.EstadoCivil;
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
public class EstadoCivilService extends Service<EstadoCivil>{

    public EstadoCivilService() {
        classRef = EstadoCivil.class;
    }
    
    @Transactional
    public List<EstadoCivil> findForTable(TableRequest request) {

        
        String hql = "select t from EstadoCivil t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<EstadoCivil> l = q.getResultList();
        return l;
    }
    

}