(function () {
    angular.module("app.services").factory("Cargo", ["$resource", "config", Cargo]);

    function Cargo($resource, config) {
        return  $resource(config.apiUrl + '/cargo/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/cargo/count"},
            cargosFuncionarioEmpresa: {method: "GET", isArray: true, url: config.apiUrl + "/cargo/empresa/:empresa/funcionarios"}
                
    });
    }
})()