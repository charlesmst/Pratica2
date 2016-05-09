(function () {
    'use strict';
    angular.module('app').controller('EmpresaEditController', ['$mdToast', '$http', 'Empresa', '$state', '$stateParams', 'Workspace', 'Cidade', EmpresaEditController]);

    var state = "empresa"
    function EmpresaEditController($mdToast, $http, Empresa, $state, $stateParams, Workspace, Cidade) {
        var vm = this;
        vm.entity = {}
        vm.querySearchCidade = querySearchCidade

        Workspace.title = "Manutenção de Empresa";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Empresa.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
                vm.entity.dataFundacao = Workspace.toDate(vm.entity.dataFundacao)
            }))

        } else
            vm.entity = new Empresa()
 
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }

     
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }

        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }
    }

})()