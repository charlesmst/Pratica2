package br.com.empresa.rh.service;


import br.com.empresa.rh.model.Curriculo;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
/**
 *
 * @author gustavo.michels
 */
@Repository
public class CurriculoService extends Service<Curriculo>{

    @Override
    public Curriculo findById(Object id) {
        String hql = "select t from Curriculo t "
                + " inner join fetch t.pessoa p "
                + " left join fetch p.cor "
                + " inner join fetch p.escolaridade "
                + " left join fetch p.estadoCivil "
                + " left join fetch p.necessidadeEspecials "
                + " left join fetch p.pessoaCarteira "
                + " left join fetch p.curriculoExperiencias "
                + " left join fetch p.curriculoFormacoes "
                + " left join fetch p.curriculoQualificacoes "
                + " inner join fetch p.cidade c "
                + " inner join fetch c.estado uf "
                + " where p.id = :id";
        Curriculo c = (Curriculo) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return c;
    }

    public CurriculoService() {
        classRef = Curriculo.class;
    }
    
    @Transactional
    public List<Curriculo> findForTable(TableRequest request) {
        String hql = "select t from Curriculo t "
                + " inner join fetch t.pessoa p "
                + " left join fetch p.cor "
                + " inner join fetch p.escolaridade "
                + " left join fetch p.estadoCivil "
                + " left join fetch p.necessidadeEspecials "
                + " left join fetch p.pessoaCarteira "
                + " left join fetch p.curriculoExperiencias "
                + " left join fetch p.curriculoFormacoes "
                + " left join fetch p.curriculoQualificacoes "
                + " inner join fetch p.cidade c "
                + " inner join fetch c.estado uf ";
        hql+= request.applyFilter("pessoaId");     
        hql+= request.applyOrder("pessoaId");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Curriculo> l = q.getResultList();
        return l;
    }
    

}