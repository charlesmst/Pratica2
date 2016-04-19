(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);

    function config($stateProvider, $urlRouterProvider) {
        $stateProvider
                .state('evento', {
                    url: '/evento',
                    views: {
                        "": {
                            templateUrl: 'app/folha/evento.tmpl.html',
                            controller: "EventoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('eventoadd', {
                    url: '/evento/add',
                    views: {
                        "": {
                            templateUrl: 'app/folha/evento.edit.tmpl.html',
                            controller: "EventoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('eventoedit', {
                    url: '/evento/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/folha/evento.edit.tmpl.html',
                            controller: "EventoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
})()