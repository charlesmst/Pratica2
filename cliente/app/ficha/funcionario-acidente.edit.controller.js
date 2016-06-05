(function () {
    'use strict';
    angular.module('app').controller('FuncionarioAcidenteEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosAcidente', '$mdDialog', FuncionarioAcidenteEditController]);

    function FuncionarioAcidenteEditController($mdToast, $http, $state, $stateParams, Workspace, DadosAcidente, $mdDialog) {
        var vm = this;
        vm.entity = DadosAcidente

        console.log(vm.entity)
        if (vm.entity.hasOwnProperty("dataOcorrencia"))
            vm.entity.dataOcorrencia = Workspace.toDate(vm.entity.dataOcorrencia)

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