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
        String hql = "select t from Candidato t "
                + " left join fetch t.pessoa p "
                + " left join fetch t.vagas v "
                + " left join fetch v.planoAvaliacao pa "
                + " left join fetch pa.questaos"
                + " left join fetch v.cargo "
                + " left join fetch t.respostas r "
                + " left join fetch t.competencias c "
                + " left join fetch t.entrevistas e";
        hql+= request.applyFilter("t.id", "p.nome");     
        hql+= request.applyOrder("t.id", "p.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Candidato> l = q.getResultList();
        return l;
    }
    

}