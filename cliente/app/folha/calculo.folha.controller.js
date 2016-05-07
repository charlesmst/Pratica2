(function () {
    'use strict';
    angular.module('app').controller('CalculoFolhaController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'Empresa', 'Cargo', '$scope', '$parse', 'Evento', '$q', '$mdDialog', CalculoFolhaController]);

    function CalculoFolhaController($mdToast, $http, $state, $stateParams, Workspace, Empresa, Cargo, $scope, $parse, Evento, $q, $mdDialog) {
        var vm = this;
        vm.empresas = []
        vm.cargos = []
        Workspace.title = "Cálculo de folha";
        vm.save = save;
        vm.loadFuncionarios = loadFuncionarios;

        vm.entity = angular.extend(new Evento(), {
            ano: new Date().getFullYear(),
            empresa: undefined,
            funcionarios: [],
            eventos: [{referencia: 0}],
            tipo: 1
        });

        $scope.$watch('crudVm.entity.empresa', function () {
            if (vm.entity.empresa) {
                loadFuncionarios();
            }
        });
        loadEmpresas();
        function save($event, $valid) {
            if (!$valid)
                return;
            switch (parseInt(vm.entity.tipo)) {
                case 1:
                    calculaMes($event);
                    break;
                case 2:
                    calculaFerias($event);
                    break;
                case 3:
                    calculaComplementar($event);
                    break;
            }
        }
        function calculaFerias($event) {
            calculaMes($event)
        }
        function calculaMes($event) {
            verificarCalculado($event).then(function () {
                Workspace.loading("Cálculando...", vm.entity.$calcularMes(callbackSave, callbackError).$promise)

            });
        }
        function calculaComplementar($event) {
            $mdDialog.show({
                controller: "CalculoFolhaDialogController as modalVm",
                templateUrl: 'app/folha/calculo.folha.modal.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: $event,
                resolve: {
                    CalculoData: function () {
                        return vm.entity
                    }
                }
            }).then(function (r) {
                vm.entity.eventos = r;

                Workspace.loading("Cálculando...", vm.entity.$calcularMes(callbackSave, callbackError).$promise)
            })
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

        function loadEmpresas() {
            Empresa.listagem().$promise.then(function (r) {
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