package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.EventoMensal;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.service.folha.EventoCollection;
import br.com.empresa.rh.service.folha.EventoScript;
import br.com.empresa.rh.service.folha.IEvento;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class EventoService extends Service<Evento> {

    @Override
    public Evento findById(Object id) {
        String hql = "select t from Evento t "
                + " left join fetch t.eventoDependencias e "
                + " where t.id = :id";
        return (Evento)entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.
    }

    public EventoService() {
        classRef = Evento.class;
    }

    @Transactional
    public List<Evento> findForTable(TableRequest request) {

        String hql = "select t from Evento t ";
        hql += request.applyFilter("id", "nome");
        hql += request.applyOrder("id", "nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Evento> l = q.getResultList();
        return l;
    }

    public EventoCollection todosEventosFuncionario(int idCargo, Date data) {
        FuncionarioCargo cargo = (FuncionarioCargo) entityManager.createQuery(
                "from FuncionarioCargo f"
                + " left join fetch f.cargo "
                + " where f.id = :id"
        ).setParameter("id", idCargo).getSingleResult();
        return todosEventosFuncionario(cargo, data);
    }

    public EventoCollection todosEventosFuncionario(FuncionarioCargo funcionario, Date data) {
        EventoCollection collection = new EventoCollection();
        collection.addAll(eventosFuncionario(funcionario, data));
        collection.addAll(eventosCargo(funcionario.getCargo(), data));
        collection.addAll(eventosPadrao());
        collection.getEventos().addAll(eventosMensais(funcionario, data));
        return collection;
    }

    public List<Evento> eventosFuncionario(FuncionarioCargo cargoFuncionario, Date data) {
        String query = "from Evento e "
                + " inner join e.eventoFuncionarios f"
                + " where f.funcionarioCargo.id = :idCargo and "
                + " f.dataInicio <= :data and (f.dataFim is null or f.dataFim >= :data) ";
        return entityManager.createQuery(query)
                .setParameter("idCargo", cargoFuncionario.getId())
                .setParameter("data", data)
                .getResultList();
    }

    public List<Evento> eventosCargo(Cargo cargo, Date data) {
        String query = "from Evento e "
                + " inner join e.cargoHasEventos f"
                + " where f.cargo.id = :idCargo and "
                + " f.dataInicio <= :data and (f.dataFim is null or f.dataFim >= :data) ";
        return entityManager.createQuery(query)
                .setParameter("idCargo", cargo.getId())
                .setParameter("data", data)
                .getResultList();
    }

    public List<Evento> eventosPadrao() {
        String query = "from Evento e where e.padrao = :p";
        return entityManager.createQuery(query).setParameter("p", true).getResultList();
    }

    public List<IEvento> eventosMensais(FuncionarioCargo cargoFuncionario, Date data) {
        Calendar c = Calendar.getInstance();
        c.setTime(data);
        int ano = c.get(Calendar.YEAR);
        int mes = c.get(Calendar.MONTH);
        
        String query = "from EventoMensal e "
                + " inner join fetch e.evento m "
                + " where e.funcionarioCargo.id = :id and e.mes = :mes and e.ano = :ano ";
        
        List<EventoMensal> eventos = entityManager.createQuery(query)
                .setParameter("id", cargoFuncionario.getId())
                .setParameter("mes", mes)
                .setParameter("ano", ano)
                .getResultList();
        
        List<IEvento> l = new ArrayList<>();
        for (EventoMensal evento : eventos) {
           EventoScript e = new EventoScript(evento.getEvento());
           e.setReferencia(evento.getReferencia());
           l.add(e);
        }
        return l;
    }
}
