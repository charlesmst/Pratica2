(function () {
    'use strict';
    angular.module('app').controller('NecessidadePessoaEditController', ['$mdToast', '$http', 'NecessidadePessoa', '$state', '$stateParams', 'Workspace', 'Cargo', NecessidadePessoaEditController]);

    var state = "necessidade-pessoa"
    function NecessidadePessoaEditController($mdToast, $http, NecessidadePessoa, $state, $stateParams, Workspace, Cargo) {
        var vm = this;
        vm.entity = {}
        vm.cargos = []
        
        vm.situacoes = []
        Workspace.title = "Manutenção de Necessidade de Pessoa";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", NecessidadePessoa.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new NecessidadePessoa()

        loadCargos()
        loadSituacoes()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid) {
                return;
            } else {
                vm.entity.usuario = null;
                vm.entity.dataRequisicao = null;
                Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
            }
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
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta;
            })
        }
        function loadSituacoes() {
            $http.get('data/recrutamento/statusNecessidadePessoa.json').then(function (resposta) {
                vm.situacoes = (resposta.data)

            })
        }
    }

})()