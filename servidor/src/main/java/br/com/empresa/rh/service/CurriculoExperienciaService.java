package br.com.empresa.rh.service;


import br.com.empresa.rh.model.CurriculoExperiencia;
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
public class CurriculoExperienciaService extends Service<CurriculoExperiencia>{

    public CurriculoExperienciaService() {
        classRef = CurriculoExperiencia.class;
    }
    
    @Transactional
    public List<CurriculoExperiencia> findForTable(TableRequest request) {

        
        String hql = "select t from CurriculoExperiencia t ";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoExperiencia> l = q.getResultList();
        return l;
    }
    

}