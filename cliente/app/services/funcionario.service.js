(function () {
    angular.module("app.services").factory("Funcionario", ["$resource", "config", Funcionario]);

    function Funcionario($resource, config) {
        return  $resource(config.apiUrl + '/funcionario/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/funcionario/count"}

        });
    }
})()