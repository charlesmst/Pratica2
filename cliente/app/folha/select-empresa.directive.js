(function () {

    'use strict';

    angular
        .module('app')
        .directive('selectEmpresa', selectEmpresa);

    selectEmpresa.$inject = ['Empresa'];
    function selectEmpresa(Empresa) {
        // Usage:
        //
        // Creates:
        //
        var directive = {
            // bindToController: true,
          
            templateUrl: 'app/folha/select-empresa.directive.tmpl.html',
            link: link,
            restrict: 'E',
            scope: {
                model: "=",
                required:"=",
                autoSelectFirst:"="
            },
            transclude: true
        };
        return directive;

        function link(scope, element, attrs) {
            var vm = scope;
            loadEmpresas();
            function loadEmpresas() {
                Empresa.listagem().$promise.then(function (r) {
                    vm.empresas = r;
                    if(vm.autoSelectFirst && r.length > 0)
                        vm.model = vm.empresas[0]
                })
            }
        }
    }
})();