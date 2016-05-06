(function () {
    angular.module("app.services").factory("Sindicato", ["$resource", "config", Sindicato]);

    function Sindicato($resource, config) {
        return  $resource(config.apiUrl + '/sindicato/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/sindicato/count"}

        });
    }
})()