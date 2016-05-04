package br.com.empresa.rh.service;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.FolhaCalculada;
import br.com.empresa.rh.model.FolhaCalculadaEvento;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.request.TableRequest;
import br.com.empresa.rh.model.response.EventoFolha;
import br.com.empresa.rh.model.response.FolhaResponse;
import br.com.empresa.rh.service.folha.EventoTipo;
import br.com.empresa.rh.service.folha.TipoCalculo;
import br.com.empresa.rh.util.ApiException;
import br.com.empresa.rh.util.Utilitarios;
import java.util.ArrayList;
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
public class FolhaCalculadaService extends Service<FolhaCalculada> {

    @Autowired
    private Utilitarios Utilitarios;
    @Override
    public FolhaCalculada findById(Object id) {
        FolhaCalculada f = entityManager.createQuery("from FolhaCalculada f "
                + " left outer join fetch f.folhaCalculadaEventos ff "
                + " left outer join fetch ff.evento evento "
                + " inner join fetch f.funcionarioCargo fc "
                + " inner join fetch fc.cargo cargo "
                + " inner join fetch fc.funcionario funcionario"
                + " inner join fetch funcionario.pessoa  fp "
                + " where f.id = :id  and f.excluido is false ", FolhaCalculada.class).setParameter("id", id).getSingleResult();
        return f;
    }

    @Override
    public long count() {
        Object o = entityManager.createQuery("select count(*) from " + classRef.getName() + " where  excluido is false").getSingleResult();
        return (long) o;
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

    @Transactional
    public List<FolhaCalculada> findForTable(TableRequest request) {

        String hql = "select t from FolhaCalculada t "
                + " inner join fetch t.funcionarioCargo f "
                + " inner join fetch f.funcionario ff "
                + " inner join fetch ff.pessoa ";
        hql += request.applyFilter("t.id");
        hql += (!hql.contains("where") ? " where t.excluido is  false " : " and t.excluido is false ");
//        hql += request.applyOrder("t.id");
        hql += !hql.contains("order") ? " order by t.ano desc, t.mes desc,tipo " : ",t.ano desc, t.mes desc,tipo ";
        Query q = entityManager.createQuery(hql);
        request.applyPagination(q);
        request.applyParameters(q);
        List<FolhaCalculada> l = q.getResultList();
        return l;
    }

    public FolhaResponse fromFolhaCalculada(FolhaCalculada folhaCalculada) {
        FolhaResponse fr = new FolhaResponse();
        fr.setEventos(new ArrayList<EventoFolha>());
        fr.setEventosInvisiveis(new ArrayList<EventoFolha>());
        //Dados do cabecalho
        fr.setNome(folhaCalculada.getFuncionarioCargo().getFuncionario().getPessoa().getNome());
        fr.setCargo(folhaCalculada.getFuncionarioCargo().getCargo().getNome());

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
                    break;
                case EventoTipo.DESCONTO:              
                case EventoTipo.FINALIZACAO:
                    evento.setValorDesconto(eventoCalculado.getValor());
                    break;
            }
            if(eventoCalculado.isVisivel())
                fr.getEventos().add(evento);
            else
                fr.getEventosInvisiveis().add(evento);
        }
        return fr;
    }

}
