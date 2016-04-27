package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Cargo;
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
public class CargoService extends Service<Cargo>{

    public CargoService() {
        classRef = Cargo.class;
    }
    
    public List<Cargo> daEmpresa(int codigoEmpresa){
        String hql = "from Cargo c  "
                + " left join  fetch c.funcionarioCargos fc "
                + " inner join fetch fc.funcionario f "
                + " inner join fetch f.pessoa p "
                + " where fc.unidade.empresa.id = :id and fc is not EMPTY ";
        return entityManager.createQuery(hql)
                .setParameter("id", codigoEmpresa)
                .getResultList();
    }
    @Transactional
    public List<Cargo> findForTable(TableRequest request) {

        
        String hql = "select t from Cargo t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Cargo> l = q.getResultList();
        return l;
    }
    

}