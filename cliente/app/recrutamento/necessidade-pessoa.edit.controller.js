(function () {
    'use strict';
    angular.module('app').controller('NecessidadePessoaEditController', ['NecessidadePessoa', '$state', 'Workspace', '$q', 'Cargo', NecessidadePessoaEditController]);

    var state = "necessidade-pessoa"

    function NecessidadePessoaEditController(NecessidadePessoa, $state, Workspace, $q, Cargo) {
        var vm = this;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.showEdit = showEdit;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.list = []
        vm.selectedItems = []
        vm.cargos = []
        vm.situacoes = [
            {
                cod: "p",
                nome: "Pendente"
            },
            {
                cod: "n",
                nome: "Análise"
            },
            {
                cod: "r",
                nome: "Reprovado"
            },
             {
                cod: "a",
                nome: "Aprovado"
            }
        ]
        Workspace.title = "NecessidadePessoa"
        Workspace.enableSearch(onFilter)

        vm.query = {
            filter: "",
            order: 'id',
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
                    Workspace.showMessage("Registros excluidos");
                    load(vm.query);
                }));
            });
        }
        loadCargos()
        function loadCargos() {
            Cargo.query().$promise.then(function (resposta) {
                vm.cargos = resposta.data
            })
        }

        function showAdd() {
            $state.go(state + "add")
        }

        function showEdit(e, id) {
            $state.go(state + "edit", {"id": id})
        }

        function load(query) {
            vm.promise = NecessidadePessoa.query(query, success).$promise;
            loadCount()
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
            NecessidadePessoa.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });

        }


    }

})()