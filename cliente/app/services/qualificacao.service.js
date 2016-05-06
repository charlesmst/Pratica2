(function () {
    angular.module("app.services").factory("Qualificacao", ["$resource", "config", Qualificacao]);

    function Qualificacao($resource, config) {
        return  $resource(config.apiUrl + '/qualificacao/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/qualificacao/count"}

        });
    }
})()