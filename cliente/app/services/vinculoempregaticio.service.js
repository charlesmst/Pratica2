(function () {
    angular.module("app.services").factory("VinculoEmpregaticio", ["$resource", "config", VinculoEmpregaticio]);

    function VinculoEmpregaticio($resource, config) {
        return  $resource(config.apiUrl + '/vinculoempregaticio/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/vinculoempregaticio/count"}

        });
    }
})()