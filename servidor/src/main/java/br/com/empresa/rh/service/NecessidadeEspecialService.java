package br.com.empresa.rh.service;


import br.com.empresa.rh.model.NecessidadeEspecial;
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
public class NecessidadeEspecialService extends Service<NecessidadeEspecial>{

    public NecessidadeEspecialService() {
        classRef = NecessidadeEspecial.class;
    }
    
    @Transactional
    public List<NecessidadeEspecial> findForTable(TableRequest request) {

        
        String hql = "select t from NecessidadeEspecial t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<NecessidadeEspecial> l = q.getResultList();
        return l;
    }
    

}