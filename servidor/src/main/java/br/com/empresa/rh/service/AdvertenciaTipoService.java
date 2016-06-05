package br.com.empresa.rh.service;

import br.com.empresa.rh.model.AdvertenciaTipo;
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
public class AdvertenciaTipoService extends Service<AdvertenciaTipo> {

    public AdvertenciaTipoService() {
        classRef = AdvertenciaTipo.class;
    }

    @Transactional
    public List<AdvertenciaTipo> findForTable(TableRequest request) {

        String hql = "select t from AdvertenciaTipo t ";
        if (request != null) {
            hql += request.applyFilter("id", "nome");
            hql += request.applyOrder("id", "nome");
        }
        Query q = entityManager.createQuery(hql);
        if (request != null) {
            request.applyPagination(q);
            request.applyParameters(q);
        }
        List<AdvertenciaTipo> l = q.getResultList();
        return l;
    }

}
