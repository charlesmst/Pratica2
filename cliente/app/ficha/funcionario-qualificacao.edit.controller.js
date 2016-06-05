(function () {
    'use strict';
    angular.module('app').controller('FuncionarioQualificacaoEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosQualificacao', '$mdDialog', 'Cargo', 'Qualificacao', FuncionarioCargoEditController]);

    var state = "funcionario-cargo"
    function FuncionarioCargoEditController($mdToast, $http, $state, $stateParams, Workspace, DadosQualificacao, $mdDialog, Cargo, Qualificacao) {
        var vm = this;
        vm.entity = DadosQualificacao
        if (DadosQualificacao.hasOwnProperty("validade") && typeof (DadosQualificacao.validade) === "string")
            DadosQualificacao.validade = Workspace.toDate(DadosQualificacao.validade)
        loadQualificacoes()

        if (vm.entity.dataEntrada)
            vm.entity.dataEntrada = Workspace.toDate(vm.entity.dataEntrada)

        if (vm.entity.dataSaida)
            vm.entity.dataSaida = Workspace.toDate(vm.entity.dataSaida)
        vm.save = save;
        vm.cancel = cancel;


        function loadQualificacoes() {
            Qualificacao.query().$promise.then(function (resposta) {
                vm.qualificacaos = resposta;
            })
        }

        function save($event, $valid) {
            if (!$valid)
                return;

            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }


    }

})()