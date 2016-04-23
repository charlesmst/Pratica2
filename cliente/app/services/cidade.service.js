(function () {
    angular.module("app.services").factory("Cidade", ["$resource", "config", Cidade]);

    function Cidade($resource, config) {
        return  $resource(config.apiUrl + '/cidade/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/cidade/count"}

        });
    }
})()