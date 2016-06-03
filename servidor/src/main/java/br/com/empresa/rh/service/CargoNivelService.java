package br.com.empresa.rh.service;


import br.com.empresa.rh.model.CargoNivel;
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
public class CargoNivelService extends Service<CargoNivel>{

    public CargoNivelService() {
        classRef = CargoNivel.class;
    }
    
    @Transactional
    public List<CargoNivel> findForTable(TableRequest request) {

        
        String hql = "select t from CargoNivel t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CargoNivel> l = q.getResultList();
        return l;
    }
    

}