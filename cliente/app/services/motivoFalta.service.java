
import br.com.empresa.rh.model.MotivoFalta;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author Bottega  - dl.bottega@hotmail.com  
 */
@Repository
public class MotivoFaltaService extends Service<MotivoFalta>{

    public MotivoFaltaService() {
        classRef = MotivoFalta.class;
    }
    
    @Transactional
    public List<MotivoFalta> findForTable(TableRequest request) {

        
        String hql = "select t from MotivoFalta t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<MotivoFalta> l = q.getResultList();
        return l;
    }
    

}