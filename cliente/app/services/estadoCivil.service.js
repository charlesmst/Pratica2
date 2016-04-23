(function () {
    angular.module("app.services").factory("EstadoCivil", ["$resource", "config", EstadoCivil]);

    function EstadoCivil($resource, config) {
        return  $resource(config.apiUrl + '/estado-civil/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/estado-civil/count"}

        });
    }
})()