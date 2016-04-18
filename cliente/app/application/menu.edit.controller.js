(function () {
    'use strict';
    angular.module('app').controller('MenuEditController', ['$mdToast', '$http', 'Menu', '$state', '$stateParams', 'Workspace', 'Nivel', '$q', MenuEditController]);


    function MenuEditController($mdToast, $http, Menu, $state, $stateParams, Workspace, Nivel, $q) {
        var vm = this;
        vm.entity = {}
        vm.save = save;
        vm.cancel = cancel;
        vm.niveis = []

        vm.querySearch = querySearch;

        Workspace.title = "Manutenção de Menu";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Menu.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        }
        else {
            vm.entity = new Menu()
            vm.entity.niveis = []

        }
        loadNiveis();
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go("menu")
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go("menu")

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }
        function loadNiveis() {
            Nivel.query({}, function (dados) {
                vm.niveis = dados;
            });
        }

        /**
         * Search for vegetables.
         */
        function querySearch(query) {
            return Nivel.query({
                filter: query
            }).$promise;


        }
    }

})()