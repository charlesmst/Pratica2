(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEventoController', ['EventoFuncionario', '$state', 'Workspace', '$q', 'FuncionarioCargo', '$stateParams', '$scope', FuncionarioEventoController]);

    var state = "Evento"

    function FuncionarioEventoController(EventoFuncionario, $state, Workspace, $q, FuncionarioCargo, $stateParams, $scope) {
        var vm = this;
        vm.id = $stateParams.id;
        vm.showDelete = showDelete;
        vm.showAdd = showAdd;
        vm.showEdit = showEdit;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.list = []
        vm.selectedItems = []
        Workspace.title = "Evento"
        Workspace.enableSearch(onFilter)
        vm.funcionarioCargo = {}
        vm.funcionarioCargos = []
        vm.query = {
            filter: "",
            order: 'id',
            limit: 10,
            page: 1
        };
        vm.mes = new Date().getMonth();
        vm.ano = new Date().getFullYear();
        $scope.$watchGroup(["crudVm.funcionarioCargo", "crudVm.mes", "crudVm.ano"], function () {
            load(vm.query)
        })
        loadCargos()
        
        Workspace.callbackOnEnterState($scope,$state,loadCargos)

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
                }, function (r) {
                    Workspace.showError(r.data.mensagem)
                }));
            });
        }
        function showAdd() {
            $state.go('fichafuncionarioevento.add').then(loadCargos)
        }

        function showEdit(e, id) {
            $state.go('fichafuncionarioevento.edit', {"idCargo": id}).then(loadCargos)
        }

        function load(query) {
            query.cargoId = vm.funcionarioCargo.id;
            query.mes = vm.mes;
            query.ano = vm.ano;
            if (query.cargoId) {
                vm.promise = EventoFuncionario.queryC(query, success).$promise;
                loadCount()
            }

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
            EventoFuncionario.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });

        }

        function loadCargos() {
            FuncionarioCargo.funcionario({pessoaId: vm.id}).$promise.then(function (r) {

                vm.funcionarioCargos = r;
                if (vm.funcionarioCargos.length > 0)
                    vm.funcionarioCargo = vm.funcionarioCargos[0]
            })

        }


    }

})()