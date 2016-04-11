(function () {
    angular.module('app').directive('showErrors', ["$compile","$animate", showErrors]);
    var messages = {
        "required": "Campo é obrigatório",
        "maxlength": "Mínimo {0} caracteres",
        "minlength": "Máximo {0} caracteres"
    }
    function showErrors($compile,$animate) {

        return {
            restrict: 'A',
            require: '^form',
            link: function (scope, el, attrs, ngModelCtrl) {
                var data = el.data();
                var inputName = el.attr('name');
                if (!inputName)
                    return;
                var baseFormName = ngModelCtrl.$name;
                var baseFieldName = baseFormName + '.' + inputName + '.$error';
                var validators = {};
                if (attrs["required"] === true)
                    validators["required"] = messages["required"]

                if (attrs["maxlength"]) {
                    validators["maxlength"] = messages["maxlength"]
                }

                if (attrs["minlength"]) {
                    validators["minlength"] = messages["minlength"]
                }
                angular.forEach(data, function (value, key) {
                    if (key.indexOf('se') === 0) {
                        var validationProperty = key.substring(2).toLowerCase();
                        validators[validationProperty] = value;
                    }
                });
                console.log(validators)
                var validationMessages = '<div ng-messages="' + baseFieldName + '">';
                for (var a in validators)
                    validationMessages += '<div ng-message="' + a + '">' + validators[a] + '</div>';
                validationMessages += "</div>";
                scope.validators = validators;

                var elementCompiled = $compile('<ng-messages for="'+baseFieldName+'">\n\
<ng-message when="required" >Campo obrigatório<ng-message >\n\
</ng-messages>')(scope)
//                console.log($animate.enabled(elementCompiled))
                el.after(elementCompiled);


            }
        }
    }



})();

