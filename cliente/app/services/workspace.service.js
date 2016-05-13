(function () {
    angular.module('app').service("Workspace", ['$rootScope', '$mdDialog', '$mdToast', '$q', Workspace]);

    function Workspace($rootScope, $mdDialog, $mdToast, $q) {
        var vm = this;
        vm.title = "";
        vm.searchEnable = false;
        vm.search = "";
        vm.showSearch = false;
        vm.onFilterChange = undefined;
        vm.toDate = toDate;
        vm.setDefaults = setDefaults;
        vm.enableSearch = enableSearch
        vm.showDeleteDialog = showDeleteDialog;
        vm.showMessage = showMessage;
        vm.loading = loading;
        vm.showConfirmDialog = showConfirmDialog;
        vm.showError = showError;
        vm.callbackOnEnterState = callbackOnEnterState;
        vm.currentLoading = 0;
        $rootScope.$on('$stateChangeStart', setDefaults)

        init()
        var lastSearch = ""
        function init() {

            $rootScope.$watch(function () {
                return vm.search
            }, function () {
                if (vm.search === lastSearch)
                    return;
                lastSearch = vm.search
                if (vm.onFilterChange)
                    vm.onFilterChange(vm.search)
            })
        }
        function enableSearch(callback) {
            vm.searchEnable = true;
            vm.onFilterChange = callback;
        }
        function setDefaults($event, $curState, $stPar, $oldState) {
            var reloadState = true;
            if ($curState && $oldState && $oldState.name && $curState.name) {
                //Se são do mesmo pai
                if ($curState.name.indexOf($oldState) === 0 || $oldState.name.indexOf($curState)) {
                    reloadState = false;
                }
            }
            if (reloadState) {
                lastSearch = ""
                vm.title = "";
                vm.searchEnable = false;
                vm.search = "";
                vm.showSearch = false;
                vm.onFilterChange = undefined;
            }
        }
        function showDeleteDialog($event) {
            var confirm = $mdDialog.confirm("Wa")
                .title('Gostaria de excluir?')
                .textContent('O registro será perdido.')
                .ok('Sim')
                .cancel('Não').openFrom($event.target).closeTo($event.target)
            return $mdDialog.show(confirm)

        }
        function showConfirmDialog($event, titulo, mensagem) {
            var confirm = $mdDialog.confirm("Wa")
                .title(titulo)
                .textContent(mensagem)
                .ok('Sim')
                .cancel('Não').openFrom($event.target).closeTo($event.target)
            return $mdDialog.show(confirm)

        }
        function stopLoading() {
            vm.currentLoading--;
            if (vm.currentLoading < 0)
                vm.currentLoading = 0;
        }
        function loading(message, p) {

            vm.currentLoading++;
            if (p) {
                return p.then(stopLoading, stopLoading)
            }
            vm.currentLoading--;
            return {
                hide: stopLoading
            };
        }
        function showMessage(message) {
            $mdToast.show(
                $mdToast.simple()
                    .textContent(message)
                    .position("bottom left")
                    .hideDelay(3000)
            );
        }
        function showError(message) {
            $mdToast.show({
                template: '<md-toast><span flex>' + message + '</span><md-button ng-click="vm.closeToast()">Fechar</md-button></md-toast>',
                hideDelay: false,
                position: "bottom left",
                controller: function ($mdToast) {
                    var vm = this
                    vm.closeToast = closeToast
                    function closeToast() {
                        $mdToast.hide()
                    }
                },
                controllerAs: "vm"

            })
        }

        function toDate(str) {
            var d = new Date(str)
            d.setDate(d.getDate() + 1)
            return d;
        }
        function callbackOnEnterState(scope, $state, callback) {
            var curState = $state.current.name
            var remove = $rootScope.$on('$stateChangeSuccess', function (event, toState, toParams, fromState, fromParams) {
                if (toState.name == curState) {
                    callback()
                }
            });
            scope.$on('$destroy', function () {
                remove();
            });

        }
    }
})();