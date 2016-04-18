(function () {
    angular.module("app.services").factory("${name}", ["$resource","config", ${name}]);

    function ${name}($resource,config) {
        return  $resource(config.apiUrl+'/${name}/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/${name}/count"}

        });
    }
})()