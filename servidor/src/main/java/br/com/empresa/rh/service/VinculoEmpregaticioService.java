package br.com.empresa.rh.service;


import br.com.empresa.rh.model.VinculoEmpregaticio;
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
public class VinculoEmpregaticioService extends Service<VinculoEmpregaticio>{

    public VinculoEmpregaticioService() {
        classRef = VinculoEmpregaticio.class;
    }
    
    @Transactional
    public List<VinculoEmpregaticio> findForTable(TableRequest request) {

        
        String hql = "select t from VinculoEmpregaticio t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<VinculoEmpregaticio> l = q.getResultList();
        return l;
    }
    

}