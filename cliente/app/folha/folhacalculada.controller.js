(function () {
    'use strict';
    angular.module('app').controller('FolhaCalculadaController', ['FolhaCalculada', '$state', 'Workspace', '$q', 'Permissoes', '$mdDialog', '$scope', 'Download', FolhaCalculadaController]);

    var state = "folhacalculada"

    function FolhaCalculadaController(FolhaCalculada, $state, Workspace, $q, Permissoes, $mdDialog, $scope, Download) {
        var vm = this;

        vm.showDelete = showDelete;
        vm.getNome = getNome;
        vm.showFolha = showFolha;
        vm.onPaginate = onPaginate;
        vm.onReorder = onReorder;
        vm.printFiles = printFiles
        vm.permissoes = {};
        vm.list = [];
        vm.entity = {};
        vm.selectedItems = [];
        Workspace.title = "Folha de Pagamento";

        //        Workspace.enableSearch(onFilter)

        vm.tiposFolha = [
            "", "Mês", "Férias", "Complementar", "Décimo", "Demissão"
        ];
        vm.query = {
            filter: "",
            order: '-t.id',
            limit: 15,
            page: 1
        };
        $scope.$watchGroup(['crudVm.entity.empresa', 'crudVm.query.ano', 'crudVm.query.mes', 'crudVm.entity.funcionarios'], function () {
            load(vm.query)
        })

        loadPermissoes();

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

        function loadPermissoes() {
            Permissoes.get({ modulo: "folha" }).$promise.then(function (r) {
                vm.permissoes = r;
            });
        }
        function showFolha(e, id) {
            $mdDialog.show({
                controller: 'FolhaExibicaoController as folhaVm',
                templateUrl: 'app/folha/folha-modal.tmpl.html',
                parent: angular.element(document.body),
                targetEvent: e,
                clickOutsideToClose: true,
                resolve: {
                    Dados: function () {
                        return { id: id };
                    }
                }
            })
        }

        function load(query) {
            if (!vm.permissoes.outrosFuncionarios) {
                delete query.funcionarios;
            } else {
                query.funcionarios = [];
                if (vm.entity.funcionarios)
                    angular.forEach(vm.entity.funcionarios, function (v) {
                        query.funcionarios.push(v.id);
                    })
                console.log(query.funcionarios)

            }
            if (!vm.entity.empresa)
                return;
            query.empresa = vm.entity.empresa.id;
            vm.promise = FolhaCalculada.query(query, success).$promise;
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
            FolhaCalculada.count(vm.query).$promise.then(function (e) {
                vm.count = e.count
            });

        }
        function getNome(tipo) {
            return vm.tiposFolha[parseInt(tipo)]
        }
        function printFiles($event) {
            var files = vm.selectedItems.map(function (v) {
                return v.id;
            })
            console.log(files)
            Workspace.loading("Baixando...", Download.downloadFile('/folhacalculada/relatorio', { "ids": files }, 'folha'));

        }

    }

})()