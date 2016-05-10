package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo
 */
@Repository
public class FuncionarioService extends Service<Funcionario>{

    @Override
    public Funcionario findById(Object id) {
        return (Funcionario)entityManager.createQuery("from Funcionario f "
                + " inner join fetch f.pessoa p  "
                + " inner join fetch f.banco b"
                + " inner join fetch p.cidade cidade"
                + " inner join fetch f.vinculoEmpregaticio ve"
                + " left outer join fetch f.tipoSanguineo ts"
                + " left outer join fetch f.dependentes de "
                + " left outer join fetch f.funcionarioCargos fc "
                + " left outer join fetch fc.cargo ce"
                + " left outer join fetch fc.demissaoTipo dt "
                + " left outer join fetch fc.sindicato sind"
                + " left outer join fetch fc.unidade un"
                + " left outer join fetch un.empresa emp"
                + " left outer join fetch fc.funcionarioCargoHasAdvertenciaTipos fcat"
                + " left outer join fetch fc.funcionarioAcidentes fa"
                + " where f.pessoaId = :id ").setParameter("id", id).getSingleResult();
    }

    public FuncionarioService() {
        classRef = Funcionario.class;
    }
    
    @Transactional
    public List<Funcionario> findForTable(TableRequest request) {

        
        String hql = "select t from Funcionario t "
                + " inner join fetch t.pessoa p ";
        hql+= request.applyFilter("t.id","p.nome");     
        hql+= request.applyOrder("t.id","p.nome");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Funcionario> l = q.getResultList();
        return l;
    }
    

}