(function () {
    angular.module('app').service("Workspace", ['$rootScope', Workspace]);

    function Workspace($rootScope) {
        var vm = this;
        vm.title = "";
        vm.searchEnable = false;
        vm.search = "";
        vm.showSearch = false;
        vm.onFilterChange = undefined;
        vm.setDefaults = setDefaults;
        $rootScope.$on('$stateChangeStart', setDefaults)
        
        init()
        
        function init(){
            
        }
        function setDefaults() {
            vm.title = "";
            vm.searchEnable = false;
            vm.search = "";
            vm.showSearch = false;
            vm.onFilterChange = undefined;
        }
        

    }
})();