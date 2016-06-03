package br.com.empresa.rh.service;


import br.com.empresa.rh.model.TipoSanguineo;
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
public class TipoSanguineoService extends Service<TipoSanguineo>{

    public TipoSanguineoService() {
        classRef = TipoSanguineo.class;
    }
    
    @Transactional
    public List<TipoSanguineo> findForTable(TableRequest request) {

        
        String hql = "select t from TipoSanguineo t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<TipoSanguineo> l = q.getResultList();
        return l;
    }
    

}