(function () {
    'use strict';

    angular
        .module('app')
        .service('Download', Download);

    Download.$inject = ['$http', 'config', '$q'];
    function Download($http, config, $q) {
        this.downloadFile = downloadFile;

        ////////////////

        function downloadFile(url, parametros, fileName) {
            return $q(function (resolve, reject) {
                $http({
                    url: config.apiUrl + url,
                    method: "GET",
                    responseType: "blob",
                    params: parametros
                }).then(function (response) {
                    var blob = new Blob([response.data], {
                        type: "application/pdf"
                    });
                    //saveAs provided by FileSaver.js
                    saveAs(blob, fileName + '.pdf');
                    resolve()
                },reject)
            })
        }

    }
})();