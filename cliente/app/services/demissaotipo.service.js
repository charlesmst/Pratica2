(function () {
    angular.module("app.services").factory("DemissaoTipo", ["$resource","config", DemissaoTipo]);

    function DemissaoTipo($resource,config) {
        return  $resource(config.apiUrl+'/demissaotipo/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/demissaotipo/count"}

        });
    }
})()