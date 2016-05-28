(function () {
    'use strict';
    angular.module('app').controller('FaixaSalarialEditController', ['$mdToast', '$http', 'FaixaSalarial', '$state', '$stateParams', 'Workspace', FaixaSalarialEditController]);

    var state = "faixasalarial"
    function FaixaSalarialEditController($mdToast, $http, FaixaSalarial, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de Faixa Salarial";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", FaixaSalarial.get({ id: $stateParams.id }).$promise.then(function (data) {

                vm.entity = data
                vm.entity.faixaSalarialValors = vm.entity.faixaSalarialValors || []
                angular.forEach(vm.entity.faixaSalarialValors,function(v){
                    v.id.dataInicio = Workspace.toDate(v.id.dataInicio)
                })
            }))

        }
        else {
            vm.entity = new FaixaSalarial()
            vm.entity.faixaSalarialValors = vm.entity.faixaSalarialValors || []

        }
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