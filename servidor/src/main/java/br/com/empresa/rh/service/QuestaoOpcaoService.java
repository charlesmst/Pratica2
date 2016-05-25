package br.com.empresa.rh.service;


import br.com.empresa.rh.model.QuestaoOpcao;
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
public class QuestaoOpcaoService extends Service<QuestaoOpcao>{

    public QuestaoOpcaoService() {
        classRef = QuestaoOpcao.class;
    }
    
    @Transactional
    public List<QuestaoOpcao> findForTable(TableRequest request) {

        
        String hql = "select t from QuestaoOpcao t ";
        hql+= request.applyFilter("id","texto");     
        hql+= request.applyOrder("id","texto");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<QuestaoOpcao> l = q.getResultList();
        return l;
    }
}