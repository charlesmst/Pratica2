(function () {
    'use strict';
    angular.module('app').controller('FuncionarioFaixaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosFaixa', '$mdDialog', FuncionarioFaixaEditController]);

    var state = "funcionario-Faixa"
    function FuncionarioFaixaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosFaixa, $mdDialog) {
        var vm = this;
        vm.entity = DadosFaixa
        
        console.log(vm.entity)
        if (vm.entity.hasOwnProperty("dataInicio"))
            vm.entity.dataInicio = Workspace.toDate(vm.entity.dataInicio)
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

})();