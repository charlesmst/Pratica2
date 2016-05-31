(function () {
    'use strict';
    angular.module('app').controller('EventoController', ['Evento', '$state', 'Workspace', '$q', EventoController]);


    function EventoController(Evento, $state, Workspace, $q) {
        var vm = this;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.showEdit = showEdit;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.list = []
        vm.selectedItems = []
        Workspace.title = "Eventos"
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
                    Workspace.showMessage("Registros Excluidos!");
                    load(vm.query);
                }));
            });
        }
        function showAdd() {
            $state.go("eventoadd")
        }

        function showEdit(e, id) {
            $state.go("eventoedit", {"id": id})
        }

        function load(query) {
            vm.promise = Evento.query(query, success).$promise;
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
            Evento.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });

        }


    }

})()