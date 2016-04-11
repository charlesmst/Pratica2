(function () {
    'use strict';
    angular.module('app').controller('MenuEditController', ['$mdToast', '$http', 'Menu', '$state', '$stateParams', 'Workspace', MenuEditController]);


    function MenuEditController($mdToast, $http, Menu, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de usuário"
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Menu.get({id: $stateParams.id}).$promise.then(function (data) {
                
                vm.entity = data
            }))

        }
        else
            vm.entity = new Menu()
        vm.save = save;
        vm.cancel = cancel;
        function save() {


            Workspace.loading("Salvando...", vm.entity.$save(callbackSave).$promise)
        }
        function cancel() {
            $state.go("menu")
        }
        function callbackSave() {
            $mdToast.showSimple("Registro salvo")
            $state.go("menu")
        }

    }

})()