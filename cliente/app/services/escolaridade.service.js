(function () {
    angular.module("app.services").factory("Escolaridade", ["$resource", "config", Escolaridade]);

    function Escolaridade($resource, config) {
        return  $resource(config.apiUrl + '/escolaridade/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/escolaridade/count"}

        });
    }
})()