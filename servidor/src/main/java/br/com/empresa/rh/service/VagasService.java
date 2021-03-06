package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Candidato;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.model.Vagas;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gustavo.michels
 */
@Repository
public class VagasService extends Service<Vagas> {

    @Override
    public Vagas findById(Object id) {
        String hql = "select t from Vagas t "
                + " left outer join fetch t.necessidadePessoa n "
                + " left outer join fetch t.planoAvaliacao pa "
                + " left outer join fetch pa.questaos qe "
                + " left outer join fetch t.cargo a "
                + " left outer join fetch t.competencias "
                + " left outer join fetch t.candidatos d "
                + " left outer join fetch d.entrevistas "
                + " left outer join fetch d.respostas r"
                + " left outer join fetch d.pessoa "
                + " left outer join fetch d.competencias "
                + " where t.id = :id";
        Vagas v = (Vagas) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return v;
    }

    public Vagas viewFindById(Object id) {
        String hql = "select t from Vagas t "
                + " left outer join fetch t.cargo a "
                + " left outer join fetch t.competencias "
                + " where t.id = :id";
        Vagas v = (Vagas) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return v;
    }

    public VagasService() {
        classRef = Vagas.class;
    }

    private List<Vagas> removeDuplicados(List<Vagas> l) {
        for (int i = 0; i < l.size(); i++) {
            Object a = l.get(i);
            for (int j = i + 1; j < l.size(); j++) {
                Object b = l.get(j);
                if (a.equals(b)) {
                    l.remove(j);
                    j--;
                }
            }
        }
        return l;
    }

    private List<Vagas> verificaInscricao(List<Vagas> l, int id) {
        for (Vagas v : l) {
            for (Candidato c : v.getCandidatos()) {
                if (c.getPessoa().getId() == id) {
                    c.setIsCandidato(true);
                } else {
                    c.setIsCandidato(false);
                }
            }
        }
        return l;
    }

    @Transactional
    public List<Vagas> findForTable(TableRequest request, int[] tipo, int id) {

        if (tipo[2] > 3) {
            String hql = "select t from Vagas t "
                    + " left outer join fetch t.competencias c "
                    + " left outer join fetch t.candidatos "
                    + " left outer join fetch t.cargo a ";
            hql += request.applyFilter("t.id", "a.nome", "t.descricao");
            hql += request.applyOrder("t.id", "a.nome", "t.descricao");
            Query q = entityManager.createQuery(hql);
            request.applyPagination(q);
            request.applyParameters(q);
            List<Vagas> l = q.getResultList();
            return verificaInscricao(removeDuplicados(l), id);
        } else if (tipo[2] <= 3) {
            String hql = "select t from Vagas t "
                    + " left outer join fetch t.competencias c"
                    + " left outer join fetch t.candidatos "
                    + " left outer join fetch t.cargo a "
                    + " where t.sigiloso = false and "
                    + " t.finalizado = false and ("
                    + " t.tipo = :t1 or t.tipo = :t2)";
            hql += request.applyFilter("t.id", "a.nome", "t.descricao");
            hql += request.applyOrder("t.id", "a.nome", "t.descricao");
            Query q = entityManager.createQuery(hql)
                    .setParameter("t1", tipo[0])
                    .setParameter("t2", tipo[1]);
            request.applyPagination(q);
            request.applyParameters(q);
            List<Vagas> l = q.getResultList();
            return verificaInscricao(removeDuplicados(l), id);
        } else {
            return null;
        }
    }

}
