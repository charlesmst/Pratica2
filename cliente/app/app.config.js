(function () {
    angular.module('app').config(["$mdThemingProvider",'$httpProvider', config]);
    function config($mdThemingProvider,$httpProvider) {
        var customBlueMap = $mdThemingProvider.extendPalette('light-blue', {
            'contrastDefaultColor': 'light',
            'contrastDarkColors': ['50'],
            '50': 'ffffff'
        });
        $mdThemingProvider.definePalette('customBlue', customBlueMap);
        $mdThemingProvider.theme('default')
                .primaryPalette('customBlue', {
                    'default': '500',
                    'hue-1': '50'
                })
                .accentPalette('pink')
//                .backgroundPalette('grey')

        $mdThemingProvider.theme('input', 'default')
                .primaryPalette('grey');
        
        
        
//         $httpProvider.interceptors.push('ApiInterceptor')

    }

   
})()
