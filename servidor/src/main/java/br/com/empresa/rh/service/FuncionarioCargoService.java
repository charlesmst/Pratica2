package br.com.empresa.rh.service;

import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class FuncionarioCargoService extends Service<FuncionarioCargo> {

    public FuncionarioCargoService() {
        classRef = FuncionarioCargo.class;
    }

    public List<FuncionarioCargo> findByFuncionario(int idFuncionario) {
        return entityManager.createQuery("select t from FuncionarioCargo t "
                + "left join fetch t.funcionario f "
                + "left join fetch f.pessoa p "
                + "left join fetch t.cargo c "
                + "  where f.id = :id").setParameter("id", idFuncionario).getResultList();
    }

    @Transactional
    public List<FuncionarioCargo> findForTable(TableRequest request) {

        String hql = "select t from FuncionarioCargo t "
                + "left join fetch t.funcionario f "
                + "left join fetch f.pessoa p "
                + "left join fetch t.cargo c ";
        hql += request.applyFilter("t.id", "p.nome", "c.nome");
        hql += request.applyOrder("id", "nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<FuncionarioCargo> l = q.getResultList();
        for (FuncionarioCargo l1 : l) {
            l1.getFuncionario().getPessoa().setFuncionario(null);
        }
        return l;
    }

}
