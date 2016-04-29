(function () {
    angular.module('app').config(["$stateProvider", "$urlRouterProvider", config]);
    
    function config($stateProvider, $urlRouterProvider) {
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
    }
})()