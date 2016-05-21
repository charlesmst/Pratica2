(function () {
    'use strict';
    angular.module('app').controller('CurriculoFormacaoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', '$mdDialog', 'DadosFormacao', 'Escolaridade', CurriculoFormacaoEditController]);

    var state = "funcionario-cargo"
    function CurriculoFormacaoEditController($mdToast, $http, $state, $stateParams, Workspace, $mdDialog, DadosFormacao, Escolaridade) {
        var vm = this;
        vm.entity = DadosFormacao;
        vm.escolaridades = [];
        vm.situacoes = [];
        console.log(DadosFormacao)
//        if (vm.entity.dataEntrada)
//            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)
//        
//        if (vm.entity.dataSaida)
//            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
        vm.save = save;
        vm.cancel = cancel;
        
        loadEscolaridades()
        loadSituacoes()
        function save($event, $valid) {
            if (!$valid)
                return;

            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }

        function loadEscolaridades() {
            Escolaridade.query().$promise.then(function (resposta) {
                vm.escolaridades = resposta;
            })
        }
        
        function loadSituacoes() {
            $http.get('data/recrutamento/situacaoCurriculoFormacao.json').then(function (resposta) {
                vm.situacoes = (resposta.data)
            })
        }


    }

})()