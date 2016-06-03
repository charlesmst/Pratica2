package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Banco;
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
public class BancoService extends Service<Banco>{

    public BancoService() {
        classRef = Banco.class;
    }
    
    @Transactional
    public List<Banco> findForTable(TableRequest request) {

        
        String hql = "select t from Banco t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Banco> l = q.getResultList();
        return l;
    }
    

}