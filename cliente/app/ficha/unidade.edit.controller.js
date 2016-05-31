(function () {
    'use strict';
    angular.module('app').controller('UnidadeEditController', ['$mdToast', '$http', 'Unidade', '$state', '$stateParams', 'Workspace', UnidadeEditController]);

    var state = "unidade"
    function UnidadeEditController($mdToast, $http, Unidade, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Unidades";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Unidade.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Unidade()
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