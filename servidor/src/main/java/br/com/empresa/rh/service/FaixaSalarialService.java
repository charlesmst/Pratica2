package br.com.empresa.rh.service;

import br.com.empresa.rh.model.CargoNivel;
import br.com.empresa.rh.model.FaixaSalarial;
import br.com.empresa.rh.model.FaixaSalarialValor;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.FuncionarioFaixa;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.service.folha.FolhaException;
import br.com.empresa.rh.util.ApiException;
import java.math.BigDecimal;
import java.util.Comparator;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

/**
 *
 * @author charles
 */
@Repository
public class FaixaSalarialService extends Service<FaixaSalarial> {

    public FaixaSalarialService() {
        classRef = FaixaSalarial.class;
    }

    @Override
    @Transactional
    public void update(FaixaSalarial m) {
        for (FaixaSalarialValor faixaSalarialValor : m.getFaixaSalarialValors()) {
            faixaSalarialValor.getId().setFaixaSalarialId(m.getId());
        }
        super.update(m);
    }

    @Override
    @Transactional
    public void insert(FaixaSalarial m) {
        Set<FaixaSalarialValor> s = m.getFaixaSalarialValors();
        m.setFaixaSalarialValors(null);

        entityManager.persist(m);

        m.setFaixaSalarialValors(s);
        for (FaixaSalarialValor faixaSalarialValor : m.getFaixaSalarialValors()) {
            faixaSalarialValor.getId().setFaixaSalarialId(m.getId());
        }
        entityManager.merge(m);

    }

    @Override
    public FaixaSalarial findById(Object id) {

        return (FaixaSalarial) entityManager.createQuery("from FaixaSalarial faixaSalarial "
                + " left outer join fetch faixaSalarial.faixaSalarialValors  "
                + " where faixaSalarial.id = :id")
                .setParameter("id", id).getSingleResult();
    }

    public FaixaSalarialValor valorDoCargo(FuncionarioCargo cargo, Date data) {

        String hql = "select t from FuncionarioFaixa t "
                + " left join fetch t.faixaSalarial f "
                + " where t.funcionarioCargo.id = :idCargo and t.dataInicio <= :data order by t.dataInicio desc";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idCargo", cargo.getId());
        q.setParameter("data", data);
        FuncionarioFaixa faixa;
        try {
            faixa = (FuncionarioFaixa) q.getSingleResult();
        }catch(Exception ex){
            throw new FolhaException("Funcionário com o cargo "+cargo.getId()+" não possui salário relacionado");
        }
        hql = "select t from FaixaSalarialValor t "
                + " where id.dataInicio <= :data and faixaSalarial.id = :id order by id.dataInicio desc";

        FaixaSalarialValor fsv = (FaixaSalarialValor) entityManager.createQuery(hql)
                .setParameter("data", data)
                .setParameter("id", faixa.getFaixaSalarial().getId())
                .getSingleResult();
        return fsv;

    }

    @Transactional
    public List<FaixaSalarial> findForTable(TableRequest request) {

        String hql = "select t from FaixaSalarial t ";
        hql += request.applyFilter("id", "nome");
        hql += request.applyOrder("id", "nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<FaixaSalarial> l = q.getResultList();
        return l;
    }

    public List<FaixaSalarial> findByCargoNivel(CargoNivel cargo) {
        List<FaixaSalarial> faixas = entityManager.createQuery("from FaixaSalarial f  "
                + " left outer join fetch f.cargoNivels cargoNiveis"
                //                + " left outer join fetch f.faixaSalarialValors faixaSalarialValors "
                + " where cargoNiveis.id = :id ").setParameter("id", cargo.getId()).getResultList();
        for (FaixaSalarial faixa : faixas) {
            faixa.setCargoNivels(null);
        }
        return faixas;
    }

}
