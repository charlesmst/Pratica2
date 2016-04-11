(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);

    function config($stateProvider, $urlRouterProvider) {
        $stateProvider
                .state('start', {
                    url: '/',
                    views: {
                        "": {
                            templateUrl: 'app/sample/activity.tmpl.html',
                            controller: "ActivityController",
                            controllerAs: "activityVm",
                        },
                        "top": {
                            templateUrl: 'app/sample/activity.top.tmpl.html'
                        }
                    }

                })
                .state('crud', {
                    url: '/crud',
                    views: {
                        "": {
                            templateUrl: 'app/sample/crud.tmpl.html',
                            controller: "CrudController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('crudadd', {
                    url: '/crud/add',
                    views: {
                        "": {
                            templateUrl: 'app/sample/crud.edit.tmpl.html',
                            controller: "CrudEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('crudedit', {
                    url: '/crud/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/sample/crud.edit.tmpl.html',
                            controller: "CrudEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('menu', {
                    url: '/menu',
                    views: {
                        "": {
                            templateUrl: 'app/application/menu.tmpl.html',
                            controller: "MenuController",
                            controllerAs: "crudVm",
                        }
                    }
                })                
                .state('menuedit', {
                    url: '/menu/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/application/menu.edit.tmpl.html',
                            controller: "MenuEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('menuadd', {
                    url: '/menu/add',
                    views: {
                        "": {
                            templateUrl: 'app/application/menu.edit.tmpl.html',
                            controller: "MenuEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
})()