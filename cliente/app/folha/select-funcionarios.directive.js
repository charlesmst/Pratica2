(function () {

    'use strict';

    angular
        .module('app')
        .directive('selectFuncionarios', selectFuncionarios);

    selectFuncionarios.$inject = ['Cargo', '$q'];
    function selectFuncionarios(Cargo, $q) {
        // Usage:
        //
        // Creates:
        //
        var directive = {
            // bindToController: true,

            templateUrl: 'app/folha/select-funcionarios.directive.tmpl.html',
            link: link,
            restrict: 'E',
            scope: {
                model: "=",
                required: "=",
                multiple: "=",
                empresa: "=",
                disabled: "=",
                title: "@"
            },
            transclude: true
        };
        return directive;

        function link(scope, element, attrs) {
            var vm = scope;
            vm.loadFuncionarios = loadFuncionarios;

            // scope.$watch('empresa', loadFuncionarios);
            function loadFuncionarios() {
                return $q(function (resolve, reject) {
                    if (typeof (scope.empresa) == "undefined" || typeof (scope.empresa.id) !== "number")
                        reject();
                    Cargo.cargosFuncionarioEmpresa({
                        empresa: scope.empresa.id
                    }).$promise.then(function (r) {
                        //Coloca o id do cargo no funcionario
                        angular.forEach(r, function (cargo) {
                            angular.forEach(cargo.funcionarioCargos, function (f) {
                                f.cargo = {
                                    id: cargo.id
                                };
                            })

                        })
                        vm.cargos = r;
                        resolve()
                    }, reject)
                })

            }
        }
    }
})();