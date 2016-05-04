(function () {
    angular.module('app').controller('CalculoFolhaDialogController', ['Evento', '$mdDialog', CalculoFolhaDialogController])

    function CalculoFolhaDialogController(Evento, $mdDialog) {
        var modalVm = this;
        modalVm.add = add;
        modalVm.calcular = calcular;
        modalVm.cancel = cancel;
        modalVm.eventos = [];
        modalVm.entity = {
            eventos: [
                {referencia: 0}
            ]
        }

        loadEventos();
        function loadEventos() {
            Evento.query().$promise.then(function (r) {
                modalVm.eventos = r
            })
        }
        function add() {
            modalVm.entity.eventos.push({referencia: 0})
        }
        function calcular($event,$valid) {
            if(!$valid)
                return;
            $mdDialog.hide(modalVm.entity.eventos);
        }
        function cancel() {
            $mdDialog.cancel();
        }
    }
})()

