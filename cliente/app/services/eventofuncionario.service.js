(function () {
    angular.module("app.services").factory("EventoFuncionario", ["$resource", "config", EventoFuncionario]);

    function EventoFuncionario($resource, config) {
        return  $resource(config.apiUrl + '/eventofuncionario/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/eventofuncionario/funcionario/:cargoId/:mes/:ano/count"},
            queryC: {method: "GET", isArray: true, url: config.apiUrl + "/eventofuncionario/funcionario/:cargoId/:mes/:ano"}


        });
    }
})()