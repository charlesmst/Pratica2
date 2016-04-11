(function () {
    angular.module("app").directive('autofocus', ['$timeout', autofocus]);
    
    function autofocus($timeout) {
        return {
            restrict: 'A',
            link: function ($scope, $element) {
                console.log('focus')
                $timeout(function () {
                    $element[0].focus();
                });
            }
        }
    }
})();

