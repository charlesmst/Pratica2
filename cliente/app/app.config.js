(function () {
    angular.module('app').config(["$mdThemingProvider", '$httpProvider', '$mdIconProvider', config]);
    function config($mdThemingProvider, $httpProvider, $mdIconProvider) {
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

        $httpProvider.interceptors.push('ApiInterceptor');



        $mdIconProvider
                .icon('edit', 'assets/icon/edit.svg', 24)
                .icon('logout', 'assets/icon/ic_exit_to_app.svg', 48)
                .icon('nivel', 'assets/icon/alterar_nivel.svg', 48)
                .icon('print', 'img/icons/print.svg', 24)
                .icon('hangout', 'img/icons/hangout.svg', 24)
                .icon('mail', 'img/icons/mail.svg', 24)
                .icon('message', 'img/icons/message.svg', 24)
                .icon('copy2', 'img/icons/copy2.svg', 24)
                .icon('facebook', 'img/icons/facebook.svg', 24)
                .icon('twitter', 'img/icons/twitter.svg', 24);
//         $httpProvider.interceptors.push('ApiInterceptor')

    }


})()
