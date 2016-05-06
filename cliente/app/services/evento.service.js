(function () {
    angular.module("app.services").factory("Evento", ["$resource", "config", "$http", Evento]);

    function Evento($resource, config, $http) {
        var r = $resource(config.apiUrl + '/evento/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/evento/count"},
            test: {method: "POST", isArray: false, url: config.apiUrl + "/evento/test/:funcionario/:mes/:ano/:tipo"},
            calcularMes: {method: "POST", isArray: false, url: config.apiUrl + "/evento/calcular/mes", transformResponse: []},
            funcionarioCargo: {method: "GET", isArray: true, url: config.apiUrl + "/evento/funcionario/:id", transformResponse: []},
            funcionarioCargoCount: {method: "GET", isArray: false, url: config.apiUrl + "/evento/funcionario/count"},
        });

        r.prototype.$verificarJaCalculado = verificarJaCalculado;
//        r.prototype.$calcularMes = calcularMes;

        return r;


        function verificarJaCalculado() {
            return $http.post(config.apiUrl + "/evento/calcular/mes/verificar", this);
        }
    }
})()