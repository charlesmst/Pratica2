(function () {
    angular.module("app.services").factory("CurriculoQualificacao", ["$resource", "config", CurriculoQualificacao]);

    function CurriculoQualificacao($resource, config) {
        return  $resource(config.apiUrl + '/curriculo-qualificacao/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/curriculo-qualificacao/count"},
            curriculoqualificacao: {method: "GET", isArray: true, url: config.apiUrl + "/curriculo-qualificacao/pessoa/:pessoaId"}

        });
    }
})()