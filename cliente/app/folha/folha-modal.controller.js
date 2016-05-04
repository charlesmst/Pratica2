(function(){
    angular.module('app').controller('FolhaExibicaoController',['$mdDialog','Dados','FolhaCalculada',FolhaExibicaoController]);
    
    function FolhaExibicaoController($mdDialog,Dados,FolhaCalculada){
        var vm = this;
        vm.eventos = [];
        vm.cancelar = cancelar;
        function loadFolha(id){
            FolhaCalculada.get({id:id}).$promise.then(function(r){
                vm.eventos = r;
            });
        }
        function cancelar(){
            $mdDialog.cancel();
        }        
    }
})();

