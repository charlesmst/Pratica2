(function () {
    'use strict';
    angular.module('app').controller('FuncionarioCargoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosCargo', '$mdDialog', 'Cargo', 'Unidade', 'Empresa', 'Sindicato', FuncionarioCargoEditController]);

    var state = "funcionario-cargo"
    function FuncionarioCargoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosCargo, $mdDialog, Cargo, Unidade, Empresa, Sindicato) {
        var vm = this;
        vm.entity = DadosCargo

        loadCargos()
        loadUnidades()
        loadEmpresas()
        loadSindiacatos()

        if (vm.entity.dataEntrada)
            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)

        if (vm.entity.dataSaida)
            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
        vm.save = save;
        vm.cancel = cancel;

        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta;
            })
        }

        function loadUnidades() {
            Unidade.query().$promise.then(function (resposta) {
                vm.unidades = resposta;
            })
        }

        function loadEmpresas() {
            Empresa.query().$promise.then(function (resposta) {
                vm.empresas = resposta;
            })
        }
        
        function loadSindiacatos() {
            Sindicato.query().$promise.then(function (resposta) {
                vm.sindicatos = resposta;
            })
        }

        function save($event, $valid) {
            if (!$valid)
                return;
            
            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }


    }

})()