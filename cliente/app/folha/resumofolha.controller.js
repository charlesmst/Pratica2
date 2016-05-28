(function() {
'use strict';

    angular
        .module('app')
        .controller('ResumoFolhaController', ResumoFolhaController);

    ResumoFolhaController.$inject = ['Workspace','FolhaCalculada','Download'];
    function ResumoFolhaController(Workspace,FolhaCalculada,Download) {
        var vm = this;
        
        vm.exibir = exibir;
        vm.download  = download
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
        function download($valid){
             if(!$valid)return;
             Workspace.loading("Baixando...",Download.downloadFile('/folhacalculada/resumofolhapagamento/'+vm.entity.empresa.id+'/'+vm.entity.ano+'/'+vm.entity.mes+'/pdf',{},'ResumoFolha'));
        }
    }
})();