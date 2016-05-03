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
        function setDefaults() {
            lastSearch = ""
            vm.title = "";
            vm.searchEnable = false;
            vm.search = "";
            vm.showSearch = false;
            vm.onFilterChange = undefined;
        }
        function showDeleteDialog($event) {
            var confirm = $mdDialog.confirm("Wa")
                    .title('Gostaria de excluir?')
                    .textContent('O registro será perdido.')
                    .ok('Sim')
                    .cancel('Não').openFrom($event.target).closeTo($event.target)
            return $mdDialog.show(confirm)

        }
        function showConfirmDialog($event,titulo,mensagem) {
            var confirm = $mdDialog.confirm("Wa")
                    .title(titulo)
                    .textContent(mensagem)
                    .ok('Sim')
                    .cancel('Não').openFrom($event.target).closeTo($event.target)
            return $mdDialog.show(confirm)

        }
        function loading(message, p) {
            var toast = $mdToast.simple()
                    .textContent(message)
                    .position("bottom left");
            toast.hide = function () {
                $mdToast.hide(toast)
            }
            $mdToast.show(toast)
            if (p) {
                return p.then(toast.hide, toast.hide)
            }
            return toast;
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
            return new Date(str)

        }

    }
})();