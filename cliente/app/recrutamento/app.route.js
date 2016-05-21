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
//                .state('start', {
//                    url: '/',
//                    views: {
//                        "": {
//                            templateUrl: 'app/sample/activity.tmpl.html',
//                            controller: "ActivityController",
//                            controllerAs: "activityVm",
//                        },
//                        "top": {
//                            templateUrl: 'app/sample/activity.top.tmpl.html'
//                        }
//                    }
//
//                })
                .state('questao', {
                    url: '/questao',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/questao.tmpl.html',
                            controller: "QuestaoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('questaoadd', {
                    url: '/questao/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/questao.edit.tmpl.html',
                            controller: "QuestaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('questaoedit', {
                    url: '/questao/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/questao.edit.tmpl.html',
                            controller: "QuestaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                //Necessidade Pessoa
                
                .state('necessidade-pessoa', {
                    url: '/necessidade-pessoa',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/necessidade-pessoa.tmpl.html',
                            controller: "NecessidadePessoaController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('necessidade-pessoaadd', {
                    url: '/necessidade-pessoa/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/necessidade-pessoa.edit.tmpl.html',
                            controller: "NecessidadePessoaEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('necessidade-pessoaedit', {
                    url: '/necessidade-pessoa/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/necessidade-pessoa.edit.tmpl.html',
                            controller: "NecessidadePessoaEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                //Vagas
                
                .state('vagas', {
                    url: '/vagas',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/vagas.tmpl.html',
                            controller: "VagasController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('vagasadd', {
                    url: '/vagas/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/vagas.edit.tmpl.html',
                            controller: "VagasEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('vagasedit', {
                    url: '/vagas/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/vagas.edit.tmpl.html',
                            controller: "VagasEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('curriculo', {
                    url: '/curriculo',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/curriculo.tmpl.html',
                            controller: "CurriculoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('curriculoadd', {
                    url: '/curriculo/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/curriculo.edit.tmpl.html',
                            controller: "CurriculoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('curriculoedit', {
                    url: '/curriculo/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/curriculo.edit.tmpl.html',
                            controller: "CurriculoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('usuario', {
                    url: '/usuario',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/usuario.tmpl.html',
                            controller: "UsuarioController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('usuarioadd', {
                    url: '/usuario/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/usuario.edit.tmpl.html',
                            controller: "UsuarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('usuarioedit', {
                    url: '/usuario/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/usuario.edit.tmpl.html',
                            controller: "UsuarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('usuarionaocadastrado', {
                    url: '/usuario/cadastro',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/usuario.edit.tmpl.html',
                            controller: "UsuarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
})()