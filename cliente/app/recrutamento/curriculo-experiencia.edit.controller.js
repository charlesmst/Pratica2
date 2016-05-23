(function () {
    'use strict';
    angular.module('app').controller('CurriculoExperienciaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosExperiencia', '$mdDialog', CurriculoExperienciaEditController]);

    var state = "curriculo-experiencia"
    function CurriculoExperienciaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosExperiencia, $mdDialog) {
        var vm = this;
        vm.entity = DadosExperiencia
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