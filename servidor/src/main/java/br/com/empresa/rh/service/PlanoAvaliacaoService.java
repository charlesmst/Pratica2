package br.com.empresa.rh.service;


import br.com.empresa.rh.model.PlanoAvaliacao;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo
 */
@Repository
public class PlanoAvaliacaoService extends Service<PlanoAvaliacao>{

    @Override
    public PlanoAvaliacao findById(Object id) {
        String hql = "select t from PlanoAvaliacao t "
                + " left join fetch t.questaos q "
                //+ " left join fetch t.vagases v "
                + " where t.id = :id";
        PlanoAvaliacao p = (PlanoAvaliacao) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return p;
    }

    public PlanoAvaliacaoService() {
        classRef = PlanoAvaliacao.class;
    }
    
    @Transactional
    public List<PlanoAvaliacao> findForTable(TableRequest request) {

        
        String hql = "select t from PlanoAvaliacao t "
                + " left join fetch t.questaos q ";
//                + " left join fetch t.vagases v";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<PlanoAvaliacao> l = q.getResultList();
        return l;
    }
    
}