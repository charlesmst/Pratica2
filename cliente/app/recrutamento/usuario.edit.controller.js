(function () {
    'use strict';
    angular.module('app').controller('UsuarioEditController', ['$mdToast', '$http', 'Usuario', '$state', '$stateParams', 'Workspace','Authorization', UsuarioEditController]);

    var state = "usuario"
    function UsuarioEditController($mdToast, $http, Usuario, $state, $stateParams, Workspace,Authorization) {
        var vm = this;
        vm.sexos = []
        vm.entity = {}
        Workspace.title = "Manutenção de Usuário";

        vm.entity = new Usuario();

        vm.save = save;
        vm.cancel = cancel;

        loadSexos()
        function loadSexos() {
            $http.get('data/recrutamento/sexo.json').then(function (resposta) {
                vm.sexos = (resposta.data)
            })
        }

        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Usuário cadastrado com sucesso")
            Authorization.setCurrentUser(r);
            $state.go("curriculocandidato")

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }

    }

})()