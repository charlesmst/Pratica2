(function () {
    'use strict';
    angular.module('app').controller('FuncionarioFaltaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosFalta', '$mdDialog','MotivoFalta',  FuncionarioFaltaEditController]);

    function FuncionarioFaltaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosFalta, $mdDialog,MotivoFalta) {
        var vm = this;
        vm.entity = DadosFalta

        if (vm.entity.hasOwnProperty("dataInicio"))
            vm.entity.dataInicio = Workspace.toDate(vm.entity.dataInicio)
        
        if (vm.entity.hasOwnProperty("dataFinal"))
            vm.entity.dataFinal = Workspace.toDate(vm.entity.dataFinal)
            
        if (vm.entity.hasOwnProperty("dataRecebimento"))
            vm.entity.dataRecebimento = Workspace.toDate(vm.entity.dataRecebimento)
        vm.save = save;
        vm.cancel = cancel;

        loadMotivoFalta()

        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function loadMotivoFalta() {
            MotivoFalta.query().$promise.then(function (r) {
                vm.motivosFalta = r
            })
        }
        function cancel() {
            $mdDialog.cancel()
        }
    }

})();