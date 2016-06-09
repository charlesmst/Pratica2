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
                .state('vagasview', {
                    url: '/vagas-abertas',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/vagas-single.tmpl.html',
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
                .state('curriculocandidato', {
                    url: '/candidato',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/curriculo.edit.tmpl.html',
                            controller: "CurriculoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                
                .state('usuarioadd', {
                    url: '/registro',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/usuario.edit.tmpl.html',
                            controller: "UsuarioEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                
                .state('plano-avaliacao', {
                    url: '/plano-avaliacao',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/plano-avaliacao.tmpl.html',
                            controller: "PlanoAvaliacaoController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('plano-avaliacaoadd', {
                    url: '/plano-avaliacao/add',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/plano-avaliacao.edit.tmpl.html',
                            controller: "PlanoAvaliacaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
                .state('plano-avaliacaoedit', {
                    url: '/plano-avaliacao/edit/:id',
                    views: {
                        "": {
                            templateUrl: 'app/recrutamento/plano-avaliacao.edit.tmpl.html',
                            controller: "PlanoAvaliacaoEditController",
                            controllerAs: "crudVm",
                        }
                    }
                })
    }
})()