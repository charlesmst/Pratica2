(function () {
    angular.module("app.services").factory("Menu", ["$resource","config", Menu]);

    function Menu($resource,config) {
        return  $resource(config.apiUrl+'/menu/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:"/api/count"}

        });
    }
})()