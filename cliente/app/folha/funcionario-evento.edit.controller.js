(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEventoEditController', ['$mdToast', '$http', 'EventoFuncionario', '$state', '$stateParams', 'Workspace', 'Evento', FuncionarioEventoEditController]);

    function FuncionarioEventoEditController($mdToast, $http, EventoFuncionario, $state, $stateParams, Workspace, Evento) {
        var vm = this;
        vm.eventos = []
        vm.entity = {}

        vm.entity = new EventoFuncionario()
        vm.entity.dataInicio = new Date();
        vm.mes = vm.entity.dataInicio.getMonth();
        vm.ano = vm.entity.dataInicio.getFullYear();

        vm.save = save;
        vm.cancel = cancel;
        loadEventos()
        function loadEventos() {
            Evento.query().$promise.then(function (r) {
                vm.eventos = r;
            })
        }
        function save($event, $valid) {
            if (!$valid)
                return;
            vm.entity.dataInicio = new Date(vm.ano, vm.mes - 1, 1);
            if (vm.mesFim > 0 && vm.anoFim >0 && !vm.entity.mensal) {
                vm.entity.dataFim = new Date(vm.anoFim, vm.mesFim - 1, 1);
            } else
                vm.entity.dataFim = null;
            vm.entity.funcionarioCargo = {
                id: $stateParams.idCargo
            }
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go('^')
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro Salvo!")
            $state.go('^')

        }
        function callbackError(r) {
            Workspace.showError(r.data.mensagem)
        }


    }

})()