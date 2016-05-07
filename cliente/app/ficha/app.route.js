(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);

    function config($stateProvider, $urlRouterProvider) {


        function onEnterModal(controller, view, stateback) {
            return function ($stateParams, $state, $mdDialog) {

                $mdDialog.show({
                    controller: controller,
                    controllerAs:"modalVm",
                    templateUrl: view,
                    parent: angular.element(document.body),
                    clickOutsideToClose: true
                }).then(function () {
                    $state.transitionTo(stateback, angular.copy($stateParams), {reload: false, inherit: true, notify: true});
                }, function () {
                    $state.transitionTo(stateback, angular.copy($stateParams), {reload: false, inherit: true, notify: true});
                })
            }
        }
        $stateProvider
                .state('ficha', {
                    url: '/ficha',
                    views: {
                        "": {
                            templateUrl: 'app/ficha/funcionario.tmpl.html',
                            controller: "FuncionarioController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('fichaadd', {
                    url: '/ficha/add',
                    views: {
                        "top": {
                            templateUrl: "app/ficha/ficha.top.tmpl.html",
                            controller: "FichaTopController",
                            controllerAs: "topVm"
                        },
                        "": {
                            templateUrl: 'app/ficha/funcionario.edit.tmpl.html',
                            controller: "FuncionarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('fichaedit', {
                    url: '/ficha/edit/:id',
                    views: {
                        "top": {
                            templateUrl: "app/ficha/ficha.top.tmpl.html",
                            controller: "FichaTopController",
                            controllerAs: "topVm"
                        },
                        "": {
                            templateUrl: 'app/ficha/funcionario.edit.tmpl.html',
                            controller: "FuncionarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('fichafuncionarioevento', {
                    url: '/ficha/edit/:id/folha',
                    views: {
                        "top": {
                            templateUrl: "app/ficha/ficha.top.tmpl.html",
                            controller: "FichaTopController",
                            controllerAs: "topVm"
                        },
                        "": {
                            templateUrl: 'app/folha/funcionario-evento.tmpl.html',
                            controller: "FuncionarioEventoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('fichafuncionarioevento.add', {
                    url: "/add",
                    onEnter: onEnterModal('FuncionarioEventoEditController', 'app/folha/funcionario-evento.edit.tmpl.html', 'fichafuncionarioevento'),
                    onExit: function ($mdDialog) {
                        $mdDialog.hide();
                    }
                })
                .state('fichafuncionarioevento.edit', {
                    url: "/edit/:idCargo",
                    onEnter: onEnterModal('FuncionarioEventoEditController', 'app/folha/funcionario-evento.edit.tmpl.html', 'fichafuncionarioevento'),
                    onExit: function ($mdDialog) {
                        $mdDialog.hide();
                    }
                })

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