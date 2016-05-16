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

    @Override
    public CurriculoExperiencia findById(Object id) {
        String hql = "select t from CurriculoExperiencia t "
                + " inner join fetch t.pessoa p "
                + " where p.id = :id";
        CurriculoExperiencia ce = (CurriculoExperiencia) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult();

        return ce;
    }
    
    public List<CurriculoExperiencia> findForPessoa(int pessoaId) {
        String hql = "from CurriculoExperiencia t "
                + " inner join fetch t.pessoa p "
                + " where p.id = :id and p is not EMPTY";
        return entityManager.createQuery(hql)
                .setParameter("id", pessoaId)
                .getResultList();
    }

    public CurriculoExperienciaService() {
        classRef = CurriculoExperiencia.class;
    }
    
    @Transactional
    public List<CurriculoExperiencia> findForTable(TableRequest request) {

        
        String hql = "select t from CurriculoExperiencia t "
                + "inner join fetch t.pessoa p";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoExperiencia> l = q.getResultList();
        return l;
    }
    

}