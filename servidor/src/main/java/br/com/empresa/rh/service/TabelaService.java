package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Tabela;
import br.com.empresa.rh.model.TabelaValores;
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
public class TabelaService extends Service<Tabela> {

    @Override
    @Transactional

    public void update(Tabela m) {
        for (TabelaValores tabelaValorese : m.getTabelaValoreses()) {
            tabelaValorese.setTabela(m);
        }
        super.update(m); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void insert(Tabela m) {
        for (TabelaValores tabelaValorese : m.getTabelaValoreses()) {
            tabelaValorese.setTabela(m);
        }
        super.insert(m); //To change body of generated methods, choose Tools | Templates.
    }

    public TabelaService() {
        classRef = Tabela.class;
    }

    @Override
    public Tabela findById(Object id) {
        return entityManager.createQuery("from Tabela t left outer join fetch t.tabelaValoreses tv where t.id = :id order by tv.valorInicial", Tabela.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Transactional
    public List<Tabela> findForTable(TableRequest request) {

        String hql = "select t from Tabela t ";
        hql += request.applyFilter("t.tipo");
        hql += request.applyOrder("t.nome","t.dataInicio");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Tabela> l = q.getResultList();
        return l;
    }

    public Tabela tabelaData(Date data, String tipo) {
        String hql = "select t from Tabela t"
                + " left join fetch t.tabelaValoreses "
                + " where t.dataInicio <= :data and tipo = :tipo "
                + " order by t.dataInicio desc";

        Query q = entityManager.createQuery(hql, Tabela.class);
        q.setParameter("tipo", tipo);
        q.setParameter("data", data);
        return (Tabela) q.getSingleResult();
    }

    public List<String> tipos() {
        String hql = "select t.tipo from Tabela t"
                + " group by t.tipo ";

        Query q = entityManager.createQuery(hql, String.class);

        return q.getResultList();
    }

}
