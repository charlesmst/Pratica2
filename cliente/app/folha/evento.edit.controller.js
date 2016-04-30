(function () {
    'use strict';
    angular.module('app').controller('EventoEditController', ['$mdToast', '$http', 'Evento', '$state', '$stateParams', 'Workspace', 'SharedData', '$mdDialog', EventoEditController]);
    var state = "evento"



//                        https://github.com/ajaxorg/ace-builds/tree/master/src-min-noconflict
    function EventoEditController($mdToast, $http, Evento, $state, $stateParams, Workspace, SharedData, $mdDialog) {
        var vm = this;
        vm.entity = {}
        vm.sharedData = SharedData;
        vm.sharedData.selectedIndex = 0;
        Workspace.title = "Manutenção de Evento";
        vm.test = test;
        vm.save = save;
        vm.cancel = cancel;
        vm.eventos = []
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Evento.get({id: $stateParams.id}).$promise.then(function (data) {
                vm.entity = data
            }))
        }
        else
            vm.entity = new Evento()
        vm.entity.eventoDependencias = vm.entity.eventoDependencias || [];
        loadEventos();
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }
        function test(ev) {

            $mdDialog.show({
                controller: DialogController,
                templateUrl: 'modal.edit.evento.html',
                parent: angular.element(document.body),
                targetEvent: ev,
                clickOutsideToClose: true
            })

        }
        function loadEventos() {
            Evento.query().$promise.then(function (r) {
                vm.eventos = r
            })
        }

        function DialogController($scope, $mdDialog, FuncionarioCargo) {
            var modalVm = $scope;
            modalVm.cancel = cancel;
            modalVm.testar = testar;
            modalVm.querySearch = querySearch;
            function cancel() {
                $mdDialog.cancel()
            }
            function testar() {
                Evento.test({
                    funcionario: modalVm.funcionario.id
                }, vm.entity).$promise.then(function (r) {
                    modalVm.log = "";
                    angular.forEach(r.logs, function (item, k) {
                        modalVm.log += '"' + k + '":' + JSON.stringify(item) + "\n";
                    })
                    modalVm.eventos = r.eventos;
                })
            }
            function querySearch(text) {
                return FuncionarioCargo.query({
                    filter: text
                }).$promise;
            }
        }

    }

})()