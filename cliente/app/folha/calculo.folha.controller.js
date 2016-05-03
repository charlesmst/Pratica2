(function () {
    'use strict';
    angular.module('app').controller('CalculoFolhaController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'Empresa', 'Cargo', '$scope', '$parse', 'Evento', '$q', CalculoFolhaController]);

    function CalculoFolhaController($mdToast, $http, $state, $stateParams, Workspace, Empresa, Cargo, $scope, $parse, Evento, $q) {
        var vm = this;
        vm.meses = []
        vm.empresas = []
        vm.cargos = []
        Workspace.title = "Cálculo de folha";
        vm.save = save;
        vm.loadFuncionarios = loadFuncionarios;

        vm.entity = angular.extend(new Evento(), {
            ano: new Date().getFullYear(),
            empresa: undefined,
            funcionarios: []
        });

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
            switch (vm.entity.tipo){
                case "1":
                    calculaMes($event);
                    break;
                case "2":
                    calculaFerias($event);
                    break;
            }
        }
        function calculaFerias($event){
            calculaMes($event)
        }
        function calculaMes($event){
            verificarCalculado($event).then(function () {
                Workspace.loading("Cálculando...", vm.entity.$calcularMes(callbackSave, callbackError).$promise)

            });
        }
        function verificarCalculado($event) {
            return $q(function (resolve, reject) {
                vm.entity.$verificarJaCalculado().then(function (r) {
                    if (!r.data.sucesso) {
                        Workspace.showConfirmDialog($event, 'Atenção', r.data.mensagem + ", deseja continuar?").then(resolve, reject)
                    } else
                        resolve();
                })
            })

        }
        function callbackSave(r) {
            Workspace.showMessage("Cálculo efetuado com sucesso");
        }
        function callbackError(r) {
            Workspace.showError(r.data.mensagem);
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
                //Coloca o id do cargo no funcionario
                angular.forEach(r, function (cargo) {
                    angular.forEach(cargo.funcionarioCargos, function (f) {
                        f.cargo = {
                            id: cargo.id
                        };
                    })
                })
                vm.cargos = r;
            })
        }

    }

})()