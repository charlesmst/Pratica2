package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.CargoNivel;
import br.com.empresa.rh.model.FaixaSalarial;
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
public class CargoNivelService extends Service<CargoNivel> {

    public CargoNivelService() {
        classRef = CargoNivel.class;
    }

    @Override
    public CargoNivel findById(Object id) {

        return (CargoNivel) entityManager.createQuery("from CargoNivel c inner join fetch c.cargo where c.id = :id").setParameter("id", id).getSingleResult();
    }

    
    @Transactional
    public List<CargoNivel> findForTable(TableRequest request) {

        String hql = "select t from CargoNivel t "
                + " inner join fetch t.cargo ";
        hql += request.applyFilter("t.id", "t.nome");
        hql += request.applyOrder("t.id", "t.nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CargoNivel> l = q.getResultList();
        return l;
    }

    public List<CargoNivel> findByCargo(Cargo cargo) {
        List<CargoNivel> l =  entityManager.createQuery("from CargoNivel c "
                + " left outer join fetch c.faixaSalarials "
                + " where c.cargo.id = :id").setParameter("id", cargo.getId()).getResultList();
        for (CargoNivel l1 : l) {
            for (FaixaSalarial faixaSalarial : l1.getFaixaSalarials()) {
                faixaSalarial.setCargoNivels(null);
            }
        }
        return l;
    }

}
