(function () {
    'use strict';
    angular.module('app').controller('CurriculoEditController', ['$mdToast', '$http', 'Curriculo', '$state', '$stateParams', 'Workspace', CurriculoEditController]);

    var state = "curriculo"
    function CurriculoEditController($mdToast, $http, Curriculo, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Currículo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Curriculo.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Curriculo()
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