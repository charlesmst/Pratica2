
(function () {
    angular.module("app.services").factory("CargoNivel", ["$resource", "config", CargoNivel]);

    function CargoNivel($resource, config) {
        return  $resource(config.apiUrl + '/cargonivel/:id', {id: '@id'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/cargonivel/count"},
            doCargo: {method: "GET", isArray: true, url: config.apiUrl + "/cargonivel/cargo/:cargoid"}
                
    });
    }
})()