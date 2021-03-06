package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Nivel;
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
public class NivelService extends Service<Nivel>{

    public NivelService() {
        classRef = Nivel.class;
    }
    
    @Transactional
    public List<Nivel> findForTable(TableRequest request) {

        
        String hql = "select t from Nivel t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Nivel> l = q.getResultList();
        return l;
    }
    

}