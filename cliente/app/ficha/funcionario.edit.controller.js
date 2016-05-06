(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEditController', ['$mdToast', '$http', 'Funcionario', '$state', '$stateParams', 'Workspace', FuncionarioEditController]);

	var state = "funcionario"
    function FuncionarioEditController($mdToast, $http, Funcionario, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Funcionario";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Funcionario.get({id: $stateParams.id}).$promise.then(function (data) {
                
                vm.entity = data
            }))

        }
        else
            vm.entity = new Funcionario()
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

    }

})()