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
                .state('cargo', {
                    url: '/cargo',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargo.tmpl.html',
                            controller: "CargoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cargoadd', {
                    url: '/cargo/add',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargo.edit.tmpl.html',
                            controller: "CargoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cargoedit', {
                    url: '/cargo/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargo.edit.tmpl.html',
                            controller: "CargoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })              

                .state('faixasalarial', {
                    url: '/faixasalarial',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/faixasalarial.controller.tmpl.html',
                            controller: "FaixaSalarialController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('faixasalarialadd', {
                    url: '/faixasalarial/add',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/faixasalarial.edit.tmpl.html',
                            controller: "FaixaSalarialEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('faixasalarialedit', {
                    url: '/faixasalarial/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/faixasalarial.edit.tmpl.html',
                            controller: "FaixaSalarialEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cargonivel', {
                    url: '/cargonivel',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargonivel.tmpl.html',
                            controller: "CargoNivelController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cargoniveladd', {
                    url: '/cargonivel/add',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargonivel.edit.tmpl.html',
                            controller: "CargoNivelEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('cargoniveledit', {
                    url: '/cargonivel/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/cargo/cargonivel.edit.tmpl.html',
                            controller: "CargoNivelEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
    
})()