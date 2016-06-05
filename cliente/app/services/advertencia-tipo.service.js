(function () {
    angular.module("app.services").factory("AdvertenciaTipo", ["$resource", "config", AdvertenciaTipo]);

    function AdvertenciaTipo($resource, config) {
        return $resource(config.apiUrl + '/advertenciatipo/:id', { id: '@id' }, {
            count: { method: "GET", isArray: false, url: config.apiUrl + "/advertenciatipo/count" }
        });
    }
})()