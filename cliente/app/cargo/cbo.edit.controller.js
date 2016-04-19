(function () {
    'use strict';
    angular.module('app').controller('CboEditController', ['$mdToast', '$http', 'Cbo', '$state', '$stateParams', 'Workspace', 'Nivel', CboEditController]);

    var state = "cbo"
    function CboEditController($mdToast, $http, Cbo, $state, $stateParams, Workspace, Nivel) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Cbo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Cbo.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data;
                vm.entity.niveis = []

            }))

        }
        else {
            vm.entity = new Cbo();
            vm.entity.niveis = []
        }
        vm.save = save;
        vm.cancel = cancel;
        vm.querySearch = querySearch;
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
        function querySearch(textoBusca) {
            return Nivel.query({
                filter: textoBusca
            }).$promise

        }
    }

})()