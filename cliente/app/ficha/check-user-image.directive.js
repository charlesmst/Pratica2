(function () {
    'use strict';

    angular
        .module('app')
        .directive('checkUserImage', ['config', '$http', checkUserImage]);


    function checkUserImage(config, $http) {
        return {
            restrict: 'A',
            link: link
        }


        function link(scope, element, attrs) {
            attrs.$observe('src', function (ngSrc) {
                var tester = new Image();
                tester.onerror = function () {
                    scope.$apply(function(){
                    element.attr('src', config.imageUrl + "/0.jpg"); // set default image
                        
                    })
                };
                tester.src = ngSrc;
                
            });

        }
    }

})();