(function () {
    'use strict';
    angular.module('app').controller('VagasEditController', ['$mdToast', '$http', 'Vagas', '$state', '$stateParams', 'Workspace', VagasEditController]);

    var state = "vagas"
    function VagasEditController($mdToast, $http, Vagas, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        vm.tipos = []
        Workspace.title = "Manutenção de Vagas";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Vagas.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
                vm.entity.dataInicio= Workspace.toDate(vm.entity.dataInicio)
                
                vm.entity.dataFim= Workspace.toDate(vm.entity.dataFim)
                console.log(data)
            }))

        } else
            vm.entity = new Vagas()
        
        loadTipos()
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
        function loadTipos() {
            $http.get('data/recrutamento/tipoRecrutamento.json').then(function (resposta) {
                vm.tipos = (resposta.data)

            })
        }

    }

})()