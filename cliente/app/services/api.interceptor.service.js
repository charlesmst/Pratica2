(function () {
    angular.module('app').service('ApiInterceptor', ApiInterceptor);

    function ApiInterceptor() {
        var service = this;
        service.request = function (config) {
           
//                config.headers.common['Access-Control-Allow-Origin'] = '*';
           
        };

    }
})()

