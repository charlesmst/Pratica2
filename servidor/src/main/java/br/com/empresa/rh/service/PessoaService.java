package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Pessoa;
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
public class PessoaService extends Service<Pessoa>{

    public PessoaService() {
        classRef = Pessoa.class;
    }
    
    @Transactional
    public List<Pessoa> findForTable(TableRequest request) {

        
        String hql = "select t from Pessoa t "
                + "left join fetch Cidade c";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Pessoa> l = q.getResultList();
        return l;
    }
    

}