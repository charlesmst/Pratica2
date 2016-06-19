(function () {
    angular.module("app.services").factory("CargoHasEvento", ["$resource", "config", CargoHasEvento]);

    function CargoHasEvento($resource, config) {
        return  $resource(config.apiUrl + '/cargohasevento/:cargo/:evento', {cargo:'@id.cargoId',evento:'@id.eventoId'}, {
            count: {method: "GET", isArray: false, url: config.apiUrl + "/cargo/count"},
            byCargo: {method: "GET", isArray: true, url: config.apiUrl + "/cargohasevento/cargo/:cargo/:ano/:mes"}
        });
    }
})()