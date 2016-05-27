(function () {
    'use strict';
    angular.module('app').controller('EventoCargoEditController', ['$mdToast', '$http', 'CargoHasEvento', '$state', '$stateParams', 'Workspace', 'Evento', EventoCargoEditController]);

    function EventoCargoEditController($mdToast, $http, CargoHasEvento, $state, $stateParams, Workspace, Evento) {
        var vm = this;
        vm.eventos = []
        vm.entity = {}

        vm.entity = new CargoHasEvento()
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
            if (vm.mesFim > 0 && vm.anoFim >0 ) {
                vm.entity.dataFim = new Date(vm.anoFim, vm.mesFim - 1, 1);
            } else
                vm.entity.dataFim = null;
            vm.entity.cargo = {
                id: $stateParams.id
            }
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go('^')
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go('^')

        }
        function callbackError(r) {
            Workspace.showError(r.data.mensagem)
        }


    }

})()