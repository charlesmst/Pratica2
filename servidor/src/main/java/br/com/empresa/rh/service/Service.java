/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Usuario;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.persistence.criteria.CriteriaBuilder;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
public abstract class Service<T> {

    protected Class<T> classRef;

    @PersistenceContext
    protected EntityManager entityManager;

    @Transactional
    public void insert(T m) {
        entityManager.persist(m);
    }

    @Transactional
    public void update(T m) {
        entityManager.merge(m);
    }

    @Transactional
    public void delete(Object id) {
        T m = findById(id);

        entityManager.remove(m);
    }

    public long count() {
        Object o = entityManager.createQuery("select count(*) from " + classRef.getName()).getSingleResult();
        return (long) o;
    }

    public T findById(Object id) {
        T m = (T) entityManager.find(classRef, id);
        return m;
    }

    public List<T> findAll() {
                String nome = classRef.getSimpleName();

        TypedQuery<T> q = 
        entityManager.createQuery("select t from "+nome+" t", classRef);
        return q.getResultList();
    }

//
//    public List<T> findByMultipleColumns(String valor, String order, String... colunas) {
//        CriteriaBuilder cb = entityManager.getCriteriaBuilder();
//        cb.or((root,query,cb)->{
//            
//        });
//        if (!valor.equals("")) {
//
//            ArrayList<Criterion> cr = new ArrayList<>();
//            boolean isInteger = false;
//            try {
//                Integer.parseInt(valor);
//                isInteger = true;
//            } catch (Exception e) {
//            }
//            ArrayList<String> gone = new ArrayList<>();
//
//            for (int i = 0; i < colunas.length; i++) {
////                Type t = HibernateUtil.getColumnType(classRef, colunas[i]);
//                String coluna = colunas[i];
//                //Verify if is necessary to create an alias
//                if (coluna.contains(".")) {
//                    String table = coluna.substring(0, coluna.indexOf("."));
//                    if (!gone.contains(table)) {
//                        gone.add(table);
//                        String nome = Character.toString((char) ((char) 'A' + (char) gone.indexOf(table)));
//
//                        c.createAlias(table, "tab" + nome);
//                    }
//                    //Adjust column name
//                    colunas[i] = coluna.replaceAll("^[^\\.]+(\\..+)$", "tab" + Character.toString((char) ((char) 'A' + (char) gone.indexOf(table))) + "$1");
//
//                }
////                if (t instanceof org.hibernate.type.IntegerType) {
////                    if (isInteger) {
////                        cr.add(Restrictions.eq(colunas[i], new Integer(valor)));
////                    }
////                } else {
//                    cr.add(Restrictions.like(colunas[i], "%" + valor.replaceAll("\\s+", "%") + "%"));
//
////                }
//            }
//            Criterion[] cra = new Criterion[cr.size()];
//            for (int i = 0; i < cr.size(); i++) {
//                cra[i] = cr.get(i);
//            }
//            cb.or(cra);
//        }
//        if (order != null) {
//            c.addOrder(Order.asc(order));
//        }
//
//        List<T> r = c.list();
//        return r;
//    }
}
