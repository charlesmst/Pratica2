package br.com.empresa.rh.service;

import br.com.empresa.rh.filter.secure.NivelAcesso;
import br.com.empresa.rh.model.Dependente;
import br.com.empresa.rh.model.Ferias;
import br.com.empresa.rh.model.Funcionario;
import br.com.empresa.rh.model.FuncionarioAcidente;
import br.com.empresa.rh.model.FuncionarioCargo;
import br.com.empresa.rh.model.FuncionarioCargoHasAdvertenciaTipo;
import br.com.empresa.rh.model.FuncionarioCargoHasMotivoFalta;
import br.com.empresa.rh.model.FuncionarioCargoHasMotivoFaltaId;
import br.com.empresa.rh.model.FuncionarioFaixa;
import br.com.empresa.rh.model.FuncionarioQualificacao;
import br.com.empresa.rh.model.Pessoa;
import br.com.empresa.rh.model.Usuario;
import br.com.empresa.rh.model.request.TableRequest;
import java.util.HashSet;
import java.util.List;
import javax.persistence.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author gustavo
 */
@Repository
public class PessoaService extends Service<Pessoa> {

    public PessoaService() {
        classRef = Pessoa.class;
    }

    @Transactional
    public List<Pessoa> findForTable(TableRequest request, boolean somenteFuncionarios) {

        String hql = "select t from Pessoa t"
                + " left join fetch t.funcionario funcionario "
                + " left join fetch t.cidade c "
                + " left join fetch t.escolaridade e";
        if (request != null) {
            hql += request.applyFilter("t.id", "t.nome");
            hql += request.applyOrder("t.id", "t.nome");
        }

        if (somenteFuncionarios) {
            hql += hql.contains("where") ? " and " : " where ";
            hql += " funcionario is not null ";
        }
        Query q = entityManager.createQuery(hql);
        if (request != null) {
            request.applyPagination(q);
            request.applyParameters(q);
        }
        List<Pessoa> l = q.getResultList();
        return l;
    }

