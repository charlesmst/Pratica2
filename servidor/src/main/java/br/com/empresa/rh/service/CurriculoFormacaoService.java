package br.com.empresa.rh.service;


import br.com.empresa.rh.model.CurriculoFormacao;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo.michels
 */
@Repository
public class CurriculoFormacaoService extends Service<CurriculoFormacao>{

    public CurriculoFormacaoService() {
        classRef = CurriculoFormacao.class;
    }
    
    @Transactional
    public List<CurriculoFormacao> findForTable(TableRequest request) {

        
        String hql = "select t from CurriculoFormacao t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoFormacao> l = q.getResultList();
        return l;
    }
    

}