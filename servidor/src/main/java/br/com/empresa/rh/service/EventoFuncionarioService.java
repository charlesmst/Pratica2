package br.com.empresa.rh.service;

import br.com.empresa.rh.model.EventoFuncionario;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.util.ApiException;
import br.com.empresa.rh.util.Utilitarios;
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
public class EventoFuncionarioService extends Service<EventoFuncionario> {

    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    @Override
    @Transactional
    public void insert(EventoFuncionario m) {

        if (m.getFuncionarioCargo() == null) {
            throw new IllegalArgumentException("funcionarioCargo");
        }
        m.setDataInicio(utilitarios.dataPeriodoInicio(m.getDataInicio()));

        if (m.isMensal() && folhaCalculadaService.folhaCalculadaMes(m.getFuncionarioCargo(), m.getDataInicio()) != null) {
            throw new ApiException("Evento já possui cálculo relacionado, exclua a folha de pagamento do funcionário no mês para alterar");
        }
        if (m.isMensal()) {
            m.setDataFim(utilitarios.dataPeriodoFim(m.getDataInicio()));
            m.setDataInicio(utilitarios.dataPeriodoInicio(m.getDataInicio()));
        }

        if (folhaCalculadaService.possuiFolhaCalculadaPeriodo(m.getFuncionarioCargo(), m.getDataInicio(), m.getDataFim())) {
            throw new ApiException("Não é possível excluir evento " + m.getEvento().getNome() + " pois já possui folha de pagamento calculada no período");
        }

        super.insert(m);
    }

    private EventoFuncionario findById(Object id, boolean excluido) {
        return entityManager.createQuery("from EventoFuncionario ef "
                + " inner join fetch ef.evento ev"
                + " inner join fetch ef.funcionarioCargo fc where ef.id = :id " + (!excluido ? " and ef.excluido is false" : ""), EventoFuncionario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Override
    public EventoFuncionario findById(Object id) {
        return findById(id, false);
    }

    @Override
    @Transactional
    public void delete(Object id) {
        //Só pode excluir se ainda nao possui folha calculada com esse evento
        EventoFuncionario e = findById(id);
        if (e.isMensal() && folhaCalculadaService.possuiFolhaCalculadaComEvento(e.getEvento(), e.getFuncionarioCargo(), e.getDataInicio())) {
            throw new ApiException("Não é possível excluir evento " + e.getEvento().getNome() + " pois já possui folha de pagamento calculada atrelada a este evento no mês atual");
        }
        if (e.getDataFim() != null) {
            if (folhaCalculadaService.possuiFolhaCalculadaComEvento(e.getEvento(), e.getFuncionarioCargo(), e.getDataInicio(), e.getDataFim())) {
                throw new ApiException("Não é possível excluir evento " + e.getEvento().getNome() + " pois já possui folha de pagamento calculada atrelada a este evento");
            }
        }

        Date d = folhaCalculadaService.ultimaDataFolhaCalculadaEvento(e);
        //Se existiu alguma folha e esta não está excluida, colocar a data final como a última
        if (d == null) {
            e.setExcluido(true);
        } else {
            e.setDataFim(utilitarios.dataPeriodoFim(d));
        }

        super.update(e);

    }

    @Autowired
    private Utilitarios utilitarios;

    public EventoFuncionarioService() {
        classRef = EventoFuncionario.class;
    }

    public long count(FuncionarioCargo cargo, int mes, int ano) {
        Date d = utilitarios.dataPeriodo(mes, ano);
        Object o = entityManager.createQuery("select count(*) from " + classRef.getName() + " t where t.funcionarioCargo.id = :id and t.dataInicio <= :d and (t.dataFim is null or t.dataFim >= :d)")
                .setParameter("id", cargo.getId())
                .setParameter("d", d)
                .getSingleResult();
        return (long) o;
    }

    @Transactional
    public List<EventoFuncionario> findForTable(TableRequest request, FuncionarioCargo cargo, int mes, int ano) {
        Date d = utilitarios.dataPeriodo(mes, ano);

        String hql = "select t from EventoFuncionario t "
                + " inner join fetch t.evento "
                + " where t.funcionarioCargo.id = :id  and t.dataInicio <= :d and (t.dataFim is null or t.dataFim >= :d) and t.excluido is false";
//        hql+= request.applyFilter("id","nome");     

        if (request != null) {
            hql += request.applyOrder("t.id", "t.evento.nome");
        }
        Query q = entityManager.createQuery(hql)
                .setParameter("id", cargo.getId())
                .setParameter("d", d);

        if (request != null) {
            request.applyPagination(q);
        }

//        request.applyParameters(q);
        List<EventoFuncionario> l = q.getResultList();
        return l;
    }

}
