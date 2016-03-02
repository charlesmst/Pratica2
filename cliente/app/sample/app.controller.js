(function () {
    'use strict';
    angular.module('app').controller('AppController', ['$mdBottomSheet', '$mdSidenav', '$mdDialog','Workspace', AppController]);


    function AppController($mdBottomSheet, $mdSidenav, $mdDialog,Workspace) {
        var vm = this;
        vm.alert = '';
        vm.workspace = Workspace;
        vm.showListBottomSheet = showListBottomSheet;
        vm.toggleSidenav = toggleSidenav;
        vm.menu = [
            {
                link: '',
                title: 'Dashboard',
                icon: 'dashboard'
            },
            {
                link: '',
                title: 'Friends',
                icon: 'group'
            },
            {
                link: '',
                title: 'Messages',
                icon: 'message'
            }
        ];
        vm.admin = [
            {
                link: '',
                title: 'Trash',
                icon: 'delete'
            },
            {
                link: 'showListBottomSheet($event)',
                title: 'Settings',
                icon: 'settings'
            }
        ];
        

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
        function toggleSidenav(menuId) {
            $mdSidenav(menuId).toggle();
        }
       
    }

})()