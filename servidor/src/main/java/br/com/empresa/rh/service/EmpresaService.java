package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Empresa;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author charles
 */
@Repository
public class EmpresaService extends Service<Empresa>{

    public EmpresaService() {
        classRef = Empresa.class;
    }
    
    @Transactional
    public List<Empresa> findForTable(TableRequest request) {

        
        String hql = "select t from Cidade t " 
                + "inner join fetch t.cidade c";
        hql+= request.applyFilter("t.id","t.nome");     
        hql+= request.applyOrder("t.id","t.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Empresa> l = q.getResultList();
        return l;
    }
    

}