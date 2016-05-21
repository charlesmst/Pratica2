(function () {
    'use strict';
    angular.module('app').controller('CurriculoQualificacaoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosFormacao', '$mdDialog', CurriculoQualificacaoEditController]);

    var state = "curriculo-qualificacao"
    function CurriculoQualificacaoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosFormacao, $mdDialog) {
        var vm = this;
        vm.entity = DadosFormacao
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