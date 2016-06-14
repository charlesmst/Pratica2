(function () {
    'use strict';
    angular.module('app').controller('CargoEditController', ['$mdToast', '$http', 'Cargo', '$state', '$stateParams', 'Workspace', 'Cbo', CargoEditController]);

    var state = "cargo"
    function CargoEditController($mdToast, $http, Cargo, $state, $stateParams, Workspace, Cbo) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Cargo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Cargo.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data;
            }))

        } else {
            vm.entity = new Cargo()
            loadCargos()
        }
        vm.save = save;
        vm.cancel = cancel;
        vm.querySearch = querySearch;
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

        function querySearch(textoBusca) {
            return Cbo.query({
                filter: textoBusca
            }).$promise
        }
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargo = resposta;
            })
        }
    }

})()