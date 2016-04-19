(function () {
    'use strict';
    angular.module('app').controller('EventoEditController', ['$mdToast', '$http', 'Evento', '$state', '$stateParams', 'Workspace', EventoEditController]);
    var state = "evento"

    function EventoEditController($mdToast, $http, Evento, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Evento";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Evento.get({id: $stateParams.id}).$promise.then(function (data) {
                
                vm.entity = data
            }))

        }
        else
            vm.entity = new Evento()
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

    }

})()