package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.CargoHasEvento;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.util.Utilitarios;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author charles
 */
@Repository
public class CargoHasEventoService extends Service<CargoHasEvento>{

    @Autowired
    private Utilitarios utilitarios;
    public CargoHasEventoService() {
        classRef = CargoHasEvento.class;
    }
    
    @Transactional
    public List<CargoHasEvento> findForTable(TableRequest request) {

        
        String hql = "select t from CargoHasEvento t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CargoHasEvento> l = q.getResultList();
        return l;
    }
    public List<CargoHasEvento> findByCargo(Cargo cargo, int ano, int mes){
        Date d = utilitarios.dataPeriodo(mes, ano);
        return entityManager.createQuery("from CargoHasEvento ce "
                + " inner join fetch ce.evento evento "
                + " where ce.cargo.id = :id and :d >= ce.dataInicio and (ce.dataFim is null or ce.dataFim >= :d)")
                .setParameter("id", cargo.getId())
                .setParameter("d", d)
                .getResultList();
    }

}