package br.com.empresa.rh.service;


import br.com.empresa.rh.model.DemissaoTipo;
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
public class DemissaoTipoService extends Service<DemissaoTipo>{

    public DemissaoTipoService() {
        classRef = DemissaoTipo.class;
    }
    
    @Transactional
    public List<DemissaoTipo> findForTable(TableRequest request) {

        
        String hql = "select t from DemissaoTipo t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<DemissaoTipo> l = q.getResultList();
        return l;
    }
    

}