(function () {
    'use strict';
    angular.module('app').controller('CrudEditController', ['$mdToast', '$http', 'Entry', '$state', '$stateParams', 'Workspace', CrudEditController]);


    function CrudEditController($mdToast, $http, Entry, $state, $stateParams, Workspace) {
        var vm = this;
        vm.entity = {}
        Workspace.title = "Manutenção de usuário"
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Entry.get({id: $stateParams.id}).$promise.then(function (data) {
                
                vm.entity = data
                if (vm.entity.birth)
                    vm.entity.birth = new Date(vm.entity.birth)
            }))

        }
        else
            vm.entity = new Entry()
        vm.save = save;
        vm.cancel = cancel;
        function save() {


            Workspace.loading("Salvando...", vm.entity.$save(callbackSave).$promise)
        }
        function cancel() {
            $state.go("crud")
        }
        function callbackSave() {
            $mdToast.showSimple("Registro salvo")
            $state.go("crud")
        }

    }

})()