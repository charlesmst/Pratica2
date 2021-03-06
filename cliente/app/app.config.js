(function () {
    angular.module('app').config(["$mdThemingProvider", '$httpProvider', '$mdIconProvider', '$mdDateLocaleProvider', config]);
    function config($mdThemingProvider, $httpProvider, $mdIconProvider, $mdDateLocaleProvider) {
        var customBlueMap = $mdThemingProvider.extendPalette('light-blue', {
            'contrastDefaultColor': 'light',
            'contrastDarkColors': ['50'],
            '50': 'ffffff'
        });
        $mdThemingProvider.definePalette('customBlue', customBlueMap);
        $mdThemingProvider.theme('default')
                .primaryPalette('teal', {
                    'default': '600',
                    'hue-1': '800',
                    'hue-2': '700'
                })
                .accentPalette('teal', {
                    'default': '800',
                })

        $mdThemingProvider.theme('input', 'default')
                .primaryPalette('teal', {
                    'default': '600',
                    'hue-1': '800',
                    'hue-2': '700'
                });
        $mdDateLocaleProvider.formatDate = function (date) {
            console.log("DateFormat ",date)
            if(!date)
                return "";
            return moment(date).format('DD/MM/YYYY');
        };

        $mdDateLocaleProvider.parseDate = function (dateString) {
            if(dateString === null || typeof(dateString) === "undefined")
                return null;
            var m = moment(dateString, 'DD/MM/YYYY', true);
//            return 
//            m.isValid() ? 
            return m.toDate() 
//            : null;
        };
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

                .icon('login', 'assets/icon/user-in-front-of-computer.svg', 38)
                .icon('cadastre', 'assets/icon/add-contact.svg', 38)

                .icon('tabela', 'assets/icon/spreadsheet.svg', 38)
                .icon('eventos', 'assets/icon/signing-receipt.svg', 38)
                .icon('calculator', 'assets/icon/calculator.svg', 38)
                .icon('folha', 'assets/icon/file-black-text-paper-sheet-symbol-with-one-folded-corner.svg', 38)

                .icon('ficha', 'assets/icon/ficha-funcional.svg', 38)
                .icon('cargos', 'assets/icon/stationery-product.svg', 38)
                .icon('cbo', 'assets/icon/bar-code.svg', 38)
                .icon('qualificacao', 'assets/icon/3-star-hotel.svg', 38)
                .icon('acidente', 'assets/icon/car-first-aid-kit.svg', 38)
                .icon('sindicato', 'assets/icon/workers-team.svg', 38)
                .icon('empresa', 'assets/icon/buildings.svg', 38)
                .icon('unidade', 'assets/icon/buildings-unidades.svg', 38)
                .icon('curriculo', 'assets/icon/curriculos.svg', 38)

                .icon('necessidade', 'assets/icon/business-man.svg', 38)
                .icon('vagas', 'assets/icon/jobs-open.svg', 38)
                .icon('avaliacao', 'assets/icon/favorites-list.svg', 38)

                .icon('faixa', 'assets/icon/stock-earnings.svg', 38)
                .icon('user', 'assets/icon/users.svg', 38);

        //  $httpProvider.interceptors.push('ApiInterceptor')
    }
})()