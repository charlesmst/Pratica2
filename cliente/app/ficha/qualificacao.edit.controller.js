(function () {
    'use strict';
    angular.module('app').controller('QualificacaoEditController', ['$mdToast', '$http', 'Qualificacao', '$state', '$stateParams', 'Workspace', QualificacaoEditController]);

    var state = "qualificacao"
    function QualificacaoEditController($mdToast, $http, Qualificacao, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Qualificações";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Qualificacao.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Qualificacao()
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
            Workspace.showMessage("Registro Salvo!")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
        }
    }
})()