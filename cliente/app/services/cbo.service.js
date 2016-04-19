(function () {
    angular.module("app.services").factory("Cbo", ["$resource","config", Cbo]);

    function Cbo($resource,config) {
        return  $resource(config.apiUrl+'/cbo/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/cbo/count"}

        });
    }
})()