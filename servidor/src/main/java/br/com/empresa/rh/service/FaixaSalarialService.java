package br.com.empresa.rh.service;

import br.com.empresa.rh.model.FaixaSalarial;
import br.com.empresa.rh.model.FaixaSalarialValor;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.FuncionarioFaixa;
import br.com.empresa.rh.model.request.TableRequest;
import java.math.BigDecimal;
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
public class FaixaSalarialService extends Service<FaixaSalarial> {

    public FaixaSalarialService() {
        classRef = FaixaSalarial.class;
    }

    public FaixaSalarialValor valorDoCargo(FuncionarioCargo cargo,Date data) {

        String hql = "select t from FuncionarioFaixa t "+
                        " left join fetch t.faixaSalarial f "+
                        " where t.funcionarioCargo.id = :idCargo and t.dataInicio <= :data order by t.dataInicio desc";
        Query q = entityManager.createQuery(hql);
        q.setParameter("idCargo", cargo.getId());
        q.setParameter("data", data);
        FuncionarioFaixa faixa = (FuncionarioFaixa) q.getSingleResult();
        
        hql =  "select t from FaixaSalarialValor t "+
                    " where id.dataInicio <= :data and faixaSalarial.id = :id order by id.dataInicio desc";
        
        FaixaSalarialValor fsv = (FaixaSalarialValor)entityManager.createQuery(hql)
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

}
