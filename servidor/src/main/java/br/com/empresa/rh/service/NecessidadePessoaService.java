package br.com.empresa.rh.service;


import br.com.empresa.rh.model.NecessidadePessoa;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.util.ApiException;
import java.util.Date;
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

    @Override
    public void update(NecessidadePessoa m) {
        NecessidadePessoa necessidade = findById(m.getId());
        necessidade.setCargo(m.getCargo());
        necessidade.setSituacao(m.getSituacao());
        necessidade.setDescricao(m.getDescricao());
        necessidade.setPerfil(m.getPerfil());
        necessidade.setQuantidade(m.getQuantidade());
        super.update(necessidade); 
    }

    @Override
    
    @Transactional
    public void insert(NecessidadePessoa m) {
        if(m.getUsuario() == null){
            throw new ApiException("Usuário nulo na inserção de necessidade");
        }
        m.setDataRequisicao(new Date());
        super.insert(m);
    }

    @Override
    public NecessidadePessoa findById(Object id) {
        String hql = "select t from NecessidadePessoa t "
                + " inner join fetch t.cargo c "
                 + " where t.id = :id";
        NecessidadePessoa e = (NecessidadePessoa) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); 
        return e;
    }

    public NecessidadePessoaService() {
        classRef = NecessidadePessoa.class;
    }
    
    @Transactional
    public List<NecessidadePessoa> findForTable(TableRequest request) {

        
        String hql = "select t from NecessidadePessoa t"
                + " inner join fetch t.usuario u inner join fetch t.cargo c ";
        hql+= request.applyFilter("t.id","c.nome");     
        hql+= request.applyOrder("t.id","c.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<NecessidadePessoa> l = q.getResultList();
        return l;
    }
    

}