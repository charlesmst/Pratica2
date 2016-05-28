(function () {
    angular.module("app.services").factory("FaixaSalarial", ["$resource","config", FaixaSalarial]);

    function FaixaSalarial($resource,config) {
        return  $resource(config.apiUrl+'/faixasalarial/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/faixasalarial/count"}

        });
    }
})()