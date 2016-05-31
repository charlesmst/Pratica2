(function () {
    angular.module('app').config(["$mdThemingProvider", '$httpProvider', '$mdIconProvider', config]);
    function config($mdThemingProvider, $httpProvider, $mdIconProvider) {
        var customBlueMap = $mdThemingProvider.extendPalette('blue-grey', {
            'contrastDefaultColor': 'blue',
            'contrastDarkColors': ['100'],
            '100': 'ffffff'
        });
        $mdThemingProvider.definePalette('customBlue', customBlueMap);
        $mdThemingProvider.theme('default')
                .primaryPalette('blue-grey', {
                    'default': '900', 
                    'hue-1': '500',
                    'hue-2': '900'
                })
                .accentPalette('blue-grey')
//                .backgroundPalette('grey')

        $mdThemingProvider.theme('input', 'default')
                .primaryPalette('blue-grey');

        $httpProvider.interceptors.push('ApiInterceptor');



        $mdIconProvider
                .icon('edit', 'assets/icon/edit.svg', 24)                
                .icon('logout', 'assets/icon/ic_exit_to_app.svg', 48)
                .icon('nivel', 'assets/icon/alterar_nivel.svg', 48)
                .icon('download', 'assets/icon/pdf.svg', 48)
                .icon('view', 'assets/icon/ic_pageview_black_24px.svg', 24)
                .icon('mail', 'img/icons/mail.svg', 24)
                .icon('message', 'img/icons/message.svg', 24)
                .icon('copy2', 'img/icons/copy2.svg', 24)
                .icon('facebook', 'img/icons/facebook.svg', 24)
                .icon('twitter', 'img/icons/twitter.svg', 24)                                  
                
                .icon('tabela', 'assets/icon/spreadsheet.svg', 48)    
                .icon('eventos', 'assets/icon/signing-receipt.svg', 48)
                .icon('calculator', 'assets/icon/calculator.svg', 48)
                .icon('folha', 'assets/icon/file-black-text-paper-sheet-symbol-with-one-folded-corner.svg', 48)      
        
                .icon('ficha', 'assets/icon/ficha-funcional.svg', 48)
                .icon('cargos', 'assets/icon/stationery-product.svg', 48)
                .icon('cbo', 'assets/icon/bar-code.svg', 48)
                .icon('qualificacao', 'assets/icon/3-star-hotel.svg', 48)
                .icon('acidente', 'assets/icon/car-first-aid-kit.svg', 48)
                .icon('sindicato', 'assets/icon/workers-team.svg', 48)
                .icon('empresa', 'assets/icon/buildings.svg', 48)
                .icon('unidade', 'assets/icon/buildings-unidades.svg', 48)
                .icon('curriculo', 'assets/icon/curriculos.svg', 48)
        
                .icon('necessidade', 'assets/icon/business-man.svg', 48)
                .icon('vagas', 'assets/icon/jobs-open.svg', 48)
                .icon('avaliacao', 'assets/icon/favorites-list.svg', 48)
                
                .icon('faixa', 'assets/icon/stock-earnings.svg', 48)
                .icon('user', 'assets/icon/users.svg', 48);
                
//         $httpProvider.interceptors.push('ApiInterceptor')

    }


})()
