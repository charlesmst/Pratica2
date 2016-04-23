package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Cidade;
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
public class CidadeService extends Service<Cidade>{

    public CidadeService() {
        classRef = Cidade.class;
    }
    
    @Transactional
    public List<Cidade> findForTable(TableRequest request) {

        
        String hql = "select t from Cidade t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Cidade> l = q.getResultList();
        return l;
    }
    

}