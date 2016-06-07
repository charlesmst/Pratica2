(function () {
    'use strict';
    angular.module('app').controller('FichaTopController', ['$state', '$stateParams', 'Workspace', '$scope', FichaTopController]);

    function FichaTopController($state, $stateParams, Workspace, $scope) {
        var vm = this;
        vm.id = $stateParams.id;
        vm.uisrefParams = {id: vm.id};

        vm.tabs = [
            {
                titulo: "Dados do Colaborador",
                sref: "fichaedit"
            },
            {
                titulo: "Folha de Pagamento",
                sref: "fichafuncionarioevento"
            }
        ];

        for (var i = 0; i < vm.tabs.length; i++) {
            if ($state.$current.name == vm.tabs[i].sref) {
                vm.selectedIndex = i;
                $scope.$watch("topVm.selectedIndex", changeTo)

                break;
            }
        }

        function changeTo() {
            $state.go(vm.tabs[vm.selectedIndex].sref,{id:$stateParams.id})
        }
        console.log(vm.id)

    }

})();