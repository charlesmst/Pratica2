(function () {
    'use strict';
    angular.module('app').controller('CandidatoEditController', ['$mdToast', '$http', 'Candidato', '$state', '$stateParams', 'Workspace', CandidatoEditController]);

    var state = "candidato"
    function CandidatoEditController($mdToast, $http, Candidato, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Candidato";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Candidato.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Candidato()
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