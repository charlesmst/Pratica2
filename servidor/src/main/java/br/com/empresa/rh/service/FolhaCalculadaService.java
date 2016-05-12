package br.com.empresa.rh.service;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Empresa;
import br.com.empresa.rh.model.Evento;
import br.com.empresa.rh.model.EventoFuncionario;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FolhaCalculadaEvento;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.response.EventoFolha;
import br.com.empresa.rh.model.response.FolhaResponse;
import br.com.empresa.rh.service.folha.EventoTipo;
import br.com.empresa.rh.service.folha.TipoCalculo;
import br.com.empresa.rh.util.ApiException;
import br.com.empresa.rh.util.Utilitarios;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.persistence.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author charles
 */
@Repository
public class FolhaCalculadaService extends Service<FolhaCalculada> {

    @Autowired
    private Utilitarios utilitarios;

    @Override
    public FolhaCalculada findById(Object id) {
        FolhaCalculada f = entityManager.createQuery("from FolhaCalculada f "
                + " left outer join fetch f.folhaCalculadaEventos ff "
                + " left outer join fetch ff.evento evento "
                + " inner join fetch f.funcionarioCargo fc "
                + " inner join fetch fc.unidade unidade "
                + " inner join fetch unidade.empresa "
                + " inner join fetch fc.cargo cargo "
                + " inner join fetch cargo.cbo "
                + " inner join fetch fc.funcionario funcionario "
                + " left outer join fetch funcionario.banco "
                + " inner join fetch funcionario.pessoa  fp "
                + " where f.id = :id  and f.excluido is false ", FolhaCalculada.class).setParameter("id", id).getSingleResult();
        return f;
    }

    @Override
    public long count() {
        Object o = entityManager.createQuery("select count(*) from " + classRef.getName() + " where  excluido is false").getSingleResult();
        return (long) o;
    }

    public boolean possuiFolhaCalculadaComEvento(Evento evento, FuncionarioCargo cargo) {
        Object o = entityManager.createQuery("select count(*) from FolhaCalculada f "
                + " inner join f.folhaCalculadaEventos fce "
                + " where f.funcionarioCargo.id = :idFuncionario and fce.evento.id = :idEvento ")
                .setParameter("idFuncionario", cargo.getId())
                .setParameter("idEvento", evento.getId())
                .getSingleResult();

        return o != null && ((long) o) > 0;
    }

    public boolean possuiFolhaCalculadaPeriodo(FuncionarioCargo cargo, Date inicio, Date fim) {
        Query q = entityManager.createQuery("select count(*) from FolhaCalculada f "
                + " where f.funcionarioCargo.id = :idFuncionario and f.dataReferente >= :inicio and f.excluido is false "
                + (fim != null ? "and f.dataReferente <= :fim " : ""))
                .setParameter("idFuncionario", cargo.getId())
                .setParameter("inicio", inicio);
        if (fim != null) {
            q.setParameter("fim", fim);
        }
        Object o = q.getSingleResult();

        return o != null && ((long) o) > 0;
    }

    public boolean possuiFolhaCalculadaComEvento(Evento evento, FuncionarioCargo cargo, Date dataInicio, Date dataFim) {
        Object o = entityManager.createQuery("select count(*) from FolhaCalculada f "
                + " inner join f.folhaCalculadaEventos fce "
                + " where f.funcionarioCargo.id = :idFuncionario and fce.evento.id = :idEvento and f.dataReferente between :dataInicio and :dataFim")
                .setParameter("idFuncionario", cargo.getId())
                .setParameter("idEvento", evento.getId())
                .setParameter("dataInicio", dataInicio)
                .setParameter("dataFim", dataFim)
                .getSingleResult();

        return o != null && ((long) o) > 0;
    }

    public boolean possuiFolhaCalculadaComEvento(Evento evento, FuncionarioCargo cargo, Date dataMes) {

        Object o = entityManager.createQuery("select count(*) from FolhaCalculada f "
                + " inner join f.folhaCalculadaEventos fce "
                + " where f.funcionarioCargo.id = :idFuncionario and fce.evento.id = :idEvento and f.dataReferente between :dataInicio and :dataFim")
                .setParameter("idFuncionario", cargo.getId())
                .setParameter("idEvento", evento.getId())
                .setParameter("dataInicio", utilitarios.dataPeriodoInicio(dataMes))
                .setParameter("dataFim", utilitarios.dataPeriodoFim(dataMes))
                .getSingleResult();

        return o != null && ((long) o) > 0;
    }

    @Override
    @Transactional
    public void delete(Object id) {
        FolhaCalculada f = entityManager.find(classRef, id);
        f.setExcluido(true);
        entityManager.merge(f);
    }

    public FolhaCalculadaService() {
        classRef = FolhaCalculada.class;
    }

