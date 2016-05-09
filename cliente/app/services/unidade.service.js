(function () {
    angular.module("app.services").factory("Unidade", ["$resource", "config", Unidade]);

    function Unidade($resource, config) {
        return  $resource(config.apiUrl + '/unidade/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/unidade/count"}

        });
    }
})()