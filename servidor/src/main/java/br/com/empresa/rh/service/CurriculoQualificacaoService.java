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
public class CurriculoQualificacaoService extends Service<CurriculoQualificacao> {

    @Override
    public CurriculoQualificacao findById(Object id) {
        String hql = "select t from CurriculoQualificacao t "
                + " inner join fetch t.pessoa p "
                + " where p.id = :id";
        CurriculoQualificacao cq = (CurriculoQualificacao) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult();

        return cq;
    }

    public List<CurriculoQualificacao> findForPessoa(int pessoaId) {
        String hql = "from CurriculoQualificacao t "
                + " inner join fetch t.pessoa p "
                + " where p.id = :id and p is not EMPTY";
        return entityManager.createQuery(hql)
                .setParameter("id", pessoaId)
                .getResultList();
    }

    public CurriculoQualificacaoService() {
        classRef = CurriculoQualificacao.class;
    }

    @Transactional
    public List<CurriculoQualificacao> findForTable(TableRequest request) {

        String hql = "select t from CurriculoQualificacao t "
                + " inner join fetch t.pessoa p";
        hql += request.applyFilter("id", "descricao");
        hql += request.applyOrder("id", "descricao");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CurriculoQualificacao> l = q.getResultList();
        return l;
    }

}
