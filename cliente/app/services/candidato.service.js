(function () {
    angular.module("app.services").factory("Candidato", ["$resource", "config", Candidato]);

    function Candidato($resource, config) {
        return  $resource(config.apiUrl + '/candidato/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/candidato/count"}

        });
    }
})()