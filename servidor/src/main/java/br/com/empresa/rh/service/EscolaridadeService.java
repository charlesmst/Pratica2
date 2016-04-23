package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Escolaridade;
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
public class EscolaridadeService extends Service<Escolaridade>{

    public EscolaridadeService() {
        classRef = Escolaridade.class;
    }
    
    @Transactional
    public List<Escolaridade> findForTable(TableRequest request) {

        
        String hql = "select t from Escolaridade t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Escolaridade> l = q.getResultList();
        return l;
    }
    

}