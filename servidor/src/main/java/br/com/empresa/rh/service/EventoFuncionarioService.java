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

    @Override
    public void insert(EventoFuncionario m) {
        if(m.isMensal())
            m.setDataFim(null);
        
        //Verificar se não tem folha cálculada para o funcionário
//        if(m.get)
    }

    @Override
    public EventoFuncionario findById(Object id) {
        return entityManager.createQuery("from EventoFuncionario ef "
                + " inner join fetch ef.evento ev"
                + " inner join fetch ef.funcionarioCargo fc where ef.id = :id ",EventoFuncionario.class)
                .setParameter("id", id)
                .getSingleResult();
    }

    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    @Override
    public void delete(Object id) {
        //Só pode excluir se ainda nao possui folha calculada com esse evento
        EventoFuncionario e = findById(id);
        if (folhaCalculadaService.possuiFolhaCalculadaComEvento(e.getEvento(), e.getFuncionarioCargo())) {
            throw new ApiException("Não é possível excluir evento " + e.getEvento().getNome() + " pois já possui folha de pagamento calculada atrelada a este evento");
        }
        super.delete(id);
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
                + " where t.funcionarioCargo.id = :id  and t.dataInicio <= :d and (t.dataFim is null or t.dataFim >= :d)";
//        hql+= request.applyFilter("id","nome");     
        hql += request.applyOrder("t.id", "t.evento.nome");
        Query q = entityManager.createQuery(hql)
                .setParameter("id", cargo.getId())
                .setParameter("d", d);
        request.applyPagination(q);
//        request.applyParameters(q);
        List<EventoFuncionario> l = q.getResultList();
        return l;
    }

}
