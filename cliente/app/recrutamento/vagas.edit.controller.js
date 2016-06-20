(function () {
    'use strict';
    angular.module('app').controller('VagasEditController', ['$mdToast', '$http', 'Vagas', '$state', '$stateParams', 'Workspace', 'Cargo', 'PlanoAvaliacao', 'Entrevista', '$mdDialog', 'config', VagasEditController]);

    var state = "vagas"
    function VagasEditController($mdToast, $http, Vagas, $state, $stateParams, Workspace, Cargo, PlanoAvaliacao, Entrevista, $mdDialog, config) {
        var vm = this;
        vm.entity = {}
        vm.saveCandidato = saveCandidato
        vm.mostraEditCandidato = mostraEditCandidato
        vm.querySearchPlano = querySearchPlano
        vm.mostraAddEntrevista = mostraAddEntrevista
        vm.mostraEditEntrevista = mostraEditEntrevista
        vm.getImage = getImage;
        vm.cargos = []
        vm.tipos = []
        Workspace.title = "Vagas";
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
            Workspace.showMessage("Registro Salvo!")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
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

        function mostraAddEntrevista(candidato) {
            $mdDialog.show({
                controller: 'EntrevistaEditController as modalVm',
                templateUrl: 'app/recrutamento/entrevista.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosEntrevista: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.candidatos[candidato].entrevistas[0])
                            vm.entity.candidatos[candidato].entrevistas = []
                        vm.entity.candidatos[candidato].entrevistas[0] = adicionado
//                        vm.entity.candidatos.entrevistas = []
//                        vm.entity.candidatos.entrevistas.push(adicionado)
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
        function getImage(row) {
            return config.imageUrl + "/" + (row.imagem || "0.jpg")
        }

    }

})()