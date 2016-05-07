(function () {
    angular.module("app.services").factory("Empresa", ["$resource","config", Empresa]);

    function Empresa($resource,config) {
        return  $resource(config.apiUrl+'/empresa/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/empresa/count"},
            listagem: {method: "GET", isArray: true, url:config.apiUrl+"/empresa/listagem"}
            
        });
    }
})()