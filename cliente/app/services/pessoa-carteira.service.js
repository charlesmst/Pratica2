(function () {
    angular.module("app.services").factory("PessoaCarteira", ["$resource", "config", PessoaCarteira]);

    function PessoaCarteira($resource, config) {
        return  $resource(config.apiUrl + '/pessoa-carteira/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/pessoa-carteira/count"}

        });
    }
})()