(function () {
    'use strict';
    angular.module('app').controller('CalculoFolhaController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'Empresa', 'Cargo', '$scope','$parse', CalculoFolhaController]);

    function CalculoFolhaController($mdToast, $http, $state, $stateParams, Workspace, Empresa, Cargo, $scope,$parse) {
        var vm = this;
        vm.meses = []
        vm.empresas = []
        vm.cargos = []
        Workspace.title = "Cálculo de folha";
        vm.save = save;
        vm.loadFuncionarios = loadFuncionarios;

        vm.entity = {
            ano: new Date().getFullYear(),
            empresa: undefined,
            funcionarios:[]
        };

        $scope.$watch('crudVm.entity.empresa', function () {
            if (vm.entity.empresa) {
                loadFuncionarios();
            }
        });
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
                vm.meses = r.data
            })
        }
        function loadEmpresas() {
            Empresa.query().$promise.then(function (r) {
                vm.empresas = r;
            })
        }
        function loadFuncionarios() {
            Cargo.cargosFuncionarioEmpresa({
                empresa: vm.entity.empresa.id
            }).$promise.then(function (r) {
                vm.cargos = r;
            })
        }

    }

})()