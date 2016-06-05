package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Qualificacao;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Bottega - dl.bottega@hotmail.com
 */
@Repository
public class QualificacaoService extends Service<Qualificacao> {

    public QualificacaoService() {
        classRef = Qualificacao.class;
    }

    @Transactional
    public List<Qualificacao> findForTable(TableRequest request) {

        String hql = "select t from Qualificacao t ";
        if (request != null) {
            hql += request.applyFilter("id", "nome");
            hql += request.applyOrder("id", "nome");
        }
        Query q = entityManager.createQuery(hql);
        if (request != null) {
            request.applyPagination(q);
            request.applyParameters(q);
        }
        List<Qualificacao> l = q.getResultList();
        return l;
    }

}
