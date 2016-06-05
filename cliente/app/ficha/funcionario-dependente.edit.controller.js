(function () {
    'use strict';
    angular.module('app').controller('FuncionarioDependenteEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosDependente', '$mdDialog','Pessoa', FuncionarioDependenteEditController]);

    var state = "funcionario-Dependente"
    function FuncionarioDependenteEditController($mdToast, $http, $state, $stateParams, Workspace, DadosDependente, $mdDialog,Pessoa) {
        var vm = this;
        vm.entity = DadosDependente

        if (vm.entity.hasOwnProperty("dataInicial"))
            vm.entity.dataInicial = Workspace.toDate(vm.entity.dataInicial)
        
        if (vm.entity.hasOwnProperty("dataFim"))
            vm.entity.dataFim = Workspace.toDate(vm.entity.dataFim)
        
        vm.save = save;
        vm.cancel = cancel;

        loadPessoas()

        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function loadPessoas() {
            Pessoa.query().$promise.then(function (r) {
                vm.pessoas = r
            })
        } 
        function cancel() {
            $mdDialog.cancel()
        }
    }

})();