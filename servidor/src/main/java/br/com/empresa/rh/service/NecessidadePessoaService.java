package br.com.empresa.rh.service;


import br.com.empresa.rh.model.NecessidadePessoa;
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
public class NecessidadePessoaService extends Service<NecessidadePessoa>{

    public NecessidadePessoaService() {
        classRef = NecessidadePessoa.class;
    }
    
    @Transactional
    public List<NecessidadePessoa> findForTable(TableRequest request) {

        
        String hql = "select t from NecessidadePessoa t"
                + " inner join fetch t.usuario u ";
        hql+= request.applyFilter("id","cargo","situacao","dataRequisicao","u.nome");     
        hql+= request.applyOrder("id","cargo","situacao","dataRequisicao","u.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<NecessidadePessoa> l = q.getResultList();
        return l;
    }
    

}