(function () {
    angular.module("app.services").factory("Dependente", ["$resource", "config", Dependente]);

    function Dependente($resource, config) {
        return  $resource(config.apiUrl + '/dependente/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/dependente/count"}

        });
    }
})()