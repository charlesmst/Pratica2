(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEventoEditController', ['$mdToast', '$http', 'EventoFuncionario', '$state', '$stateParams', 'Workspace', 'Evento', FuncionarioEventoEditController]);

    function FuncionarioEventoEditController($mdToast, $http, EventoFuncionario, $state, $stateParams, Workspace, Evento) {
        var vm = this;
        vm.eventos = []
        vm.entity = {}
        if ($stateParams.idCargo) {
            Workspace.loading("Carregando...", EventoFuncionario.get({ id: $stateParams.id }).$promise.then(function (data) {

                vm.entity = data
                if (vm.entity.dataInicio)
                    vm.entity.dataInicio = Workspace.toDate(vm.entity.dataInicio);
                if (vm.entity.dataFim)
                    vm.entity.dataFim = Workspace.toDate(vm.entity.dataFim);

                vm.mes = vm.entity.dataInicio.getMonth();
                vm.ano = vm.entity.dataInicio.getFullYear();
            }))

        }
        else {
            vm.entity = new EventoFuncionario()
            vm.entity.dataInicio = new Date();
            vm.mes = vm.entity.dataInicio.getMonth();
            vm.ano = vm.entity.dataInicio.getFullYear();
        }
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
            if (vm.entity.mensal)
                vm.entity.dataInicio = new Date(vm.ano, vm.mes - 1, 1);
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