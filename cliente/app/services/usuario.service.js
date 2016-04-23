(function () {
    angular.module("app.services").factory("Usuario", ["$resource", "config", Usuario]);

    function Usuario($resource, config) {
        return  $resource(config.apiUrl + '/usuario/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/usuario/count"}

        });
    }
})()