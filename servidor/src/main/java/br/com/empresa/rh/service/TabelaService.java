package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Tabela;
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
public class TabelaService extends Service<Tabela>{

    public TabelaService() {
        classRef = Tabela.class;
    }
    
    @Transactional
    public List<Tabela> findForTable(TableRequest request) {

        
        String hql = "select t from Tabela t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Tabela> l = q.getResultList();
        return l;
    }
    
    public Tabela tabelaData(Date data,String tipo){
        String hql = "select t from Tabela t"+
                    " left join fetch t.tabelaValoreses "+
                    " where t.dataInicio <= :data and tipo = :tipo "+
                    " order by t.dataInicio desc";
        
        Query q = entityManager.createQuery(hql, Tabela.class);
        q.setParameter("tipo", tipo);
        q.setParameter("data", data);
        return (Tabela)q.getSingleResult();
    }
    

}