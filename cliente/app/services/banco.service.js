(function () {
    angular.module("app.services").factory("Banco", ["$resource", "config", Banco]);

    function Banco($resource, config) {
        return  $resource(config.apiUrl + '/banco/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/banco/count"}

        });
    }
})()