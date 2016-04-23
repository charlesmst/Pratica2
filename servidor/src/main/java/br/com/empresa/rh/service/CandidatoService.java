package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Candidato;
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
public class CandidatoService extends Service<Candidato>{

    public CandidatoService() {
        classRef = Candidato.class;
    }
    
    @Transactional
    public List<Candidato> findForTable(TableRequest request) {

        
        String hql = "select t from Candidato t ";
        hql+= request.applyFilter("id","t.pessoa.nome");     
        hql+= request.applyOrder("id","t.pessoa.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Candidato> l = q.getResultList();
        return l;
    }
    

}