(function () {
    angular.module("app.services").factory("NecessidadeEspecial", ["$resource", "config", NecessidadeEspecial]);

    function NecessidadeEspecial($resource, config) {
        return  $resource(config.apiUrl + '/necessidade-especial/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/necessidade-especial/count"}

        });
    }
})()