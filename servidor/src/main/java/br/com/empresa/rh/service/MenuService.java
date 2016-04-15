package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Menu;
import br.com.empresa.rh.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import org.hibernate.Hibernate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MenuService extends Service<Menu>{

    public MenuService() {
        classRef = Menu.class;
    }
    
    @Transactional
    public List<Menu> findForTable(TableRequest request) {

        
        String hql = "select t from Menu t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Menu> l = q.getResultList();
        return l;
    }
    

}
