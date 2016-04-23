(function () {
    angular.module("app.services").factory("Pessoa", ["$resource", "config", Pessoa]);

    function Pessoa($resource, config) {
        return  $resource(config.apiUrl + '/pessoa/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/pessoa/count"}

        });
    }
})()