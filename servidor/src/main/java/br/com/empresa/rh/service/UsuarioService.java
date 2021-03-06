package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Usuario;
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
public class UsuarioService extends Service<Usuario> {

    @Override
    public Usuario findById(Object id) {
        String hql = "select t from Usuario t "
                + " inner join fetch t.pessoa p "
                + " left join fetch p.escolaridade e"
                + " left join fetch p.cidade c "
                + " left join fetch c.estado uf"
                + " where p.id = :id";
        Usuario u = (Usuario) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult();
        return u;
    }

    public UsuarioService() {
        classRef = Usuario.class;
    }

    public Usuario pessoaUsuario(String usuario) {
        List<Usuario> l = entityManager.createQuery("from Usuario u "
                + " where upper(u.usuario) like upper( :usuario ) ")
                .setParameter("usuario", usuario.toUpperCase())
                .getResultList();
        if(l.isEmpty())
            return null;
        return l.get(0);
    }

    @Transactional
    public List<Usuario> findForTable(TableRequest request) {

        String hql = "select t from Usuario t "
                + " inner join fetch t.pessoa p "
                + " inner join fetch p.escolaridade e"
                + " inner join fetch p.cidade c "
                + " inner join fetch c.estado uf";
        hql += request.applyFilter("t.id", "p.nome", "usuario");
        hql += request.applyOrder("t.id", "p.nome", "usuario");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Usuario> l = q.getResultList();
        return l;
    }

}
