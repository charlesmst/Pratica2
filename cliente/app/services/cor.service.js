(function () {
    angular.module("app.services").factory("Cor", ["$resource", "config", Cor]);

    function Cor($resource, config) {
        return  $resource(config.apiUrl + '/cor/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/cor/count"}

        });
    }
})()