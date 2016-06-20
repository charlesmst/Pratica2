(function () {
    'use strict';
    angular.module('app').controller('VagasController', ['Vagas', '$state', 'Workspace', '$q', '$http', 'config', VagasController]);
    var state = "vagas"

    function VagasController(Vagas, $state, Workspace, $q, $http, config) {
        var vm = this;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.showEdit = showEdit;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.inscricaoCandidato = inscricaoCandidato;
        vm.isCandidato = isCandidato;
        vm.list = []
        vm.selectedItems = []
        Workspace.title = "Vagas"
        Workspace.enableSearch(onFilter)

        vm.query = {
            filter: "",
            order: 't.id',
            limit: 10,
            page: 1
        };

        load(vm.query)
        function showDelete($event) {
            Workspace.showDeleteDialog($event).then(function () {
                //Confirmou
                var promises = [];
                var items = angular.copy(vm.selectedItems);
                vm.selectedItems = [];
                angular.forEach(items, function (value) {
                    promises.push(value.$delete());
                });
                Workspace.loading("Excluindo...", $q.all(promises).then(function () {
                    Workspace.showMessage("Registros Excluidos!");
                    load(vm.query);
                }));
            });
        }
        function showAdd() {
            $state.go(state + "add")
        }

        function inscricaoCandidato(idVaga) {
            $http.post(config.apiUrl + "/vagas/inscreveusuario/" + idVaga).then(function (r) {
                console.log(r.data);
                Workspace.showMessage("Inscrição realizada com sucesso!");
                load();
            }, function (e) {
                Workspace.showMessage("Faça login!");
                $state.go("login");
            });
        }
        
        function isCandidato(can) {
            var teste = false;
            for(var x = 0; x < can.length; x++) {
                if(can[x].isCandidato){
                    teste = true;
                }
            }
            return teste;
        }

        function showEdit(e, id) {
            $state.go(state + "edit", {"id": id})
        }

        function load(query) {
            vm.list = [];
            vm.promise = Vagas.query(query, success).$promise;
            console.log(vm.promise)

        }

        function success(response) {
            vm.list = response;
        }

        function onPaginate(page, limit) {
            load(angular.extend({}, vm.query, {page: page, limit: limit}));
        }

        function onReorder(order) {
            load(angular.extend(vm.query, {order: order}));
        }

        function onFilter(filter) {
            load(angular.extend(vm.query, {'filter': filter, page: 1}))

        }
        function loadCount() {
            Vagas.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });
        }

        function callbackSave(r) {
            Workspace.showMessage("Registro Salvo!")
        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
        }

    }
})()