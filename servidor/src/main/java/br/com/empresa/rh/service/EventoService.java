package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.EventoDependencia;
import br.com.empresa.rh.model.EventoDependenciaId;
import br.com.empresa.rh.model.EventoMensal;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.service.folha.EventoCollection;
import br.com.empresa.rh.service.folha.EventoScript;
import br.com.empresa.rh.service.folha.IEvento;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class EventoService extends Service<Evento> {

    @Autowired
    private ParametroService parametroService;

    @Transactional
    public void salvar(Evento e) {

        for (EventoDependencia eventoDependencia : e.getEventoDependencias()) {
            eventoDependencia.setEvento(e);
        }
        if (e.getId() == 0) {
            entityManager.persist(e);
        } else {
            entityManager.merge(e);
        }
    }

    @Transactional
    public void persistDependencias2(Evento e) {
//        entityManager.merge(e);
        for (EventoDependencia eventoDependencia : e.getEventoDependencias()) {
            Evento e2 = new Evento();
            e2.setId(e.getId());
            eventoDependencia.setEvento(e2);
            Evento e3 = new Evento();
            e3.setId(eventoDependencia.getEventoDependencia().getId());
            eventoDependencia.setEventoDependencia(e3);
            entityManager.merge(eventoDependencia);

        }
    }

    public EventoCollection eventosFerias() {
        String[] valores = parametroService.findById("eventos_folha").getValor().split(",");
        List<Integer> eventosId = new ArrayList<>();
        for (String valore : valores) {
            eventosId.add(Integer.parseInt(valore));
        }
        List<Evento> eventos = new ArrayList<>();
        for (Integer eventoId : eventosId) {
            eventos.add(findById(eventoId));
        }
        EventoCollection col = new EventoCollection(eventos);
        return col;
    }

    @Override
    public Evento findById(Object id) {
        String hql = "select t from Evento t "
                + " left join fetch t.eventoDependencias e "
                + " left join fetch e.eventoDependencia d "
                + " where t.id = :id";
        Evento e = (Evento) entityManager.createQuery(hql)
                .setParameter("id", id)
                .getSingleResult(); //To change body of generated methods, choose Tools | Templates.

        return e;
    }

    public EventoService() {
        classRef = Evento.class;
    }

    public double valorPeriodo(FuncionarioCargo cargo, Evento evento, int mesinicio, int anoinicio, int mesfim, int anofim) {
        Calendar cIni = Calendar.getInstance();
        cIni.set(Calendar.DAY_OF_MONTH, 1);
        cIni.set(Calendar.MONTH, mesinicio - 1);
        cIni.set(Calendar.YEAR, anoinicio);

        Date dIni = cIni.getTime();

        cIni.set(Calendar.MONTH, mesfim - 1);
        cIni.set(Calendar.YEAR, anofim);
        cIni.add(Calendar.MONTH, 1);
        cIni.add(Calendar.DATE, -1);
        Date dFim = cIni.getTime();

        Object r = entityManager.createQuery("select sum(fce.valor) from FolhaCalculadaEvento fce "
                + " where fce.folhaCalculada.funcionarioCargo.id = :id and fce.evento.id = :idEvento and "
                + " fce.folhaCalculada.dataReferente between :dataInicio and :dataFim and fce.folhaCalculada.excluido is false ")
                .setParameter("id", cargo.getId())
                .setParameter("idEvento", evento.getId())
                .setParameter("dataInicio", dIni)
                .setParameter("dataFim", dFim)
                .getSingleResult();
        if (r != null) {
            return (double) r;
        } else {
            return 0;
        }
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
                + " inner join e.eventoFuncionarios f "
                + " left join fetch e.eventoDependencias ed"
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
                + " left join fetch e.eventoDependencias ed"
                + " where f.cargo.id = :idCargo and "
                + " f.dataInicio <= :data and (f.dataFim is null or f.dataFim >= :data) ";
        return entityManager.createQuery(query)
                .setParameter("idCargo", cargo.getId())
                .setParameter("data", data)
                .getResultList();
    }

    public List<Evento> eventosPadrao() {
        String query = "from Evento e"
                + " left join fetch e.eventoDependencias ed"
                + " where e.padrao = :p";
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
