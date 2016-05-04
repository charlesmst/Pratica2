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

    public UsuarioService() {
        classRef = Usuario.class;
    }

    public Usuario pessoaUsuario(String usuario) {
        List<Usuario> l = entityManager.createQuery("from Usuario u "
                + " inner join fetch u.pessoa p "
                + " where u.usuario like :usuario ")
                .setParameter("usuario", usuario.toUpperCase())
                .getResultList();
        if(l.isEmpty())
            return null;
        return l.get(0);
    }

    @Transactional
    public List<Usuario> findForTable(TableRequest request) {

        String hql = "select t from Usuario t ";
        hql += request.applyFilter("id", "usuario");
        hql += request.applyOrder("id", "usuario");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Usuario> l = q.getResultList();
        return l;
    }

}
