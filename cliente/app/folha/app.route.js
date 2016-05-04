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
                        },
                        "top": {
                            templateUrl: 'app/folha/evento.edit.top.tmpl.html',
                            controller: "TopController",
                            controllerAs: "topVm",
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
                        },
                        "top": {
                            templateUrl: 'app/folha/evento.edit.top.tmpl.html',
                            controller: "TopController",
                            controllerAs: "topVm",
                        }
                    }
                })
                .state('calculofolha', {
                    url: '/calculofolha',
                    views: {
                        "": {
                            templateUrl: 'app/folha/calculo.folha.tmpl.html',
                            controller: "CalculoFolhaController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('folhacalculada', {
                    url: '/folha',
                    views: {
                        "": {
                            templateUrl: 'app/folha/folhacalculada.tmpl.html',
                            controller: "FolhaCalculadaController",
                            controllerAs: "crudVm",
                        }
                    }
                })

    }
})()