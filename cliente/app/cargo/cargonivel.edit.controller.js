(function () {
    'use strict';
    angular.module('app').controller('CargoNivelEditController', ['$mdToast', '$http', 'CargoNivel', '$state', '$stateParams', 'Workspace','Cargo', CargoNivelEditController]);

    var state = "cargonivel"
    function CargoNivelEditController($mdToast, $http, CargoNivel, $state, $stateParams, Workspace, Cargo ) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Nível do Cargo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", CargoNivel.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data;
            }))

        } else
            vm.entity = new CargoNivel()
//            loadCargoNivel();
        vm.save = save;
        vm.cancel = cancel;
//        vm.querySearch = querySearch;
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

//    function querySearch(textoBusca) {
//        return Cargo.query({
//            filter: textoBusca
//        }).$promise
//    }
//        function loadCargoNivel() {
//        CargoNivel.query().$promise.then(function (resposta) {
//            vm.cargo = resposta;
//        })
//    }
})()