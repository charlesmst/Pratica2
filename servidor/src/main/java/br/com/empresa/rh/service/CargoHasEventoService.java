package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Cargo;
import br.com.empresa.rh.model.CargoHasEvento;
import br.com.empresa.rh.model.CargoHasEventoId;
import br.com.empresa.rh.model.EventoFuncionario;
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
public class CargoHasEventoService extends Service<CargoHasEvento> {

    @Autowired
    private Utilitarios utilitarios;
    @Autowired
    private FolhaCalculadaService folhaCalculadaService;

    public CargoHasEventoService() {
        classRef = CargoHasEvento.class;
    }

    @Override
    @Transactional
    public void insert(CargoHasEvento m) {

        if (m.getEvento() == null) {
            throw new IllegalArgumentException("evento");
        }
        if (m.getCargo() == null) {
            throw new IllegalArgumentException("cargo");
        }
        
        m.setDataInicio(utilitarios.dataPeriodoInicio(m.getDataInicio()));

        if (m.getDataFim() != null) {
            m.setDataFim(utilitarios.dataPeriodoFim(m.getDataFim()));
        }
        m.setId(new CargoHasEventoId());
        m.getId().setCargoId(m.getCargo().getId());
        m.getId().setEventoId(m.getEvento().getId());
        if (folhaCalculadaService.possuiFolhaCalculadaPeriodo(m.getCargo(), m.getDataInicio(), m.getDataFim())) {
            throw new ApiException("Não é possível excluir evento " + m.getEvento().getNome() + " pois já possui folha de pagamento calculada no período");
        }

        super.insert(m);
    }
    
    @Override
    @Transactional
    public void delete(Object id) {
        //SÃ³ pode excluir se ainda nao possui folha calculada com esse evento
        CargoHasEvento e = findById(id);
        Date d = folhaCalculadaService.ultimaDataFolhaCalculadaEvento(e.getCargo());
        Date dFim = utilitarios.dataPeriodoFim(d);
        if (e.getDataFim() != null) {
            if (dFim.equals(e.getDataFim()) && folhaCalculadaService.possuiFolhaCalculadaComEvento(e.getEvento(), e.getCargo(), e.getDataInicio(), e.getDataFim())) {
                throw new ApiException("Não é possível excluir evento " + e.getEvento().getNome() + " pois já possui folha de pagamento calculada atrelada a este evento");
            }
        }

        
        //Se existiu alguma folha e esta não está excluida, colocar a data final como a Ãºltima
        if (d == null) {
            e.setExcluido(true);
        } else {
            e.setDataFim(utilitarios.dataPeriodoFim(d));
        }

        super.update(e);

    }


    @Transactional
    public List<CargoHasEvento> findForTable(TableRequest request) {

        String hql = "select t from CargoHasEvento t ";
        hql += request.applyFilter("id", "nome");
        hql += request.applyOrder("id", "nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<CargoHasEvento> l = q.getResultList();
        return l;
    }

    public List<CargoHasEvento> findByCargo(Cargo cargo, int ano, int mes) {
        Date d = utilitarios.dataPeriodo(mes, ano);
        return entityManager.createQuery("from CargoHasEvento ce "
                + " inner join fetch ce.evento evento "
                + " where ce.cargo.id = :id and :d >= ce.dataInicio and (ce.dataFim is null or ce.dataFim >= :d)")
                .setParameter("id", cargo.getId())
                .setParameter("d", d)
                .getResultList();
    }

}
