(function () {
    'use strict';
    angular.module('app').controller('AppController', ['$mdBottomSheet', '$mdSidenav', '$mdDialog','Workspace','hotkeys','$scope','$state', AppController]);


    function AppController($mdBottomSheet, $mdSidenav, $mdDialog,Workspace,hotkeys,$scope,$state) {
        var vm = this;
        vm.alert = '';
        vm.workspace = Workspace;
        vm.showListBottomSheet = showListBottomSheet;
        vm.toggleSidenav = toggleSidenav;
        vm.toggleSearch = toggleSearch;
        vm.goTo = goTo;
        
        vm.labelPagination = {
            of:"de",
            page:"Página",
            rowsPerPage:"Linhas por página:"
        }
        vm.menu = [
            {
                link: '',
                title: 'Folha de pagamento',
                icon: 'dashboard'
            },
            {
                link: '',
                title: 'Ficha funcional',
                icon: 'group'
            },
            {
                link: '',
                title: 'Recrutamento e seleção',
                icon: 'message'
            }
        ];
        vm.admin = [
            {
                link: 'menu',
                title: 'Menu',
                icon: 'delete'
            },
            {
                link: 'showListBottomSheet($event)',
                title: 'Configurações',
                icon: 'settings'
            }
        ];
        init()

        function showListBottomSheet($event) {
            vm.alert = '';
            $mdBottomSheet.show({
                template: '<md-bottom-sheet class="md-list md-has-header"> <md-subheader>Settings</md-subheader> <md-list> <md-item ng-repeat="item in listVm.items"><md-item-content md-ink-ripple flex class="inset"> <a flex aria-label="{{item.name}}" ng-click="listItemClick($index)"> <span class="md-inline-list-icon-label">{{ item.name }}</span> </a></md-item-content> </md-item> </md-list></md-bottom-sheet>',
                controller: 'ListBottomSheetController',
                controllerAs:"listVm",
                targetEvent: $event
            }).then(function (clickedItem) {
                vm.alert = clickedItem.name + ' clicked!';
            });
        }
        function goTo(r){
            $state.go(r)
        }
        function toggleSidenav(menuId) {
            $mdSidenav(menuId).toggle();
        }
        function toggleSearch(){
            vm.workspace.showSearch = !vm.workspace.showSearch
            vm.workspace.search = ""
        }
        function init(){
            hotkeys.bindTo($scope).add({
                combo:"esc",
                description:"Cancelar busca",
                allowIn:['INPUT'],
                callback:function(){
                    if(vm.workspace.showSearch)
                        toggleSearch()
                }
            })
        }
    }

})()