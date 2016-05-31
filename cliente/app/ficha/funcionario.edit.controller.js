(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEditController', ['$mdToast', '$http', 'Funcionario', '$state', '$stateParams', 'Workspace', '$mdDialog', 'Cor', 'EstadoCivil','Escolaridade', FuncionarioEditController]);

    var state = "ficha"
    function FuncionarioEditController($mdToast, $http, Funcionario, $state, $stateParams, Workspace, $mdDialog, Cor, EstadoCivil,Escolaridade) {
        var vm = this;
        vm.entity = {}

        vm.cor = []
        vm.escolaridade = []

        loadSexos()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
        Workspace.title = "Manutenção de Funcionario";
        console.log($stateParams)
        vm.mostraAddCargo = mostraAddCargo;
        vm.mostraEditCargo = mostraEditCargo
        vm.mostraAddQualificacao = mostraAddQualificacao;
        vm.mostraEditQualificacao = mostraEditQualificacao
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Funcionario.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Funcionario()
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

                        if (!vm.entity.funcionarioCargos)
                            vm.entity.funcionarioCargos = []
                        vm.entity.funcionarioCargos.push(adicionado)
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

                        if (!vm.entity.funcionarioQualificacaos)
                            vm.entity.funcionarioQualificacaos = []
                        vm.entity.funcionarioQualificacaos.push(adicionado)
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

        function loadCor() {
            Cor.query().$promise.then(function (resposta) {
                vm.cor = resposta;
            })

        }

        function loadSexos() {
            $http.get('data/recrutamento/sexo.json').then(function (resposta) {
                vm.sexos = (resposta.data)
            })
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