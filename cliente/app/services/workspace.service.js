(function () {
    angular.module('app').service("Workspace", ['$rootScope', '$mdDialog', '$mdToast', '$q', Workspace]);

    function Workspace($rootScope, $mdDialog, $mdToast, $q) {
        var vm = this;
        vm.title = "";
        vm.searchEnable = false;
        vm.search = "";
        vm.showSearch = false;
        vm.onFilterChange = undefined;
        vm.setDefaults = setDefaults;
        vm.enableSearch = enableSearch
        vm.showDeleteDialog = showDeleteDialog;
        vm.showMessage = showMessage;
        vm.loading = loading;
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
        function loading(message, p) {
            var toast = $mdToast.simple()
                    .textContent(message)
                    .position("top right");
            toast.hide = function () {
                $mdToast.hide(toast)
            }
            $mdToast.show(toast)
            if (p){
                return p.then(toast.hide,toast.hide)
            }
            return toast;
        }
        function showMessage(message) {
            $mdToast.show(
                    $mdToast.simple()
                    .textContent(message)
                    .position("top right")
                    .hideDelay(3000)
                    );
        }

    }
})();