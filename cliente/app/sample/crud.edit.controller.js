(function () {
    'use strict';
    angular.module('app').controller('CrudEditController', ['$mdToast', '$http', 'Entry', '$state', '$stateParams', CrudEditController]);


    function CrudEditController($mdToast, $http, Entry, $state, $stateParams) {
        var vm = this;
        if ($stateParams.id)
            vm.entity = Entry.get({id: $stateParams.id})
        else
            vm.entity = new Entry()
        vm.save = save;
        vm.cancel = cancel;
        function save() {
            if ($stateParams.id)
                vm.entity.$update(callbackSave)
            else
                vm.entity.$save(callbackSave)
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