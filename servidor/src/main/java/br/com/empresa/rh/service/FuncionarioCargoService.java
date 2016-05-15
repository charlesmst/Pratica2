package br.com.empresa.rh.service;

import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.FuncionarioCargoHasMotivoFalta;
import br.com.empresa.rh.model.MotivoFalta;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.util.Utilitarios;
import java.util.List;
import javax.persistence.Query;
import org.hibernate.Hibernate;
import org.joda.time.Days;
import org.joda.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class FuncionarioCargoService extends Service<FuncionarioCargo> {

    @Autowired
    private Utilitarios utilitarios;

    public FuncionarioCargoService() {
        classRef = FuncionarioCargo.class;
    }

    public List<FuncionarioCargo> findByFuncionario(int idFuncionario) {
        return entityManager.createQuery("select t from FuncionarioCargo t "
                + "left join fetch t.funcionario f "
                + "left join fetch f.pessoa p "
                + "left join fetch t.cargo c "
                + "  where f.id = :id").setParameter("id", idFuncionario).getResultList();
    }

    @Transactional
    public List<FuncionarioCargo> findForTable(TableRequest request) {

        String hql = "select t from FuncionarioCargo t "
                + "left join fetch t.funcionario f "
                + "left join fetch f.pessoa p "
                + "left join fetch t.cargo c ";
        hql += request.applyFilter("t.id", "p.nome", "c.nome");
        hql += request.applyOrder("id", "nome");
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<FuncionarioCargo> l = q.getResultList();
        for (FuncionarioCargo l1 : l) {
            l1.getFuncionario().getPessoa().setFuncionario(null);
        }
        return l;
    }

    public double descontaAtestados(double diasAtuais, FuncionarioCargo funcionarioCargo, int mes, int ano) {
        List<FuncionarioCargoHasMotivoFalta> l = atestadoMes(funcionarioCargo, mes, ano);
        double dias = 0d;
        for (FuncionarioCargoHasMotivoFalta l1 : l) {
            LocalDate inicio = new LocalDate(l1.getDataInicio());
            LocalDate fim = new LocalDate(utilitarios.dataPeriodoFim(utilitarios.dataPeriodo(mes, ano)));
            if (l1.getDataFinal() != null) {
                LocalDate d = new LocalDate(l1.getDataFinal());
                if (d.compareTo(fim) < 0) {
                    fim = d;
                }
            }

            int diasDeste = Days.daysBetween(inicio, fim).getDays();
            if (diasDeste > 15) {
                //Verificar quantos estão neste mês
                LocalDate inicioContagem = inicio.plusDays(15);
                LocalDate inicioMes = new LocalDate().withDayOfMonth(1).withMonthOfYear(mes).withYear(ano);
                LocalDate fimMes = inicioMes.plusMonths(1).minusDays(1);
                LocalDate dataInicioContagem = inicioContagem.isAfter(inicioMes) ? inicioContagem : inicioMes;
                LocalDate dataFimContagem = fim.isBefore(fimMes) ? fim : fimMes;
                dias += Days.daysBetween(dataInicioContagem, dataFimContagem).getDays();
            }
        }
        return Math.max(0, diasAtuais - dias);
    }

    public List<FuncionarioCargoHasMotivoFalta> atestadoMes(FuncionarioCargo funcionarioCargo, int mes, int ano) {
        String hql = "from FuncionarioCargoHasMotivoFalta fc "
                + " inner join fetch fc.motivoFalta motivoFalta "
                + " where  fc.funcionarioCargo.id = :id and :mesano between cast(to_char(fc.dataInicio,'YYYYMM') as integer) and cast(to_char(fc.dataFinal,'YYYYMM') as integer) "
                + " and fc.motivoFalta.atestado is true";
        int mesAno = Integer.parseInt(String.format("%04d", ano) + String.format("%02d", mes));

        List<FuncionarioCargoHasMotivoFalta> l = entityManager.createQuery(hql)
                .setParameter("id", funcionarioCargo.getId())
                .setParameter("mesano", mesAno).getResultList();

        return l;
    }

}
