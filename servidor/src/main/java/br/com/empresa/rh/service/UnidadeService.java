package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Unidade;
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
public class UnidadeService extends Service<Unidade>{

    public UnidadeService() {
        classRef = Unidade.class;
    }
    
    @Transactional
    public List<Unidade> findForTable(TableRequest request) {

        
        String hql = "select t from Unidade t "
                + " inner join fetch t.empresa ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Unidade> l = q.getResultList();
        for (Unidade l1 : l) {
            l1.getEmpresa().setUnidades(null);
        }
        return l;
    }
    

}