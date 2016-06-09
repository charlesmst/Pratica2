(function () {
    'use strict';
    angular.module('app').controller('EntrevistaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosEntrevista', '$mdDialog', EntrevistaEditController]);

    var state = "entrevista"
    function EntrevistaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosEntrevista, $mdDialog) {
        var vm = this;
        vm.situacoes = [];
        vm.entity = DadosEntrevista
        if (vm.entity.dataProgramada)
            vm.entity.dataProgramada = Workspace.toDate(vm.entity.dataProgramada)
        
//        if (vm.entity.hora)
//            vm.entity.hora = Workspace.toDate(vm.entity.hora)
        
        vm.save = save;
        vm.cancel = cancel;
        
        loadSituacoes();
        function save($event, $valid) {
            if (!$valid)
                return;

            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }
        
        function loadSituacoes() {
            $http.get('data/recrutamento/situacaoEntrevista.json').then(function (resposta) {
                vm.situacoes = (resposta.data)
            })
        }
    }

})()