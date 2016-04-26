package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Parametro;
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
public class ParametroService extends Service<Parametro>{

    public ParametroService() {
        classRef = Parametro.class;
    }
    
    public String valorParametro(String nome){
        Parametro p = findById(nome);
        if(p != null)
            return p.getValor();
        return null;
    }
    public int diaEntregaFolha(){
        String v =  valorParametro("data_entrega_folha") ;
        if(v == null)
            return 5;
        return Integer.parseInt(v);
    }
    
    public int diaFinalFolha(){
        String v =  valorParametro("data_final_folha") ;
        if(v == null)
            return 20;
        return Integer.parseInt(v);
    }
    @Transactional
    public List<Parametro> findForTable(TableRequest request) {

        
        String hql = "select t from Parametro t ";
        hql+= request.applyFilter("id","nome");     
        hql+= request.applyOrder("id","nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Parametro> l = q.getResultList();
        return l;
    }
    

}