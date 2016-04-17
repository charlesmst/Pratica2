(function () {
    angular.module('app').service('ApiInterceptor', ["$rootScope", "AuthorizationData", ApiInterceptor]);
    function ApiInterceptor($rootScope, AuthorizationData) {
        var service = this;
        service.request = function (config) {
            var currentUser = AuthorizationData.getCurrentUser(),
                    access_token = currentUser ? currentUser.token : null;
            if (access_token) {
                config.headers.Authorization = access_token;
            }
            return config;
        };
        service.responseError = function (response) {
            if (response.status === 401 || response.status === 403) {
                $rootScope.$broadcast('unauthorized',response.data);
            }
            return response;
        };
    }
})()

