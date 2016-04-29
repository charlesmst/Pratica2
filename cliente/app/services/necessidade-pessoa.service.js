(function () {
    angular.module("app.services").factory("NecessidadePessoa", ["$resource", "config", NecessidadePessoa]);

    function NecessidadePessoa($resource, config) {
        return  $resource(config.apiUrl + '/necessidade-pessoa/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/necessidade-pessoa/count"}

        });
    }
})()