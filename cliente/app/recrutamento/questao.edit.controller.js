(function () {
    'use strict';
    angular.module('app').controller('QuestaoEditController', ['$mdToast', '$http', 'Questao', '$state', '$stateParams', 'Workspace', QuestaoEditController]);

    var state = "questao"
    function QuestaoEditController($mdToast, $http, Questao, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Questões";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Questao.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Questao()
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