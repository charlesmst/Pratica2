package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class CargoService extends Service<Cargo> {

    @Override
    public Cargo findById(Object id) {

        return (Cargo) entityManager.createQuery("from Cargo c left outer join fetch c.cbo where c.id = :id").setParameter("id", id).getSingleResult();
    }

    public CargoService() {
        classRef = Cargo.class;
    }

    public List<Cargo> daEmpresa(int codigoEmpresa,Date data) {
        String hql = "from Cargo c  "
                + " left join  fetch c.funcionarioCargos fc "
                + " inner join fetch fc.funcionario f "
                + " inner join fetch f.pessoa p "
                + " where fc.unidade.empresa.id = :id and fc is not EMPTY and"
                + " fc.dataEntrada <= :data and (fc.dataSaida is null or fc.dataSaida >= :data) ";
        return entityManager.createQuery(hql)
                .setParameter("id", codigoEmpresa)
                .setParameter("data",data)
                .getResultList();
    }

    @Transactional
    public List<Cargo> findForTable(TableRequest request) {

        String hql = "select t from Cargo t "
        + " inner join fetch t.cbo ";
        hql += request.applyFilter("t.id", "t.nome");
        hql += request.applyOrder("t.id", "t.nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Cargo> l = q.getResultList();
        return l;
    }

}
