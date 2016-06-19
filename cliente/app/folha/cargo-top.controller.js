(function() {
'use strict';

    angular
        .module('app')
        .controller('CargoTopController', CargoTopController);

    CargoTopController.$inject = ['$stateParams','$state'];
    function CargoTopController($stateParams,$state) {
        var vm = this;
        vm.selecetedIndex = $state.current.name == "cargoedit"?0:1
        vm.tabs = [
            {
                titulo:"Cargo",
                link:"cargoedit({id:"+$stateParams.id+"})"
            },
            {
                titulo:"Eventos do Cargo",
                link:"cargoevento({id:"+$stateParams.id+"})"
            },
            
        ];


        activate();

        ////////////////

        function activate() { }
    }
})();