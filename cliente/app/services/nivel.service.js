(function () {
    angular.module("app.services").factory("Nivel", ["$resource","config", Nivel]);

    function Nivel($resource,config) {
        return  $resource(config.apiUrl+'/nivel/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/nivel/count"}

        });
    }
})()