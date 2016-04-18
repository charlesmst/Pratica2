package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Tabelas;
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
public class TabelasService extends Service<Tabelas>{

    public TabelasService() {
        classRef = Tabelas.class;
    }
    
    @Transactional
    public List<Tabelas> findForTable(TableRequest request) {

        
        String hql = "select t from Tabelas t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Tabelas> l = q.getResultList();
        return l;
    }
    

}