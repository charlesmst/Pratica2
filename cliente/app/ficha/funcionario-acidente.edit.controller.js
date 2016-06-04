(function () {
    'use strict';
    angular.module('app').controller('FuncionarioAcidenteEditController', ['$mdToast', '$http', 'FuncionarioAcidente', '$state', '$stateParams', 'Workspace','Cargo', FuncionarioAcidenteEditController]);

    var state = "funcionario-acidente"
    function FuncionarioAcidenteEditController($mdToast, $http, FuncionarioAcidente, $state, $stateParams, Workspace,Cargo) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Acidentes";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", FuncionarioAcidente.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new FuncionarioAcidente()
        
        loadCargos()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta;
            })
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

    }

})()