--
-- PostgreSQL database dump
--

-- Dumped from database version 9.3.9
-- Dumped by pg_dump version 9.3.9
-- Started on 2016-05-15 18:54:07

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;

SET search_path = public, pg_catalog;

--
-- TOC entry 2197 (class 0 OID 45587)
-- Dependencies: 176
-- Data for Name: evento; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (6, '1/3 de férias - Mensal', false, 5, 'var vencimentos = eventos.bases + eventos.proventos
var valor = vencimentos * (1/12) * (1/3)
evento.valorCalculado = valor', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (7, 'Férias - Mensal', false, 5, 'var vencimentos = eventos.bases + eventos.proventos
var valor = vencimentos * (1/12)
evento.valorCalculado = valor;', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (8, '13º - Mensal', false, 5, 'var vencimentos = eventos.bases + eventos.proventos
var valor = vencimentos * (1/12)
evento.valorCalculado = valor;', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (9, 'Insalubridade 40%', true, 2, 'var vencimentos = eventos.bases;
var valor = vencimentos * 0.4;
evento.valorCalculado = valor;', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (10, 'Insalubridade 20%', true, 2, 'var vencimentos = eventos.bases;
var valor = vencimentos * 0.2;
evento.valorCalculado = valor;', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (11, 'Insalubridade 10%', true, 2, 'var vencimentos = eventos.bases;
var valor = vencimentos * 0.1;
evento.valorCalculado = valor;', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (12, 'Cota Patronal', false, 5, 'evento.valorCalculado = (eventos.bases+eventos.proventos) * 0.2', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (13, 'Salário Família', true, 4, 'var dependentes = consulta.dependentes;
var valorGanho = eventos.bases + eventos.proventos
calcula()
function determinar(){
    var quantidade = 0
    for each(var pessoa in dependentes){
        if(utilitarios.idadePessoa(pessoa) <=14 || pessoa.necessidadeEspecials.size() > 0)    
            quantidade++
    }
    return quantidade;
}
function calcula(){
    var q = determinar();
    if(q === 0){
        evento.aplicavel = false;
        return;
    }
    
    var tabela = consulta.getTabela(''FAMILIA'')
    for each(var a in tabela){
        if(a.valorInicial <= valorGanho && a.valorFinal >= valorGanho){
            var valorBeneficio = a.desconto;
            evento.valorCalculado = q * valorBeneficio;
            evento.referencia = q;
            return;
        }
    }
    evento.aplicavel = false;
}

', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (47, 'Férias', true, 2, '
var ferias = consulta.feriasDoMes;
var valorTotal = 0;
for each(var f in ferias)
    valorTotal += dependencia.getValorPeriodo("ferias",f.dataAquisitivoInicio,f.dataAquisitivoFim);

evento.valorCalculado = valorTotal', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (48, '1/3 de Férias', true, 2, '
var ferias = consulta.feriasDoMes;
var valorTotal = 0;
for each(var f in ferias)
    valorTotal += dependencia.getValorPeriodo("ferias",f.dataAquisitivoInicio,f.dataAquisitivoFim);

evento.valorCalculado = valorTotal', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (14, 'Desconto de salário adiantado de férias', true, 3, 'var quantidade = consulta.quantidadeFerias;
if(quantidade == 0 )
{
    evento.aplicavel = false;
}else{
    evento.referencia = quantidade;
    evento.valorCalculado = consulta.salario.valor / 30 * quantidade;
}', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (3, 'INSS', true, 3, 'var tabela = consulta.getTabela("INSS");
var vencimentos = eventos.bases + eventos.proventos;

function aplicar(item){
    var aliquota = item.aliquota /100;
    var venc = vencimentos;
    if(item.valorFinal < venc)
        venc = item.valorFinal;
    evento.setValorCalculado(venc * aliquota);
    evento.setReferencia(item.aliquota);
    folha.baseInss = vencimentos
}
function calcular(){
    for each(var item in tabela){
        if(item.valorInicial <= vencimentos && item.valorFinal >= vencimentos){
            aplicar(item);
            return;
        }
    }
    //Se não encontrar a faixa, faz em cima do máximo
    var maximo = {
        valorFinal:0
    };
    for each(var item in tabela){
        if(item.valorFinal >= maximo.valorFinal){
            maximo = item;
        }
    }
    aplicar(maximo)
    
    
}
calcular()', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (51, 'FGTS', false, 5, ' 
var fgts = valorFgts();
var baseFgts = eventos.bases + eventos.proventos
 evento.valorCalculado = (baseFgts ) * (fgts / 100)
 evento.referencia = fgts;
 folha.fgts = evento.valorCalculado;
 folha.baseFgts = baseFgts
function valorFgts(){
    var t = consulta.getTabela(''FGTS'');
    for each(var a in t){
        return a.aliquota;
    }
}', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (50, 'Ajuste de Dissídio Coletivo', true, 2, 'evento.valorCalculado = consulta.salario.valor * 0.05;
', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (2, 'Salário', true, 1, 'var salario = consulta.salario;
evento.valorCalculado = utilitarios.descontaConformeParametros(salario.valor);
evento.referencia = parametros.diasMes;//salario.cargaHoraria;
folha.salarioBase = salario.valor;', true, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (52, '13º Salário', true, 2, '
var dataInicio = utilitarios.dateMax(utilitarios.dataPeriodo(1,parametros.ano),consulta.dataAdmissao);
var demissao = consulta.dataDemissao
var dataFimPeriodo = utilitarios.dataPeriodo(12,parametros.ano)
var dataFim = (demissao?utilitarios.dateMin(dataFimPeriodo,demissao):dataFimPeriodo);

//Data limite do adiantamento é 30/11
//Data limite do pagamento é 20/12
//Então se é antes do mes 12, considerar como adiantamento, se for mes 12, considerar o pagamento integral
var valorTotal = dependencia.getProjecao(''decimo'',dataInicio.mes,dataInicio.ano,dataFim.mes,dataFim.ano)
var mesFim = dataFim.mes;
if(mesFim == 12)
    mesFim--;
evento.valorCalculado = valorTotal;

', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (54, 'Desconto de adiantamento de 13º salário', true, 3, '
var dataInicio = utilitarios.dateMax(utilitarios.dataPeriodo(1,parametros.ano),consulta.dataAdmissao);
var demissao = consulta.dataDemissao
var dataFimPeriodo = utilitarios.dataPeriodo(12,parametros.ano)
var dataFim = (demissao?utilitarios.dateMin(dataFimPeriodo,demissao):dataFimPeriodo);

//Data limite do adiantamento é 30/11
//Data limite do pagamento é 20/12
//Então se é antes do mes 12, considerar como adiantamento, se for mes 12, considerar o pagamento integral
var valorTotal = dependencia.getValorPeriodo(''decimo_pago'',dataInicio.mes,dataInicio.ano,dataFim.mes,dataFim.ano)
evento.valorCalculado = valorTotal;
if(evento.valorCalculado <= 0 )
    evento.aplicavel = false', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (53, 'Adiantamento de 13º Salário', true, 2, '
var dataInicio = utilitarios.dateMax(utilitarios.dataPeriodo(1,parametros.ano),consulta.dataAdmissao);
var demissao = consulta.dataDemissao
var dataFimPeriodo = utilitarios.dataPeriodo(12,parametros.ano)
var dataFim = (demissao?utilitarios.dateMin(dataFimPeriodo,demissao):dataFimPeriodo);

//Data limite do adiantamento é 30/11
//Data limite do pagamento é 20/12
//Então se é antes do mes 12, considerar como adiantamento, se for mes 12, considerar o pagamento integral
var valorTotal = dependencia.getProjecao(''decimo'',dataInicio.mes,dataInicio.ano,dataFim.mes,dataFim.ano)
var valorJaPago = 0
evento.valorCalculado = valorTotal/2;
', false, true);
INSERT INTO evento (id, nome, visivel_folha, tipo, script, padrao, ativo) VALUES (5, 'IRRF', true, 3, 'var tabela = consulta.getTabela("IRRF");

var vencimentos = eventos.bases + eventos.proventos;

if(verificaAplicavel())
    calcular()
function verificaAplicavel(){
    var minimo = -1;
    for each(var item in tabela){
        if(minimo ===-1 || minimo > item.valorInicial){
            minimo = item.valorInicial;
        }
    }
    if(minimo > vencimentos){
        evento.aplicavel = false;
        return false;
    }else{
        return true;
    }
}
function aplicar(item){
    if(!item)
    {
        evento.aplicavel = false;
        return;
    }
   var aliquota = item.aliquota /100;
    var venc = vencimentos;
    
    var desconto = 0;
    var quantidadeDependentes = consulta.dependentes.size();
    if(quantidadeDependentes > 0){
        desconto += valorDependente() * quantidadeDependentes;
    }
    var baseIrrf = venc - dependencia.get(''inss'').valorCalculado
    var r = (baseIrrf * aliquota - item.desconto) - desconto;
    if(r < 0)
        r = 0;
    evento.valorCalculado = r;
    evento.referencia = (item.aliquota);
    folha.baseIrrf = baseIrrf
   
}
function valorDependente(){
    var t = consulta.getTabela(''IRRF DEP'');
    for each(var a in t){
        return a.desconto;
    }
}
function calcular(){
    for each(var item in tabela){
        if(item.valorInicial <= vencimentos && (!item.valorFinal  || item.valorFinal >= vencimentos)){
            aplicar(item);
            return;
        }
    }
}', true, true);


--
-- TOC entry 2198 (class 0 OID 46668)
-- Dependencies: 251
-- Data for Name: evento_dependencia; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('inss', 3, 5);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('ferias', 7, 47);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('ferias', 6, 48);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('inss', 3, 50);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('decimo', 8, 52);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('decimo', 8, 53);
INSERT INTO evento_dependencia (nomevariavel, eventodependencia_id, evento_id) VALUES ('decimo_pago', 53, 54);


--
-- TOC entry 2196 (class 0 OID 45555)
-- Dependencies: 171
-- Data for Name: parametro; Type: TABLE DATA; Schema: public; Owner: postgres
--

INSERT INTO parametro (chave, valor) VALUES ('eventos_ferias', '2,3,47,48');
INSERT INTO parametro (chave, valor) VALUES ('eventos_decimo_adiantamento', '53');
INSERT INTO parametro (chave, valor) VALUES ('eventos_decimo', '3,5,51,52,54');


-- Completed on 2016-05-15 18:54:07

--
-- PostgreSQL database dump complete
--

