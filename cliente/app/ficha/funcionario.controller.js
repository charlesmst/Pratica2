(function () {
    'use strict';
    angular.module('app').controller('FuncionarioController', ['Pessoa', '$state', 'Workspace', '$q', 'config', FuncionarioController]);

    var state = "ficha"

    function FuncionarioController(Pessoa, $state, Workspace, $q, config) {
        var vm = this;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.showEdit = showEdit;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.getImage = getImage;
        vm.refresh = refresh;
        vm.list = []
        vm.selectedItems = []
        Workspace.title = "Colaborador"
        Workspace.enableSearch(onFilter)

        vm.query = {
            filter: "",
            order: 'id',
            limit: 10,
            page: 1,
            somenteFuncionarios:1
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

        function showEdit(e, id) {
            $state.go(state + "edit", { "id": id })
        }

        function load(query) {
            vm.promise = Pessoa.query(query, success).$promise;
            loadCount()

        }

        function success(response) {
            vm.list = response;
        }

        function onPaginate(page, limit) {
            load(angular.extend({}, vm.query, { page: page, limit: limit }));
        }

        function onReorder(order) {
            load(angular.extend(vm.query, { order: order }));
        }

        function onFilter(filter) {
            load(angular.extend(vm.query, { 'filter': filter, page: 1 }))

        }
        function loadCount() {
            Pessoa.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });

        }
        function getImage(row) {
            return config.imageUrl + "/" + (row.imagem || "0.jpg")
        }
        function refresh(){
            load(vm.query);
        }
    }

})()