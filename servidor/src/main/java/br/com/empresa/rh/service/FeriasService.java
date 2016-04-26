package br.com.empresa.rh.service;

import br.com.empresa.rh.model.Ferias;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import javax.persistence.Query;
import org.joda.time.DateTime;
import org.joda.time.Days;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class FeriasService extends Service<Ferias> {

    @Autowired
    private ParametroService parametroService;

    public FeriasService() {
        classRef = Ferias.class;
    }

    public int quantidadeDiasFerias(int cargoId, int mes, int ano) {

        Calendar cIni = Calendar.getInstance();
        cIni.set(Calendar.DAY_OF_MONTH, parametroService.diaFinalFolha());
        cIni.set(Calendar.MONTH, mes);
        cIni.set(Calendar.YEAR, ano);
        cIni.add(Calendar.MONTH, -2);

        Date dIni = cIni.getTime();

        cIni.add(Calendar.MONTH, 1);
        Date dFim = cIni.getTime();

        String hql = "from Ferias f where f.funcionarioCargo.id = :id and ( :dataInicio between f.dataGozoInicio and f.dataGozoFim or :dataFim between f.dataGozoInicio and f.dataGozoFim or (f.dataGozoInicio > :dataInicio  and f.dataGozoFim < :dataFim))";

        List<Ferias> ferias = entityManager.createQuery(hql)
                .setParameter("id", cargoId)
                .setParameter("dataInicio", dIni)
                .setParameter("dataFim", dFim)
                .getResultList();
        if (ferias.size() == 0) {
            return 0;
        }
        Ferias f = ferias.get(0);

        Date dataInicioMes = dIni;
        if (f.getDataGozoInicio().compareTo(dIni) > 0) {
            dataInicioMes = f.getDataGozoInicio();
        }

        Date dataFimMes = dFim;
        if (f.getDataGozoFim().compareTo(dFim) < 0) {
            dataFimMes = f.getDataGozoFim();
        }

        return Days.daysBetween(new DateTime(dataInicioMes), new DateTime(dataFimMes)).getDays();
    }

    @Transactional
    public List<Ferias> findForTable(TableRequest request) {

        String hql = "select t from Ferias t ";
        hql += request.applyFilter("id");
        hql += request.applyOrder("id");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<Ferias> l = q.getResultList();
        return l;
    }

}
