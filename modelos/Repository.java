<#if package?? && package != "">
package ${package};

</#if>

import br.com.empresa.rh.model.${name};
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author ${user}
 */
@Repository
public class ${name}Service extends Service<${name}>{

    public ${name}Service() {
        classRef = ${name}.class;
    }
    
    @Transactional
    public List<${name}> findForTable(TableRequest request) {

        
        String hql = "select t from ${name} t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<${name}> l = q.getResultList();
        return l;
    }
    

}