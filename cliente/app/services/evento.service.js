(function () {
    angular.module("app.services").factory("Evento", ["$resource","config", Evento]);

    function Evento($resource,config) {
        return  $resource(config.apiUrl+'/evento/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/evento/count"}

        });
    }
})()