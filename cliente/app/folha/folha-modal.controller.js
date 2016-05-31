(function () {
    angular.module('app').controller('FolhaExibicaoController', ['$mdDialog', 'Dados', 'FolhaCalculada', FolhaExibicaoController]);

    function FolhaExibicaoController($mdDialog, Dados, FolhaCalculada) {
        var vm = this;
        vm.eventos = [];
        vm.dados = {};
        vm.cancelar = cancelar;
        loadFolha(Dados.id)
        function loadFolha(id) {
            FolhaCalculada.get({id: id}).$promise.then(function (r) {
                vm.eventos = [{titulo: "Eventos do Funcionário", eventos: r.eventos}];
                if (r.eventosInvisiveis)
                    vm.eventos.push({titulo: "Eventos da empresa", eventos: r.eventosInvisiveis})
                vm.dados = r;
                console.log(r)
            });
        }
        function cancelar() {
            $mdDialog.cancel();
        }
    }
})();

