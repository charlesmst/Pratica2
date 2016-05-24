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

    @Override
    public CurriculoFormacao findById(Object id) {
        String hql = "select t from CurriculoFormacao t "
                + " inner join fetch t.pessoa p "
                + " inner join fetch t.escolaridade "
                + " where p.id = :id";
        CurriculoFormacao cf = (CurriculoFormacao) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult();

        return cf;
    }
    
    public List<CurriculoFormacao> findForPessoa (int pessoaId) {
        String hql = "from CurriculoFormacao t "
                + " inner join fetch t.pessoa p "
                + " inner join fetch t.escolaridade "
                + " where p.id = :id and p is not EMPTY";
        return entityManager.createQuery(hql)
                .setParameter("id", pessoaId)
                .getResultList();
    }
    
    public CurriculoFormacaoService() {
        classRef = CurriculoFormacao.class;
    }
    
    @Transactional
    public List<CurriculoFormacao> findForTable(TableRequest request) {

        
        String hql = "select t from CurriculoFormacao t "
                + " inner join fetch t.pessoa p "
                + " inner join fetch t.escolaridade";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoFormacao> l = q.getResultList();
        return l;
    }
    

}