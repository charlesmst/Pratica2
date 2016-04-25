package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Dependente;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.ArrayList;
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
public class DependenteService extends Service<Dependente>{

    public DependenteService() {
        classRef = Dependente.class;
    }
    
    public List<Pessoa> dependentesDe(int funcionarioId,Date data){
        String hql = "select d from Dependente d  "
                + " inner join fetch d.pessoa p"
                + " left join fetch p.necessidadeEspecials "
                + " where d.funcionario.pessoaId = :funcionario "
                + " and d.dataInicial <= :data and (d.dataFim is null or d.dataFim >= :data)";
        List<Dependente> d = entityManager.createQuery(hql)
                .setParameter("funcionario", funcionarioId)
                .setParameter("data", data).getResultList();
        
        List<Pessoa> pessoas = new ArrayList<>();
        for (Dependente d1 : d) {
            pessoas.add(d1.getPessoa());
        }
        return pessoas;
    }
    @Transactional
    public List<Dependente> findForTable(TableRequest request) {

        
        String hql = "select t from Dependente t ";
        hql+= request.applyFilter("id","t.pessoa.nome");     
        hql+= request.applyOrder("id","t.pessoa.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Dependente> l = q.getResultList();
        return l;
    }
    

}