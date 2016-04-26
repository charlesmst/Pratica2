(function () {
    angular.module("app.services").factory("FuncionarioCargo", ["$resource","config", FuncionarioCargo]);

    function FuncionarioCargo($resource,config) {
        return  $resource(config.apiUrl+'/funcionariocargo/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/funcionariocargo/count"}

        });
    }
})()