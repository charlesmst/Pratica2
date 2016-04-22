(function () {
    angular.module("app.services").factory("Questao", ["$resource", "config", Questao]);

    function Questao($resource, config) {
        return  $resource(config.apiUrl + '/questao/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/questao/count"}

        });
    }
})()