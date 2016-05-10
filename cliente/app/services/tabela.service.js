(function () {
    angular.module("app.services").factory("Tabela", ["$resource","config", Tabela]);

    function Tabela($resource,config) {
        return  $resource(config.apiUrl+'/tabela/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/tabela/count"},
            tipos: {method: "GET", isArray: true, url:config.apiUrl+"/tabela/tipos"}

        });
    }
})()