    @Override
    @Transactional
    public void update(Pessoa m) {
        save(m); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void insert(Pessoa m) {
        save(m); //To change body of generated methods, choose Tools | Templates.
    }

    @Transactional
    public void save(Pessoa pessoa) {
        boolean novoFuncionario = false;
        if (pessoa.getFuncionario() != null) {

            Funcionario funcionario = pessoa.getFuncionario();
            if (funcionario.getPessoaId() == null ||  funcionario.getPessoaId()== 0) {
                novoFuncionario = true;
            }
            funcionario.setPessoa(pessoa);
            funcionario.setPessoaId(pessoa.getId());

            if (funcionario.getDependentes() != null) {
                for (Dependente dependente : funcionario.getDependentes()) {
                    dependente.setFuncionario(funcionario);
                }
            }
            for (FuncionarioCargo funcionarioCargo : funcionario.getFuncionarioCargos()) {
                funcionarioCargo.setFuncionario(funcionario);
                if (funcionarioCargo.getSindicato() != null && funcionarioCargo.getSindicato().getId() == 0) {
                    funcionarioCargo.setSindicato(null);
                }
                if (funcionarioCargo.getDemissaoTipo() != null && funcionarioCargo.getDemissaoTipo().getId() == 0) {
                    funcionarioCargo.setDemissaoTipo(null);
                }

                if (funcionarioCargo.getFeriases() != null) {
                    for (Ferias feriase : funcionarioCargo.getFeriases()) {
                        feriase.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFeriases(new HashSet<Ferias>());
                }

                if (funcionarioCargo.getFuncionarioQualificacaos() != null) {
                    for (FuncionarioQualificacao funcionarioQualificacao : funcionarioCargo.getFuncionarioQualificacaos()) {
                        funcionarioQualificacao.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFuncionarioQualificacaos(new HashSet<FuncionarioQualificacao>());
                }

                if (funcionarioCargo.getFuncionarioAcidentes() != null) {
                    for (FuncionarioAcidente funcionarioAcidente : funcionarioCargo.getFuncionarioAcidentes()) {
                        funcionarioAcidente.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFuncionarioAcidentes(new HashSet<FuncionarioAcidente>());
                }
                if (funcionarioCargo.getFuncionarioCargoHasAdvertenciaTipos() != null) {
                    for (FuncionarioCargoHasAdvertenciaTipo funcionarioCargoHasAdvertenciaTipo : funcionarioCargo.getFuncionarioCargoHasAdvertenciaTipos()) {
                        funcionarioCargoHasAdvertenciaTipo.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFuncionarioCargoHasAdvertenciaTipos(new HashSet<FuncionarioCargoHasAdvertenciaTipo>());
                }
                if (funcionarioCargo.getFuncionarioCargoHasMotivoFaltas() != null) {
                    for (FuncionarioCargoHasMotivoFalta funcionarioCargoHasMotivoFalta : funcionarioCargo.getFuncionarioCargoHasMotivoFaltas()) {
                        funcionarioCargoHasMotivoFalta.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFuncionarioCargoHasMotivoFaltas(new HashSet<FuncionarioCargoHasMotivoFalta>());
                }
                if (funcionarioCargo.getFuncionarioFaixas() != null) {
                    for (FuncionarioFaixa funcionarioFaixa : funcionarioCargo.getFuncionarioFaixas()) {
                        funcionarioFaixa.setFuncionarioCargo(funcionarioCargo);
                    }
                } else {
                    funcionarioCargo.setFuncionarioFaixas(new HashSet<FuncionarioFaixa>());

                }
            }
        }
        if (pessoa.getUsuario() != null && pessoa.getId() > 0) {
            Pessoa p = findById(pessoa.getId());
            pessoa.getUsuario().setSenha(p.getUsuario().getSenha());
            pessoa.getUsuario().setPessoaId(p.getId());
            pessoa.getUsuario().setPessoa(pessoa);
        } else if (pessoa.getFuncionario() != null && pessoa.getId() == 0) {
            pessoa.setUsuario(new Usuario());
            pessoa.getUsuario().setPessoaId(pessoa.getId());
            pessoa.getUsuario().setUsuario(pessoa.getEmail());
            pessoa.getUsuario().setSenha(pessoa.getCpf());//@TODO Ajustar para gerar uma senha
            pessoa.getUsuario().setNivel(Integer.parseInt(NivelAcesso.FUNCIONARIO));
            pessoa.getUsuario().setPessoa(pessoa);

        }
        if(pessoa.getId() != 0 && novoFuncionario){
            entityManager.persist(pessoa.getFuncionario());
        }
        if (pessoa.getId() == 0) {
            entityManager.persist(pessoa);
//            entityManager.persist(funcionario);
        } else {
            entityManager.merge(pessoa);
//            entityManager.merge(funcionario);

        }
//        if (pessoa.getFuncionario() != null) {
//            Funcionario funcionario = pessoa.getFuncionario();
//
//            funcionario.setPessoa(pessoa);
//            funcionario.setPessoaId(pessoa.getId());
//            if(insertFuncionario)
//                entityManager.persist(this);
//        }
    }

    @Override
    public Pessoa findById(Object id) {
        return (Pessoa) entityManager.createQuery("from Pessoa p "
                + " left outer join fetch p.usuario usuario"
                + " left outer join fetch p.pessoasForMaeId pessoasForMaeId "
                + " left outer join fetch p.pessoaByPessoaId pessoaByPessoaId"
                + " left outer join fetch p.necessidadeEspecials necessidadeEspecials"
                + " left outer join fetch p.funcionario f  "
                + " left outer join fetch p.escolaridade es"
                + " left outer join fetch f.banco b"
                + " left outer join fetch p.cor c"
                + " left outer join fetch p.cidade cidade"
                + " left outer join fetch p.estadoCivil ec"
                + " left outer join fetch f.vinculoEmpregaticio ve"
                + " left outer join fetch f.tipoSanguineo ts"
                + " left outer join fetch f.dependentes de "
                + " left outer join fetch de.pessoa "
                + " left outer join fetch f.funcionarioCargos fc "
                + " left outer join fetch fc.funcionarioCargoHasMotivoFaltas funcionarioCargoHasMotivoFaltas"
                + " left outer join fetch funcionarioCargoHasMotivoFaltas.motivoFalta motivoFalta "
                + " left outer join fetch fc.cargo ce"
                + " left outer join fetch fc.sindicato sind"
                + " left outer join fetch ce.cbo cbo"
                + " left outer join fetch fc.demissaoTipo dt "
                + " left outer join fetch fc.unidade un"
                + " left outer join fetch un.empresa emp"
                + " left outer join fetch fc.funcionarioCargoHasAdvertenciaTipos fcat "
                + " left outer join fetch fcat.advertenciaTipo advertenciaTipo "
                + " left outer join fetch fc.feriases ferias"
                + " left outer join fetch fc.funcionarioAcidentes fa"
                + " left outer join fetch fc.funcionarioQualificacaos funcionarioQualificacaos"
                + " left outer join fetch funcionarioQualificacaos.qualificacao qualificacao"
                + " left outer join fetch fc.funcionarioFaixas funcionarioFaixas"
                + " left outer join fetch funcionarioFaixas.cargoNivel cargoNivel"
                + " left outer join fetch funcionarioFaixas.faixaSalarial faixaSalarial"
                + " where p.id = :id ").setParameter("id", id).getSingleResult();
    }
}
