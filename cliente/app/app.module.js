(function () {
    angular.module('app.services',['ngResource']);
    angular.module('app', ['ngMaterial','ngAnimate', 'ngMdIcons', 'ui.router','app.services','md.data.table','ngMessages','cfp.hotkeys','angular-storage','ui.ace','ui.mask']);
})()