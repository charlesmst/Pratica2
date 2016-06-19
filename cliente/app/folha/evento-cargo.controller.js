(function () {
    'use strict';

    angular
        .module('app')
        .controller('EventoCargoController', EventoCargoController);

    EventoCargoController.$inject = ['CargoHasEvento','$stateParams','$scope','Workspace','$state','$q'];
    function EventoCargoController(CargoHasEvento,$stateParams,$scope,Workspace,$state,$q) {
        var vm = this;
        vm.ano = new Date().getFullYear();
        vm.mes = new Date().getMonth() + 1;
        vm.list = []
        vm.selectedItems = []
        vm.showAdd = showAdd
        vm.showDelete = showDelete;
        $scope.$watchGroup(['crudVm.ano','crudVm.mes'],reload)
        reload();
        Workspace.callbackOnEnterState($scope,$state,reload)

        ////////////////

        function reload() { 
            CargoHasEvento.byCargo({
                ano:vm.ano,
                mes:vm.mes,
                cargo:$stateParams.id
            }).$promise.then(function(data){
                console.log(data)
                vm.list = data
            })
            
        }
         function showAdd() {
            $state.go('cargoevento.add',{"id": $stateParams.id})
        }
         function showDelete($event) {
            Workspace.showDeleteDialog($event).then(function () {
                //Confirmou
                var promises = [];
                var items = angular.copy(vm.selectedItems);
                vm.selectedItems = [];
                angular.forEach(items, function (value) {
                    promises.push(value.$delete());
                });
                Workspace.loading("Excluindo...", $q.all(promises).then(function () {
                    Workspace.showMessage("Registros Excluidos!");
                    reload();
                }, function (r) {
                    Workspace.showError(r.data.mensagem)
                }));
            });
        }
    }
})();