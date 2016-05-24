(function () {
    'use strict';
    angular.module('app').controller('FuncionarioCargoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosCargo', '$mdDialog', 'Cargo', FuncionarioCargoEditController]);

    var state = "funcionario-cargo"
    function FuncionarioCargoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosCargo, $mdDialog, Cargo) {
        var vm = this;
        vm.entity = DadosCargo
        
        loadCargos()
        
        if (vm.entity.dataEntrada)
            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)
        
        if (vm.entity.dataSaida)
            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
        vm.save = save;
        vm.cancel = cancel;
        
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta;
            })
        }
        
        function save($event, $valid) {
            if (!$valid)
                return;

            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }


    }

})()