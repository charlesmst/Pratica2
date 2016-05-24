(function () {
    'use strict';
    angular.module('app').controller('CargoEditController', ['$mdToast', '$http', 'Cargo', '$state', '$stateParams', 'Workspace', CargoEditController]);

    var state = "cargo"
    function CargoEditController($mdToast, $http, Cargo, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Cargo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Cargo.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data;
                vm.entity.niveis = []
            }))

        } else
            vm.entity = new Cargo()
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
        
        loadCbos()

        function DialogController($scope, $mdDialog, Cbo) {
            var modalVm = $scope;
            modalVm.loadCbos = loadCbos;
        }
        function loadCbos() {
            Cbo.query().$promise.then(function (resposta) {
                vm.cbo = resposta.data
            })
        }
    }

})()