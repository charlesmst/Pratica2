package br.com.empresa.rh.service;


import br.com.empresa.rh.model.PessoaCarteira;
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
public class PessoaCarteiraService extends Service<PessoaCarteira>{

    public PessoaCarteiraService() {
        classRef = PessoaCarteira.class;
    }
    
    @Transactional
    public List<PessoaCarteira> findForTable(TableRequest request) {

        
        String hql = "select t from PessoaCarteira t ";
        hql+= request.applyFilter("id","cnh");     
        hql+= request.applyOrder("id","cnh");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<PessoaCarteira> l = q.getResultList();
        return l;
    }
    

}