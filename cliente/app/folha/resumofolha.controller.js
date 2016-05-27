(function() {
'use strict';

    angular
        .module('app')
        .controller('ResumoFolhaController', ResumoFolhaController);

    ResumoFolhaController.$inject = ['Workspace','FolhaCalculada'];
    function ResumoFolhaController(Workspace,FolhaCalculada) {
        var vm = this;
        
        vm.exibir = exibir;
        vm.entity = {   
            mes:new Date().getMonth()+1,
            ano:new Date().getFullYear()
        }
        activate();

        ////////////////

        function activate() { }
        function exibir($valid) {
            if(!$valid)return;
            FolhaCalculada.resumoFolha({
                ano:vm.entity.ano,
                mes:vm.entity.mes,
                empresa:vm.entity.empresa.id
            }).$promise.then(function(r){
                vm.data = r;
                console.log(r)
            })
            
        }
    }
})();