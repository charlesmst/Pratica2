package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Competencia;
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
public class CompetenciaService extends Service<Competencia> {

    public CompetenciaService() {
        classRef = Competencia.class;
    }

    @Transactional
    public List<Competencia> findForTable(TableRequest request) {

        String hql = "select t from Competencia t ";
        hql += request.applyFilter("id", "descricao");
        hql += request.applyOrder("id", "descricao");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Competencia> l = q.getResultList();
        return l;
    }

}
