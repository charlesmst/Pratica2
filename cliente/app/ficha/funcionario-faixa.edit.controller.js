(function () {
    'use strict';
    angular.module('app').controller('FuncionarioFaixaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosFaixa', '$mdDialog', 'funcionarioAtivo', 'CargoNivel', FuncionarioFaixaEditController]);

    var state = "funcionario-Faixa"
    function FuncionarioFaixaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosFaixa, $mdDialog, funcionarioAtivo, CargoNivel) {
        var vm = this;
        vm.entity = DadosFaixa

        if (vm.entity.hasOwnProperty("dataInicio"))
            vm.entity.dataInicio = Workspace.toDate(vm.entity.dataInicio)
        vm.save = save;
        vm.cancel = cancel;
        
        console.log(funcionarioAtivo)
        loadCargoNivel()

        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function loadCargoNivel(){
            CargoNivel.doCargo({"cargoid":funcionarioAtivo.cargo.id}).$promise.then(function(r){
                vm.cargosNiveis = r
            })
        }
        function cancel() {
            $mdDialog.cancel()
        }
    }

})();