(function () {
    'use strict';
    angular.module('app').controller('AppController', ['$mdBottomSheet', '$mdSidenav', '$mdDialog', 'Workspace', 'hotkeys', '$scope', '$state', 'Authorization', 'AuthorizationData', '$rootScope', 'config', AppController]);


    function AppController($mdBottomSheet, $mdSidenav, $mdDialog, Workspace, hotkeys, $scope, $state, Authorization, AuthorizationData, $rootScope, config) {
        var vm = this;
        vm.alert = '';
        vm.isSubState = false;
        vm.config = config;

        // $rootScope.$on("api-error",handleError);

        vm.workspace = Workspace;
        vm.showListBottomSheet = showListBottomSheet;
        vm.toggleSidenav = toggleSidenav;
        vm.toggleSearch = toggleSearch;
        vm.goTo = goTo;
        vm.authorization = Authorization;
        vm.authorizationData = AuthorizationData;
        $rootScope.$on("unauthorized", handleUnauthorized);
        vm.labelPagination = {
            of: "de",
            page: "Página",
            rowsPerPage: "Linhas por página:"
        }


        $rootScope.$on('$stateChangeStart', function ($event, state) {
            vm.isSubState = state.name.indexOf('.') > 0;

        })

        init()
        function showListBottomSheet($event) {
            vm.alert = '';
            $mdBottomSheet.show({
                templateUrl: 'app/components/list-bottom-sheet.tmpl.html',
                controller: ListBottomController,
                controlerAs: "listVm",
                targetEvent: $event
            }).then(function (clickedItem) {
                clickedItem.action();
            });
        }

        function handleUnauthorized(event, args) {
            $state.go(Authorization.defaultState);
            Workspace.showMessage(args.mensagem);
        }


        function goTo(r) {
            $state.go(r)
        }
        function toggleSidenav(menuId) {
            $mdSidenav(menuId).toggle();
        }
        function toggleSearch() {
            vm.workspace.showSearch = !vm.workspace.showSearch;
            vm.workspace.search = "";
        }

        function init() {
            hotkeys.bindTo($scope).add({
                combo: "esc",
                description: "Cancelar busca",
                allowIn: ['INPUT'],
                callback: function () {
                    if (vm.workspace.showSearch)
                        toggleSearch()
                }
            })
        }
        function logout() {
            Authorization.logout().then(function () {
                $state.go(Authorization.defaultState)
                Workspace.showMessage("Logout efetuado com sucesso")
            });
        }
        function alterarNivel() {

            $mdDialog.show({
                controller: 'AlterarNivelController',
                controllerAs: "modalVm",
                templateUrl: 'app/application/alterar-nivel.controller.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: true
            }).then(function (item) {
                var u = Authorization.getCurrentUser()
                u.token = item
                Authorization.setCurrentUser(u)
                Authorization.loadNiveis().then(function () {
                    $state.go(Authorization.defaultState)

                });

            })
        }
        function ListBottomController($scope) {

            $scope.listItems = [
                {name: 'Alterar nível', icon: 'nivel', action: alterarNivel},
                {name: 'Sair', icon: 'logout', action: logout}
            ];
            $scope.clickItem = clickItem;
            function clickItem(item) {
                $mdBottomSheet.hide(item)
            }
        }
    }

})()