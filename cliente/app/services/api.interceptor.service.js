(function () {
    angular.module('app').service('ApiInterceptor', ["$rootScope", "AuthorizationData","$q", ApiInterceptor]);
    function ApiInterceptor($rootScope, AuthorizationData,$q) {
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
                return $q.resolve(response);
            }
            return $q.reject(response);
        };
    }
})()

