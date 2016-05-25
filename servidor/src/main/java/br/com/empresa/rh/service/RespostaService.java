package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Resposta;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo.michels
 */
@Repository
public class RespostaService extends Service<Resposta>{

    public RespostaService() {
        classRef = Resposta.class;
    }
    
    @Transactional
    public List<Resposta> findForTable(TableRequest request) {

        
        String hql = "select t from Resposta t ";
        hql+= request.applyFilter("id","descricao");     
        hql+= request.applyOrder("id","descricao");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Resposta> l = q.getResultList();
        return l;
    }
    

}