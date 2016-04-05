(function () {
    angular.module("app.services").factory("Entry", ["$resource","config", Entry]);

    function Entry($resource,config) {
        return  $resource(config.apiUrl+'/usuario/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:"/api/count"}

        });
    }
})()