(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);

    function config($stateProvider, $urlRouterProvider) {
        $stateProvider
                .state('qualificacao', {
                    url: '/qualificacao',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/qualificacao.tmpl.html',
                            controller: "QualificacaoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('qualificacaoadd', {
                    url: '/qualificacao/add',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/qualificacao.edit.tmpl.html',
                            controller: "QualificacaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('qualificacaoedit', {
                    url: '/qualificacao/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/qualificacao.edit.tmpl.html',
                            controller: "QualificacaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })

                .state('funcionarioAcidente', {
                    url: '/funcionarioAcidente',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionarioAcidente.tmpl.html',
                            controller: "FuncionarioAcidenteController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('funcionarioAcidenteadd', {
                    url: '/funcionarioAcidente/add',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionarioAcidente.edit.tmpl.html',
                            controller: "FuncionarioAcidenteEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('funcionarioAcidenteedit', {
                    url: '/funcionarioAcidente/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionarioAcidente.edit.tmpl.html',
                            controller: "FuncionarioAcidenteEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('sindicato', {
                    url: '/sindicato',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/sindicato.tmpl.html',
                            controller: "SindicatoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('sindicatoadd', {
                    url: '/sindicato/add',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/sindicato.edit.tmpl.html',
                            controller: "SindicatoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('sindicatoedit', {
                    url: '/sindicato/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/sindicato.edit.tmpl.html',
                            controller: "SindicatoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('funcionario-acidente', {
                    url: '/funcionario-acidente',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionario-acidente.tmpl.html',
                            controller: "FuncionarioAcidenteController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('funcionario-acidenteadd', {
                    url: '/funcionario-acidente/add',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionario-acidente.edit.tmpl.html',
                            controller: "FuncionarioAcidenteEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('funcionario-acidenteedit', {
                    url: '/funcionario-acidente/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionario-acidente.edit.tmpl.html',
                            controller: "FuncionarioAcidenteEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('empresa', {
                    url: '/empresa',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/empresa.tmpl.html',
                            controller: "EmpresaController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('empresaadd', {
                    url: '/empresa/add',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/empresa.edit.tmpl.html',
                            controller: "EmpresaEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('empresaedit', {
                    url: '/empresa/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/empresa.edit.tmpl.html',
                            controller: "EmpresaEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
})()