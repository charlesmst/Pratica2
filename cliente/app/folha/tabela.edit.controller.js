
(function () {
    'use strict';
    angular.module('app').controller('TabelaEditController', ['$mdToast', '$http', 'Tabela', '$state', '$stateParams', 'Workspace', '$q', TabelaEditController]);

    var state = "tabela"
    function TabelaEditController($mdToast, $http, Tabela, $state, $stateParams, Workspace, $q) {
        var vm = this;
        vm.entity = {}
        vm.guessTipo = []
        vm.querySearchTipo = querySearchTipo;
        vm.newTipo = newTipo;
        vm.ano = new Date().getFullYear();
        vm.mes = new Date().getMonth();
        Workspace.title = "Manutenção de Tabelas";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Tabela.get({ id: $stateParams.id }).$promise.then(function (data) {

                vm.entity = data
                var d = Workspace.toDate(vm.entity.dataInicio)
                console.log(vm.entity.dataInicio)
                vm.ano = d.getFullYear();
                vm.mes = d.getMonth()+1;
                vm.entity.tabelaValoreses =vm.entity.tabelaValoreses|| []

                angular.forEach(vm.entity.tabelaValoreses, function (v) {
                    v.aliquota = v.aliquota / 100
                })

            }))

        }
        else {
            vm.entity = new Tabela()
            vm.entity.tabelaValoreses = []
        }
        vm.save = save;
        vm.cancel = cancel;
        loadGuesses();
        function save($event, $valid) {
            if (!$valid)
                return;
            angular.forEach(vm.entity.tabelaValoreses, function (v) {
                v.aliquota = v.aliquota * 100
            })
            vm.entity.dataInicio = new Date(vm.ano,vm.mes-1,1)
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro Salvo!")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
        }
        function loadGuesses() {
            return $q(function (resolve, reject) {
                if (vm.guessTipo.length == 0)
                    Tabela.tipos().$promise.then(function (data) {
                        vm.guessTipo = data
                        resolve(data)
                    }, reject)
                else
                    resolve(vm.guessTipo)
            });
        }
        function querySearchTipo(query) {
            return $q(function (resolve, reject) {
                loadGuesses().then(function (d) {
                    var results;

                    if (query)
                        results = d.filter(function (t) {
                            return t.indexOf(query) >= 0;
                        });
                    else
                        results = d;
                    resolve(results)
                })

            })



        }
        function newTipo(texto) {
            vm.guessTipo.push(texto);
            vm.entity.tipo = texto
        }
    }

})()