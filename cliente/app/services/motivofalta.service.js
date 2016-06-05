(function () {
    angular.module("app.services").factory("MotivoFalta", ["$resource","config", FaixaSalarial]);

    function FaixaSalarial($resource,config) {
        return  $resource(config.apiUrl+'/motivofalta/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/motivofalta/count"}

        });
    }
})()