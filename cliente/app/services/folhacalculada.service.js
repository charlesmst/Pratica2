(function () {
    angular.module("app.services").factory("FolhaCalculada", ["$resource","config", FolhaCalculada]);

    function FolhaCalculada($resource,config) {
        return  $resource(config.apiUrl+'/folhacalculada/:id', {id: '@id'}, {
            
            count: {method: "GET", isArray: false, url:config.apiUrl+"/folhacalculada/count"},
            relatorio:{method:"GET",isArray:true}

        });
    }
})()