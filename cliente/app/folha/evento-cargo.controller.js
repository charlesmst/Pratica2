(function () {
    'use strict';

    angular
        .module('app')
        .controller('EventoCargoController', EventoCargoController);

    EventoCargoController.$inject = ['CargoHasEvento','$stateParams','$scope'];
    function EventoCargoController(CargoHasEvento,$stateParams,$scope) {
        var vm = this;
        vm.ano = new Date().getFullYear();
        vm.mes = new Date().getMonth() + 1;
        vm.list = []
        vm.selectedItems = []

        $scope.$watchGroup(['crudVm.ano','crudVm.mes'],reload)
        reload();

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
    }
})();