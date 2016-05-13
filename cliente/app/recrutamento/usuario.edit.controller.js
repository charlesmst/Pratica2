(function () {
    'use strict';
    angular.module('app').controller('UsuarioEditController', ['$mdToast', '$http', 'Usuario', '$state', '$stateParams', 'Workspace', 'Cidade', 'Escolaridade', UsuarioEditController]);

    var state = "usuario"
    function UsuarioEditController($mdToast, $http, Usuario, $state, $stateParams, Workspace, Cidade, Escolaridade) {
        var vm = this;
        vm.entity = {}
        vm.sexos = []
        vm.escolaridades = []
        vm.senhaConf
        vm.querySearchCidade = querySearchCidade

        Workspace.title = "Manutenção de Usuário";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Usuario.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
                vm.entity.pessoa.dataNascimento = Workspace.toDate(vm.entity.pessoa.dataNascimento)
            }))

        } else
            vm.entity = new Usuario()

        loadSexos()
        loadEscolaridades()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function loadSexos() {
            $http.get('data/recrutamento/sexo.json').then(function (resposta) {
                vm.sexos = (resposta.data)

            })
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
        
        function loadEscolaridades() {
            Escolaridade.query().$promise.then(function (resposta) {
                vm.escolaridades = resposta;
            })
        }

        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }

    }

})()