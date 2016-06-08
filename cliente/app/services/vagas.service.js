(function () {
    angular.module("app.services").factory("Vagas", ["$resource", "config", Vagas]);

    function Vagas($resource, config) {
        return  $resource(config.apiUrl + '/vagas/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/vagas/count"},
            view: {method: "GET", isArray: true, url: config.apiUrl + "/vagas/view/:id"}

        });
    }
})()