(function () {
    'use strict';
    angular.module('app').controller('FuncionarioAdvertenciaEditController', ['$mdToast', '$http', '$state', '$stateParams', 'Workspace', 'DadosAdvertencia', '$mdDialog', 'AdvertenciaTipo', FuncionarioAdvertenciaEditController]);

    function FuncionarioAdvertenciaEditController($mdToast, $http, $state, $stateParams, Workspace, DadosAdvertencia, $mdDialog, AdvertenciaTipo) {
        var vm = this;
        vm.entity = DadosAdvertencia

        console.log(vm.entity)
        if (vm.entity.hasOwnProperty("dataOcorrencia"))
            vm.entity.dataOcorrencia = Workspace.toDate(vm.entity.dataOcorrencia)
        vm.save = save;
        vm.cancel = cancel;

        loadAdvertencia()
        function save($event, $valid) {
            if (!$valid)
                return;
            console.log("Saving", vm.entity)
            $mdDialog.hide(vm.entity)
        }
        function cancel() {

            $mdDialog.cancel()
        }
        function loadAdvertencia() {
            AdvertenciaTipo.query().$promise.then(function (r) {
                vm.advertencias = r
            })
        }

    }

})()