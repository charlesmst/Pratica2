package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Cor;
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
public class CorService extends Service<Cor>{

    public CorService() {
        classRef = Cor.class;
    }
    
    @Transactional
    public List<Cor> findForTable(TableRequest request) {

        
        String hql = "select t from Cor t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Cor> l = q.getResultList();
        return l;
    }
    

}