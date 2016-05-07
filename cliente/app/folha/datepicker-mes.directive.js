(function () {
    angular.module('app').directive('datepickerMes', ['$http', datepickerMes])
    function datepickerMes($http) {
        return {
            templateUrl: 'app/folha/datepicker-mes.tmpl.html',
            link: link,
            scope: {
                mes: '=',
                ano: '=',
                isRequired:'=required'
            },
        };

        function link(scope, element, attrs, controller, transcludeFn) {
            scope.meses = []
            
            $http.get("data/meses.json").then(function (r) {
                scope.meses = r.data
            })

        }

    }

})()

