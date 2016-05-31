(function () {
    'use strict';
    angular.module('app').controller('SindicatoEditController', ['$mdToast', '$http', 'Sindicato', '$state', '$stateParams', 'Workspace', SindicatoEditController]);

    var state = "sindicato"
    function SindicatoEditController($mdToast, $http, Sindicato, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Sindicatos";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Sindicato.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Sindicato()
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

    }

})()