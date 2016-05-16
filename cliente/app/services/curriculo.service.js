(function () {
    angular.module("app.services").factory("Curriculo", ["$resource", "config", Curriculo]);

    function Curriculo($resource, config) {
        return  $resource(config.apiUrl + '/curriculo/:id', {id: '@pessoaId'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/curriculo/count"}

        });
    }
})()