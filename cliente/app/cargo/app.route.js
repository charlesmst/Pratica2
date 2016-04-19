(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);

    function config($stateProvider, $urlRouterProvider) {
        $stateProvider

                .state('cbo', {
                    url: '/cbo',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cbo.tmpl.html',
                            controller: "CboController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cboadd', {
                    url: '/cbo/add',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cbo.edit.tmpl.html',
                            controller: "CboEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cboedit', {
                    url: '/cbo/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cbo.edit.tmpl.html',
                            controller: "CboEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
    }
})()