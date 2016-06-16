(function () {
    'use strict';
    angular.module('app').controller('FuncionarioCargoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosCargo', '$mdDialog', 'Cargo', 'Unidade', 'Empresa', 'Sindicato','DemissaoTipo', FuncionarioCargoEditController]);

    var state = "funcionario-cargo"
    function FuncionarioCargoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosCargo, $mdDialog, Cargo, Unidade, Empresa, Sindicato,DemissaoTipo) {
        var vm = this;
        vm.entity = DadosCargo
        if (!DadosCargo.hasOwnProperty("ativo"))
            DadosCargo.ativo = true;
        console.log(vm.entity)
        loadCargos()
        loadUnidades()
        loadEmpresas()
        loadSindiacatos()
        loadDemissao()
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
        function loadDemissao() {
            DemissaoTipo.query().$promise.then(function (resposta) {
                vm.demissoes     = resposta;
            })
        }
        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }


    }

})()