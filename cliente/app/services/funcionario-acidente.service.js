(function () {
    angular.module("app.services").factory("FuncionarioAcidente", ["$resource", "config", FuncionarioAcidente]);

    function FuncionarioAcidente($resource, config) {
        return  $resource(config.apiUrl + '/funcionario-acidente/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/funcionario-acidente/count"}

        });
    }
})()