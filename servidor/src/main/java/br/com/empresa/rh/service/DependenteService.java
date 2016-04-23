package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Dependente;
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
public class DependenteService extends Service<Dependente>{

    public DependenteService() {
        classRef = Dependente.class;
    }
    
    @Transactional
    public List<Dependente> findForTable(TableRequest request) {

        
        String hql = "select t from Dependente t ";
        hql+= request.applyFilter("id","t.pessoa.nome");     
        hql+= request.applyOrder("id","t.pessoa.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Dependente> l = q.getResultList();
        return l;
    }
    

}