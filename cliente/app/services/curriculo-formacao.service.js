(function () {
    angular.module("app.services").factory("CurriculoFormacao", ["$resource", "config", CurriculoFormacao]);

    function CurriculoFormacao($resource, config) {
        return  $resource(config.apiUrl + '/curriculo-formacao/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/curriculo-formacao/count"},
            curriculoformacao: {method: "GET", isArray: true, url: config.apiUrl + "/curriculo-formacao/pessoa/:pessoaId"}

        });
    }
})()