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
                + " left outer join fetch p.cor "
                + " left outer join fetch p.escolaridade "
                + " left outer join fetch p.estadoCivil "
                + " left outer join fetch p.necessidadeEspecials "
                + " left outer join fetch p.curriculoExperiencias "
                + " left outer join fetch p.curriculoFormacoes cf "
                + " left outer join fetch cf.escolaridade " 
                + " left outer join fetch p.curriculoQualificacoes "
                + " left outer join fetch p.cidade c "
                + " left outer join fetch c.estado uf "
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
                + " left outer join fetch p.cor "
                + " left outer join fetch p.escolaridade "
                + " left outer join fetch p.estadoCivil "
                + " left outer join fetch p.necessidadeEspecials "
                + " left outer join fetch p.curriculoExperiencias "
                + " left outer join fetch p.curriculoFormacoes cf "
                + " left outer join fetch cf.escolaridade "
                + " left outer join fetch p.curriculoQualificacoes "
                + " left outer join fetch p.cidade c "
                + " left outer join fetch c.estado uf ";
        hql+= request.applyFilter("pessoaId");     
        hql+= request.applyOrder("pessoaId");        
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Curriculo> l = q.getResultList();
        return l;
    }   
}