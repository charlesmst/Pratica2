package br.com.empresa.rh.service;


import br.com.empresa.rh.model.CurriculoQualificacao;
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
public class CurriculoQualificacaoService extends Service<CurriculoQualificacao>{

    public CurriculoQualificacaoService() {
        classRef = CurriculoQualificacao.class;
    }
    
    @Transactional
    public List<CurriculoQualificacao> findForTable(TableRequest request) {

        
        String hql = "select t from CurriculoQualificacao t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoQualificacao> l = q.getResultList();
        return l;
    }
    

}