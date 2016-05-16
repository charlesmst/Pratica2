(function () {
    'use strict';
    angular.module('app').controller('CurriculoEditController', ['$mdToast', '$http', 'Curriculo', '$state', '$stateParams', 'Workspace', 'Escolaridade', 'EstadoCivil', 'Cidade', CurriculoEditController]);

    var state = "curriculo"
    function CurriculoEditController($mdToast, $http, Curriculo, $state, $stateParams, Workspace, Escolaridade, EstadoCivil, Cidade) {
        var vm = this;
        vm.entity = {}
        vm.sexos = []
        vm.escolaridades = []
        vm.categoriasDeCnh = []
        vm.estadosCivis = []
        vm.querySearchCidade = querySearchCidade
        Workspace.title = "Manutenção de Currículo";
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Curriculo.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
            }))

        } else
            vm.entity = new Curriculo()
       
        loadSexos()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        
        function loadSexos() {
            $http.get('data/recrutamento/sexo.json').then(function (resposta) {
                vm.sexos = (resposta.data)
            })
        }
        
        function loadCategoriasCnh(){
            $http.get('data/recrutamento/categoriasCnh.json').then(function (resposta) {
                vm.categoriasDeCnh = (resposta.data)
            })
        }
        
        function loadEscolaridades() {
            Escolaridade.query().$promise.then(function (resposta) {
                vm.escolaridades = resposta;
            })
        }
        
        function loadEstadosCivis() {
            EstadoCivil.query().$promise.then(function (resposta) {
                vm.estadosCivis = resposta;
            })
        }
        
        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }
        
        function callbackSave(r) {
            Workspace.showMessage("Registro salvo")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro")
        }

    }

})()