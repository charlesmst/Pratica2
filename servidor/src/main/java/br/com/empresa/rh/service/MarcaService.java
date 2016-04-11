package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Marca;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

@Repository
public class MarcaService extends Service<Marca>{


    @Transactional
    public void insert(Marca m) {
        entityManager.persist(m);
    }

    @Transactional
    public void delete(Marca m) {
        entityManager.remove(m);
    }

    public List<Marca> findAll() {
        List<Marca> list = entityManager.createQuery("from Marca as m").getResultList();
        return list;
    }

    public Marca findById(Integer id) {
        Marca m = (Marca) entityManager.getReference(Marca.class, id);
        return m;
    }
}
