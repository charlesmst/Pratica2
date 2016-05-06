(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEventoEditController', ['$mdToast', '$http', 'EventoFuncionario', '$state', '$stateParams', 'Workspace', FuncionarioEventoEditController]);

    function FuncionarioEventoEditController($mdToast, $http, EventoFuncionario, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        if ($stateParams.id) {
            Workspace.loading("Carregando...", EventoFuncionario.get({id: $stateParams.id}).$promise.then(function (data) {
                
                vm.entity = data
            }))

        }
        else
            vm.entity = new EventoFuncionario()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go('^')
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go('^')

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }

    }

})()