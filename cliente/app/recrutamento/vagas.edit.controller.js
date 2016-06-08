(function () {
    'use strict';
    angular.module('app').controller('VagasEditController', ['$mdToast', '$http', 'Vagas', '$state', '$stateParams', 'Workspace', 'Cargo', 'PlanoAvaliacao', 'Entrevista', '$mdDialog', VagasEditController]);

    var state = "vagas"
    function VagasEditController($mdToast, $http, Vagas, $state, $stateParams, Workspace, Cargo, PlanoAvaliacao, Entrevista, $mdDialog) {
        var vm = this;
        vm.entity = {}
        vm.saveCandidato = saveCandidato
        vm.mostraEditCandidato = mostraEditCandidato
        vm.querySearchPlano = querySearchPlano
        vm.mostraAddEntrevista = mostraAddEntrevista
        vm.mostraEditEntrevista = mostraEditEntrevista
        vm.cargos = []
        vm.tipos = []
        Workspace.title = "Manutenção de Vagas";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Vagas.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
                vm.entity.dataInicio = Workspace.toDate(vm.entity.dataInicio)

                vm.entity.dataFim = Workspace.toDate(vm.entity.dataFim)
                console.log(data)
            }))

        } else
            vm.entity = new Vagas()

        loadTipos()
        loadCargos()
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
        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta;
            })
        }

        function mostraEditCandidato(candidato) {
            vm.candidato = candidato
        }

        function querySearchPlano(texto) {
            return PlanoAvaliacao.query({
                filter: texto,
                limit: 10
            }).$promise
        }

        function loadTipos() {
            $http.get('data/recrutamento/tipoRecrutamento.json').then(function (resposta) {
                vm.tipos = (resposta.data)

            })
        }

        function mostraAddEntrevista() {
            $mdDialog.show({
                controller: 'CurriculoFormacaoEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-formacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFormacao: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.pessoa.curriculoFormacoes)
                            vm.entity.pessoa.curriculoFormacoes = []
                        vm.entity.pessoa.curriculoFormacoes.push(adicionado)
                    });
        }

        function mostraEditEntrevista(entrevista) {
            $mdDialog.show({
                controller: 'EntrevistaEditController as modalVm',
                templateUrl: 'app/recrutamento/entrevista.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosEntrevista: function () {
                        return angular.copy(entrevista);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(entrevista, alterado)
            })
        }

        function saveCandidato($event, $valid) {
            if (!$valid)
                return;
            vm.candidato = undefined

        }

    }

})()