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
public class VagasService extends Service<Vagas>{

    public VagasService() {
        classRef = Vagas.class;
    }
    
    @Transactional
    public List<Vagas> findForTable(TableRequest request) {

        
        String hql = "select t from Vagas t "
                + "inner join fetch t.necessidadePessoa n ";
        hql+= request.applyFilter("t.id","descricao");     
        hql+= request.applyOrder("t.id","descricao");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Vagas> l = q.getResultList();
        return l;
    }
    

}