package br.com.empresa.rh.service;


import br.com.empresa.rh.model.FuncionarioAcidente;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Bottega  - dl.bottega@hotmail.com  
 */
@Repository
public class FuncionarioAcidenteService extends Service<FuncionarioAcidente>{

    public FuncionarioAcidenteService() {
        classRef = FuncionarioAcidente.class;
    }
    
    @Transactional
    public List<FuncionarioAcidente> findForTable(TableRequest request) {

        
        String hql = "select t from FuncionarioAcidente t "
                + "inner join fetch t.funcionarioCargo fc ";
        hql+= request.applyFilter("t.id","t.decricao");     
        hql+= request.applyOrder("t.id","t.descricao");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<FuncionarioAcidente> l = q.getResultList();
        return l;
    }
    

}