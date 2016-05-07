(function () {
    angular.module('app').service('ApiInterceptor', ["$rootScope", "AuthorizationData", "$q", ApiInterceptor]);
    function ApiInterceptor($rootScope, AuthorizationData, $q) {
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
                $rootScope.$broadcast('unauthorized', response.data);
                return $q.reject(response);
            }
            if (response.status === 500) {
                console.error("Erro da api:",response.data.mensagem)
                if (response.data && response.data.success == false)
                    $rootScope.$broadcast('api-error', response.data);

            }
            return $q.reject(response);
        };
    }
})()

