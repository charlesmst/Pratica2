(function () {
    'use strict';
    angular.module('app').controller('CurriculoFormacaoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', '$mdDialog', CurriculoFormacaoEditController]);

    var state = "funcionario-cargo"
    function CurriculoFormacaoEditController($mdToast, $http, $state, $stateParams, Workspace, $mdDialog) {
        var vm = this;
        vm.entity = CurriculoFormacao;
//        if (vm.entity.dataEntrada)
//            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)
//        
//        if (vm.entity.dataSaida)
//            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
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