(function () {
    'use strict';
    angular.module('app').controller('FuncionarioFeriasEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosFerias', '$mdDialog', FuncionarioFeriasEditController]);

    var state = "funcionario-Ferias"
    function FuncionarioFeriasEditController($mdToast, $http, $state, $stateParams, Workspace, DadosFerias, $mdDialog) {
        var vm = this;
        vm.entity = DadosFerias
        
        console.log(vm.entity)
        if (vm.entity.hasOwnProperty("dataGozoInicio"))
            vm.entity.dataGozoInicio = Workspace.toDate(vm.entity.dataGozoInicio)
        if (vm.entity.hasOwnProperty("dataGozoFim"))
            vm.entity.dataGozoFim = Workspace.toDate(vm.entity.dataGozoFim)
            
        if (vm.entity.hasOwnProperty("dataAquisitivoInicio"))
            vm.entity.dataAquisitivoInicio = Workspace.toDate(vm.entity.dataAquisitivoInicio)
        if (vm.entity.hasOwnProperty("dataAquisitivoFim"))
            vm.entity.dataAquisitivoFim = Workspace.toDate(vm.entity.dataAquisitivoFim)
         vm.save = save;
        vm.cancel = cancel;

       
        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }


    }

})()