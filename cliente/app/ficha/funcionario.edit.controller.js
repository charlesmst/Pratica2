(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEditController', ['$mdToast', '$http', 'Pessoa', '$state', '$stateParams', 'Workspace', '$mdDialog', 'Cor', 'EstadoCivil', 'Escolaridade', 'Cidade', 'Banco', 'TipoSanguineo', 'VinculoEmpregaticio', FuncionarioEditController]);

    var state = "ficha"
    function FuncionarioEditController($mdToast, $http, Pessoa, $state, $stateParams, Workspace, $mdDialog, Cor, EstadoCivil, Escolaridade, Cidade, Banco, TipoSanguineo, VinculoEmpregaticio) {
        var vm = this;
        vm.entity = {}
        vm.mostraAddCargo = mostraAddCargo;
        vm.mostraEditCargo = mostraEditCargo
        vm.mostraAddQualificacao = mostraAddQualificacao;
        vm.querySearchCidade = querySearchCidade
        vm.mostraEditQualificacao = mostraEditQualificacao
        vm.mostraEditFerias = mostraEditFerias
        vm.mostraAddFerias = mostraAddFerias
        vm.mostraEditFaixa = mostraEditFaixa
        vm.mostraAddFaixa = mostraAddFaixa
        vm.cor = []
        vm.escolaridade = []

        loadSexos()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
        loadBancos()
        loadTipoSanguineo()
        loadVinculos()

        Workspace.title = "Ficha Funcional";
        console.log($stateParams)
        
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Pessoa.get({ id: $stateParams.id }).$promise.then(function (data) {

                vm.entity = data
                if (vm.entity.funcionario && vm.entity.funcionario.funcionarioCargos.length > 0)
                    vm.funcionarioAtivo = vm.entity.funcionario.funcionarioCargos[0]
            }))

        } else
            vm.entity = new Pessoa()
        loadCor()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;

            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }



        function mostraAddCargo() {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return {};

                    }
                }

            })
                .then(function (adicionado) {
                    console.log("Resposta da modal", adicionado)

                    if (!vm.entity.funcionario.funcionarioCargos)
                        vm.entity.funcionario.funcionarioCargos = []
                    vm.entity.funcionario.funcionarioCargos.push(adicionado)
                });
        }

        function mostraEditCargo(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }


        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }

        function mostraAddQualificacao() {
            $mdDialog.show({
                controller: 'FuncionarioQualificacaoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-qualificacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosQualificacao: function () {
                        return {};

                    }
                }

            })
                .then(function (adicionado) {
                    console.log("Resposta da modal", adicionado)

                    if (!vm.funcionarioAtivo.funcionarioQualificacaos)
                        vm.funcionarioAtivo.funcionarioQualificacaos = []
                    vm.funcionarioAtivo.funcionarioQualificacaos.push(adicionado)
                });
        }

        function mostraEditQualificacao(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioQualificacaoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-qualificacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosQualificacao: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }

        function mostraAddFerias() {
            $mdDialog.show({
                controller: 'FuncionarioFeriasEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-ferias.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFerias: function () {
                        return {};

                    }
                }

            })
                .then(function (adicionado) {
                    console.log("Resposta da modal", adicionado)

                    if (!vm.funcionarioAtivo.feriases)
                        vm.funcionarioAtivo.feriases = []
                    vm.funcionarioAtivo.feriases.push(adicionado)
                });
        }

        function mostraEditFerias(ferias) {
            console.log ("mostraEditFerias",ferias)
            $mdDialog.show({
                controller: 'FuncionarioFeriasEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-ferias.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFerias: function () {
                        return angular.copy(ferias);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(ferias, alterado)
            })
        }
        
        
        function mostraAddFaixa() {
            $mdDialog.show({
                controller: 'FuncionarioFaixaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-faixa.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFaixa: function () {
                        return {};

                    }
                }

            })
                .then(function (adicionado) {
                    console.log("Resposta da modal", adicionado)

                    if (!vm.funcionarioAtivo.funcionarioFaixas)
                        vm.funcionarioAtivo.funcionarioFaixas = []
                    vm.funcionarioAtivo.funcionarioFaixas.push(adicionado)
                });
        }

        function mostraEditFaixa(Faixa) {
            console.log ("mostraEditFaixa",Faixa)
            $mdDialog.show({
                controller: 'FuncionarioFaixaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-faixa.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFaixa: function () {
                        return angular.copy(Faixa);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(Faixa, alterado)
            })
        }
        function loadCor() {
            Cor.query().$promise.then(function (resposta) {
                vm.cor = resposta;
            })

        }

        function loadVinculos() {
            VinculoEmpregaticio.query().$promise.then(function (resposta) {
                vm.vinculosEmpregaticios = resposta;
            })
        }

        function loadBancos() {
            Banco.query().$promise.then(function (resposta) {
                vm.bancos = resposta;
            })

        }
        function loadTipoSanguineo() {
            TipoSanguineo.query().$promise.then(function (resposta) {
                vm.tiposSanguineos = resposta;
            })

        }
        function loadSexos() {
            vm.sexos = [
                {
                    id: 0,
                    nome: "Feminino"
                }, {
                    id: 1,
                    nome: "Masculino"
                }
            ]
        }

        function loadCategoriasCnh() {
            $http.get('data/recrutamento/categoriasCnh.json').then(function (resposta) {
                vm.categoriasDeCnh = (resposta.data)
            })
        }

        function loadEscolaridades() {
            Escolaridade.query().$promise.then(function (resposta) {
                vm.escolaridades = resposta;
            })
        }

        function loadEstadosCivis() {
            EstadoCivil.query().$promise.then(function (resposta) {
                vm.estadosCivis = resposta;
            })
        }
    }


})()