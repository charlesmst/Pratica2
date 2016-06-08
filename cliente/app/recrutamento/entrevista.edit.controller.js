(function () {
    'use strict';
    angular.module('app').controller('EntrevistaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosEntrevista', '$mdDialog', EntrevistaEditController]);

    var state = "entrevista"
    function EntrevistaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosEntrevista, $mdDialog) {
        var vm = this;
        vm.entity = DadosEntrevista
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