    public FolhaCalculada folhaCalculadaMes(int mes, int ano, TipoCalculo tipo, FuncionarioCargo funcionario) {
        String hql = "from FolhaCalculada f"
                + " where f.mes = :mes and f.ano = :ano and f.excluido = :excluido and f.funcionarioCargo.id = :idCargo ";
        List<FolhaCalculada> l = entityManager.createQuery(hql)
                .setParameter("mes", mes)
                .setParameter("ano", ano)
                .setParameter("excluido", false)
                .setParameter("idCargo", funcionario.getId())
                .getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    public FolhaCalculada folhaCalculadaMes(FuncionarioCargo funcionario, Date data) {

        String hql = "from FolhaCalculada f"
                + " where f.excluido = :excluido and f.funcionarioCargo.id = :idCargo and f.dataReferente between :inicio and :fim";
        List<FolhaCalculada> l = entityManager.createQuery(hql)
                .setParameter("inicio", utilitarios.dataPeriodoInicio(data))
                .setParameter("fim", utilitarios.dataPeriodoFim(data))
                .setParameter("excluido", false)
                .setParameter("idCargo", funcionario.getId())
                .getResultList();
        if (l.isEmpty()) {
            return null;
        }
        return l.get(0);
    }

    public Date ultimaDataFolhaCalculadaEvento(EventoFuncionario ev) {
        String hql = "select max(f.dataReferente) from FolhaCalculada f"
                + " inner join f.folhaCalculadaEventos fe"
                + " where f.excluido = :excluido and fe.eventoFuncionario.id = :id";
        List<Date> d = entityManager.createQuery(hql)
                .setParameter("excluido", false)
                .setParameter("id", ev.getId())
                .getResultList();
        if (d.isEmpty()) {
            return null;
        }
        return d.get(0);
    }

    @Transactional
    public void excluirFolhasAntigas(List<FuncionarioCargo> funcionariosCalculo, int mes, int ano, TipoCalculo tipo, boolean isAdm) {
        for (FuncionarioCargo funcionariosCalculo1 : funcionariosCalculo) {
            FolhaCalculada calculada = folhaCalculadaMes(mes, ano, tipo, funcionariosCalculo1);
            //Rh não pode recalcular sem que tenha o excluido = true
            if (calculada != null) {
                if (!isAdm) {
                    throw new ApiException("Usuário não tem permissão de re-calcular folha de colaborador " + funcionariosCalculo1.getFuncionario().getPessoaId() + " que já foi cálculada, só é possível com a autorização do administrador", null);
                }
                calculada.setExcluido(true);
                entityManager.persist(calculada);
            }
        }
    }

    public long count(TableRequest request, Empresa empresa, int mes, int ano, List<FuncionarioCargo> funcionarios) {

        String hql = "select count(*) from FolhaCalculada t ";
        hql += request.applyFilter("t.id");
        hql += (!hql.contains("where") ? " where t.excluido is  false " : " and t.excluido is false ");

        HashMap<String, Object> parametros = new HashMap<>();
        if (empresa != null) {
            hql += " and t.funcionarioCargo.unidade.empresa.id = :empresaId";
            parametros.put("empresaId", empresa.getId());
        }
        if (funcionarios != null && funcionarios.size() > 0) {
            hql += " and t.funcionarioCargo.id in (:funcionarios)";
            List<Integer> func = new ArrayList<>();
            for (FuncionarioCargo funcionario : funcionarios) {
                func.add(funcionario.getId());
            }
            parametros.put("funcionarios", func);
        }
        if (mes > 0) {
            hql += " and t.mes = :mes";
            parametros.put("mes", mes);
        }

        if (ano > 0) {
            hql += " and t.ano = :ano";
            parametros.put("ano", ano);
        }
//        hql += request.applyOrder("t.id");
        Query q = entityManager.createQuery(hql);
        request.applyParameters(q);
        for (Map.Entry<String, Object> entrySet : parametros.entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            q.setParameter(key, value);
        }
        return (long) q.getSingleResult();
    }

    public List<FolhaCalculada> findForTable(TableRequest request, Empresa empresa, int mes, int ano, List<FuncionarioCargo> funcionarios) {

        String hql = "select t from FolhaCalculada t "
                + " inner join fetch t.funcionarioCargo f "
                + " inner join fetch f.funcionario ff "
                + " inner join fetch ff.pessoa ";
        hql += request.applyFilter("t.id");
        hql += (!hql.contains("where") ? " where t.excluido is  false " : " and t.excluido is false ");

        HashMap<String, Object> parametros = new HashMap<>();
        if (empresa != null) {
            hql += " and t.funcionarioCargo.unidade.empresa.id = :empresaId";
            parametros.put("empresaId", empresa.getId());
        }
        if (funcionarios != null && funcionarios.size() > 0) {
            hql += " and t.funcionarioCargo.id in (:funcionarios)";
            List<Integer> func = new ArrayList<>();
            for (FuncionarioCargo funcionario : funcionarios) {
                func.add(funcionario.getId());
            }
            parametros.put("funcionarios", func);
        }
        if (mes > 0) {
            hql += " and t.mes = :mes";
            parametros.put("mes", mes);
        }

        if (ano > 0) {
            hql += " and t.ano = :ano";
            parametros.put("ano", ano);
        }
//        hql += request.applyOrder("t.id");
        hql += !hql.contains("order") ? " order by t.ano desc, t.mes desc,tipo " : ",t.ano desc, t.mes desc,tipo ";
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        for (Map.Entry<String, Object> entrySet : parametros.entrySet()) {
            String key = entrySet.getKey();
            Object value = entrySet.getValue();
            q.setParameter(key, value);
        }
        List<FolhaCalculada> l = q.getResultList();
        return l;
    }

    public FolhaResponse fromFolhaCalculada(FolhaCalculada folhaCalculada) {
        FolhaResponse fr = new FolhaResponse();
        fr.setEventos(new ArrayList<EventoFolha>());
        fr.setEventosInvisiveis(new ArrayList<EventoFolha>());
        //Dados do cabecalho
        Funcionario f = folhaCalculada.getFuncionarioCargo().getFuncionario();
        fr.setNome(f.getPessoa().getCpf() + " - " + f.getPessoa().getNome());
        fr.setCargo(folhaCalculada.getFuncionarioCargo().getCargo().getNome());
        fr.setCbo(folhaCalculada.getFuncionarioCargo().getCargo().getCbo().getId() + "");
        Empresa e = folhaCalculada.getFuncionarioCargo().getUnidade().getEmpresa();
        fr.setEmpresa(e.getCnpj() + " - " + e.getNome());
        fr.setCompetencia(new SimpleDateFormat("MM/yyyy").format(folhaCalculada.getDataReferente()));
        fr.setAdmissao(folhaCalculada.getFuncionarioCargo().getDataEntrada());
        fr.setUnidade(folhaCalculada.getFuncionarioCargo().getUnidade().getNome());
        if (f.getBanco() != null) {
            fr.setConta(f.getConta());
            fr.setAgencia(f.getAgencia()+"");
        }
        if (folhaCalculada.getSalario() <= 0) {
            fr.setSalarioBase("");
        } else {
            fr.setSalarioBase(Utilitarios.formataDinheiro(folhaCalculada.getSalario()));
        }
        if (folhaCalculada.getBaseInss() <= 0) {
            fr.setBaseInss("");
        } else {
            fr.setBaseInss(Utilitarios.formataDinheiro(folhaCalculada.getBaseInss()));
        }
        if (folhaCalculada.getBaseFgts() <= 0) {
            fr.setBaseFgts("");
        } else {
            fr.setBaseFgts(Utilitarios.formataDinheiro(folhaCalculada.getBaseFgts()));
        }
        if (folhaCalculada.getBaseIrrf() <= 0) {
            fr.setBaseIrrf("");
        } else {
            fr.setBaseIrrf(Utilitarios.formataDinheiro(folhaCalculada.getBaseIrrf()));
        }
        fr.setFgts(Utilitarios.formataDinheiro(folhaCalculada.getFgts()));
//Eventos
        for (FolhaCalculadaEvento eventoCalculado : folhaCalculada.getFolhaCalculadaEventos()) {
            EventoFolha evento = new EventoFolha();
            evento.setId(eventoCalculado.getEvento().getId());
            evento.setNome(eventoCalculado.getEvento().getNome());
            evento.setReferencia(eventoCalculado.getReferencia());

            switch (eventoCalculado.getTipo()) {
                case EventoTipo.BASE:
                case EventoTipo.PROVENTO:
                case EventoTipo.BENEFICIO:
                    evento.setValorVencimento(eventoCalculado.getValor());
                    evento.setTipo(+1);
                    break;
                case EventoTipo.DESCONTO:
                case EventoTipo.FINALIZACAO:
                    evento.setValorDesconto(eventoCalculado.getValor());
                    evento.setTipo(-1);

                    break;
            }
            if (eventoCalculado.isVisivel()) {
                fr.getEventos().add(evento);
            } else {
                fr.getEventosInvisiveis().add(evento);
            }
        }

        TipoCalculo t = TipoCalculo.parse(folhaCalculada.getTipo());
        switch (t) {
            case demissao:
                fr.setTitulo("Recibo de pagamento de demissão");
                break;
            case complementar:
                fr.setTitulo("Recibo de pagamento complementar");
                break;
            case ferias:
                fr.setTitulo("Recibo de pagamento de férias");
                break;

            case decimo:
                fr.setTitulo("Recibo de pagamento de décimo");
                break;
            case mes:
                fr.setTitulo("Recibo do pagamento de salário");
                break;

        }

        fr.getEventos().sort(new Comparator<EventoFolha>() {
            @Override
            public int compare(EventoFolha f1, EventoFolha f2) {
                if ((f1.getValorVencimento() > 0 && f2.getValorVencimento() > 0) || (f1.getValorDesconto() > 0 && f2.getValorDesconto() > 0)) {
                    return Integer.compare(f1.getId(), f2.getId());
                } else if (f1.getValorVencimento() > 0) {
                    return -1;
                } else {
                    return 1;
                }
            }
        });

        return fr;
    }

}
