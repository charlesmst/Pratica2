(function () {
    'use strict';
    angular.module('app').controller('FuncionarioCargoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosCargo', '$mdDialog', FuncionarioCargoEditController]);

    var state = "funcionario-cargo"
    function FuncionarioCargoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosCargo, $mdDialog) {
        var vm = this;
        vm.entity = DadosCargo
        if (vm.entity.dataEntrada)
            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)
        
        if (vm.entity.dataSaida)
            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
        vm.save = save;
        vm.cancel = cancel;
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