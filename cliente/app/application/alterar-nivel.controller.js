(function () {
    'use strict';

    angular
        .module('app')
        .controller('AlterarNivelController', AlterarNivelController);

    AlterarNivelController.$inject = ['$http', 'config', '$mdDialog'];
    function AlterarNivelController($http, config, $mdDialog) {
        var vm = this;
        vm.niveis = []
        vm.save = save;
        vm.cancel = cancel;
        activate();

        ////////////////

        function activate() {
            $http.get(config.apiUrl + "/authorize/niveis").then(function (r) {
                vm.niveis = r.data;
            })
        }
        function save($event, $valid) {
            if (!$valid)
                return;
            $mdDialog.hide(vm.selected)
        }
        function cancel() {
            $mdDialog.cancel();
        }
    }
})();