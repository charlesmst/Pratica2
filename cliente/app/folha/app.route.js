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

            .state('tabela', {
                url: '/tabela',
                views: {
                    "": {
                        templateUrl: 'app/folha/tabela.tmpl.html',
                        controller: "TabelaController",
                        controllerAs: "crudVm",
                    }
                }
            })
            .state('tabelaadd', {
                url: '/tabela/add',
                views: {
                    "": {
                        templateUrl: 'app/folha/tabela.edit.tmpl.html',
                        controller: "TabelaEditController",
                        controllerAs: "crudVm",
                    }
                }
            })
            .state('tabelaedit', {
                url: '/tabela/edit/:id',
                views: {
                    "": {
                        templateUrl: 'app/folha/tabela.edit.tmpl.html',
                        controller: "TabelaEditController",
                        controllerAs: "crudVm",
                    }
                }
            })
            .state('cargoevento', {
                url: '/cargo/edit/:id/folha',
                views: {
                    "top": {
                        templateUrl: "app/ficha/ficha.top.tmpl.html",
                        controller: "FichaTopController",
                        controllerAs: "topVm"
                    },
                    "": {
                        templateUrl: 'app/folha/evento-cargo.tmpl.html',
                        controller: "EventoCargoController",
                        controllerAs: "crudVm",
                    }
                }
            })
            .state('cargoevento.add', {
                url: "/add",
                onEnter: onEnterModal('EventoCargoEditController', 'app/folha/evento-cargo.edit.tmpl.html', 'cargoevento'),
                onExit: function ($mdDialog) {
                    $mdDialog.hide();
                }
            })
            
            .state('resumofolha', {
                url: '/resumofolha',
                views: {
                    "": {
                        templateUrl: 'app/folha/resumofolha.controller.tmpl.html',
                        controller: "ResumoFolhaController",
                        controllerAs: "crudVm",
                    }
                }
            })
    }
})()