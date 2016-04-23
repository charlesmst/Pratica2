(function () {
    angular.module("app.services").factory("Entrevista", ["$resource", "config", Entrevista]);

    function Entrevista($resource, config) {
        return  $resource(config.apiUrl + '/entrevista/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/entrevista/count"}

        });
    }
})()