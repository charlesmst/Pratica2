(function () {
    'use strict';
    angular.module('app').controller('CrudController', ['$mdToast', '$http', 'Entry', '$state', '$mdDialog', CrudController]);


    function CrudController($mdToast, $http, Entry, $state, $mdDialog) {
        var vm = this;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.list = []
        vm.selectedItems = []
        load()

        function showDelete() {

            // Appending dialog to document.body to cover sidenav in docs app
            var confirm = $mdDialog.confirm("Wa")
                    .title('Gostaria de excluir?')
                    .textContent('O registro será perdido.')
                    .ok('Sim')
                    .cancel('Não');
            $mdDialog.showConfim("confirm").then(function () {
                $scope.status = 'You decided to get rid of your debt.';
            }, function () {
                $scope.status = 'You decided to keep your debt.';
            });

            $mdToast.showSimple('Simple Toast!')
        }
        function showAdd() {
            $state.go("crudadd")
        }
        function load() {
            vm.list = Entry.query()
        }

    }

})()