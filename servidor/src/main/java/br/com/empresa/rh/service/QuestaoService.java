package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Questao;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gusta
 */
@Repository
public class QuestaoService extends Service<Questao>{

    public QuestaoService() {
        classRef = Questao.class;
    }
    
    @Transactional
    public List<Questao> findForTable(TableRequest request) {

        
        String hql = "select t from Questao t ";
        hql+= request.applyFilter("id","descricao");     
        hql+= request.applyOrder("id","descricao");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Questao> l = q.getResultList();
        return l;
    }
    

}