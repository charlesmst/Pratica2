package br.com.empresa.rh.service;


import br.com.empresa.rh.model.PlanoAvaliacao;
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
public class PlanoAvaliacaoService extends Service<PlanoAvaliacao>{

    public PlanoAvaliacaoService() {
        classRef = PlanoAvaliacao.class;
    }
    
    @Transactional
    public List<PlanoAvaliacao> findForTable(TableRequest request) {

        
        String hql = "select t from PlanoAvaliacao t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<PlanoAvaliacao> l = q.getResultList();
        return l;
    }
    

}