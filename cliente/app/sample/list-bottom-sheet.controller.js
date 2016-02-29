(function () {
    angular.module('app').controller('ListBottomSheetController', [ '$mdBottomSheet', ListBottomSheetController]);

    function ListBottomSheetController($mdBottomSheet) {
        var vm = this;
        vm.items = [
            {name: 'Share', icon: 'share'},
            {name: 'Upload', icon: 'upload'},
            {name: 'Copy', icon: 'copy'},
            {name: 'Print this page', icon: 'print'},
        ];

        vm.listItemClick = function ($index) {
            var clickedItem = $scope.items[$index];
            $mdBottomSheet.hide(clickedItem);
        };
    }
})()
