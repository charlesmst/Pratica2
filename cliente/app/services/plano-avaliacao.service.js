(function () {
    angular.module("app.services").factory("PlanoAvaliacao", ["$resource", "config", PlanoAvaliacao]);

    function PlanoAvaliacao($resource, config) {
        return  $resource(config.apiUrl + '/plano-avaliacao/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/plano-avaliacao/count"}

        });
    }
})()