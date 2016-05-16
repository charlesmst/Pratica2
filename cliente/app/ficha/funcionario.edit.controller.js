(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEditController', ['$mdToast', '$http', 'Funcionario', '$state', '$stateParams', 'Workspace', '$mdDialog', FuncionarioEditController]);

    var state = "ficha"
    function FuncionarioEditController($mdToast, $http, Funcionario, $state, $stateParams, Workspace, $mdDialog) {
        var vm = this;
        vm.entity = {}
        vm.id = $stateParams.id;
        Workspace.title = "Manutenção de Funcionario";
        console.log($stateParams)
        vm.mostraAddCargo = mostraAddCargo;
        vm.mostraEditCargo = mostraEditCargo
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Funcionario.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Funcionario()
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

        function mostraAddCargo() {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.funcionarioCargos)
                            vm.entity.funcionarioCargos = []
                        vm.entity.funcionarioCargos.push(adicionado)
                    });
        }

        function mostraEditCargo(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }
    }

})()