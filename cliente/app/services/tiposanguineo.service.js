(function () {
    angular.module("app.services").factory("TipoSanguineo", ["$resource", "config", TipoSanguineo]);

    function TipoSanguineo($resource, config) {
        return  $resource(config.apiUrl + '/tiposanguineo/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/tiposanguineo/count"}

        });
    }
})()