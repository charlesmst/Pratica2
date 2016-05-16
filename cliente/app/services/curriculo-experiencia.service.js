(function () {
    angular.module("app.services").factory("CurriculoExperiencia", ["$resource", "config", CurriculoExperiencia]);

    function CurriculoExperiencia($resource, config) {
        return  $resource(config.apiUrl + '/curriculo-experiencia/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/curriculo-experiencia/count"},
            curriculoexperiencia: {method: "GET", isArray: true, url: config.apiUrl + "/curriculo-experiencia/pessoa/:pessoaId"}

        });
    }
})()