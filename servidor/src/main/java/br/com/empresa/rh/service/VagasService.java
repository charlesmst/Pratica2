package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Vagas;
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
public class VagasService extends Service<Vagas> {

    @Override
    public Vagas findById(Object id) {
        String hql = "select t from Vagas t "
                + " left outer join fetch t.necessidadePessoa n "
                + " left outer join fetch t.planoAvaliacao pa "
                + " left outer join fetch pa.questaos qe "
                + " left outer join fetch t.cargo a "
                + " left outer join fetch t.competencias "
                + " left outer join fetch t.candidatos d "
                + " left outer join fetch d.entrevistas "
                + " left outer join fetch d.respostas r"
                + " left outer join fetch d.pessoa "
                + " left outer join fetch d.competencias "
                + " where t.id = :id";
        Vagas v = (Vagas) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return v;
    }
    
    public Vagas viewFindById(Object id) {
        String hql = "select t from Vagas t "
                + " left outer join fetch t.cargo a "
                + " left outer join fetch t.competencias "
                + " where t.id = :id";
        Vagas v = (Vagas) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return v;
    }

    public VagasService() {
        classRef = Vagas.class;
    }

    @Transactional
    public List<Vagas> findForTable(TableRequest request) {
        String hql = "select t from Vagas t "
                + " left join fetch t.competencias c "
                + " left join fetch t.cargo a";
        hql += request.applyFilter("t.id", "a.nome", "t.descricao");
        hql += request.applyOrder("t.id", "a.nome", "t.descricao");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Vagas> l = q.getResultList();
        return l;
    }

}
