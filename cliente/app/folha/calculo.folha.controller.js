(function () {
    'use strict';
    angular.module('app').controller('CalculoFolhaController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'Empresa', CalculoFolhaController]);

    function CalculoFolhaController($mdToast, $http, $state, $stateParams, Workspace, Empresa) {
        var vm = this;
        vm.entity = {
            ano: new Date().getFullYear()
        }
        vm.meses = []
        vm.empresas = []
        Workspace.title = "Cálculo de folha";
        vm.save = save;
        loadMes();
        loadEmpresas();
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Cálculando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function callbackSave(r) {
            Workspace.showMessage("Cálculo efetuado com sucesso");
        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro");
        }
        function loadMes() {
            $http.get("data/meses.json").then(function (r) {
                console.log(r)
                vm.meses = r.data
            })
        }
        function loadEmpresas() {
            Empresa.query().$promise.then(function (r) {
                vm.empresas = r
            })
        }

    }

})()