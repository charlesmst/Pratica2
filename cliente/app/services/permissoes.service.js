(function () {
    angular.module("app.services").factory("Permissoes", ["$resource", "config", Permissoes]);

    function Permissoes($resource, config) {
        return  $resource(config.apiUrl + '/permissoes/:modulo', {modulo: '@modulo'}, {
        });
    }
})()