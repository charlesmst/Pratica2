package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Funcionario;
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
public class FuncionarioService extends Service<Funcionario>{

    public FuncionarioService() {
        classRef = Funcionario.class;
    }
    
    @Transactional
    public List<Funcionario> findForTable(TableRequest request) {

        
        String hql = "select t from Funcionario t ";
        hql+= request.applyFilter("id");     
        hql+= request.applyOrder("id");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Funcionario> l = q.getResultList();
        return l;
    }
    

}