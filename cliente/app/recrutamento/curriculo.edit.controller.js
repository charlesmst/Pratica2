(function () {
    'use strict';
    angular.module('app').controller('CurriculoEditController', ['$mdToast', '$http', 'Curriculo', '$state', '$stateParams', 'Workspace', 'Escolaridade', 'EstadoCivil', 'Cidade', 'CurriculoExperiencia', 'CurriculoQualificacao', 'CurriculoFormacao', '$mdDialog', CurriculoEditController]);

    var state = "curriculo"
    function CurriculoEditController($mdToast, $http, Curriculo, $state, $stateParams, Workspace, Escolaridade, EstadoCivil, Cidade, CurriculoExperiencia, CurriculoQualificacao, CurriculoFormacao, $mdDialog) {
        var vm = this;
        vm.entity = {}
        vm.id = $stateParams.id;
        vm.sexos = []
        vm.escolaridades = []
        vm.categoriasDeCnh = []
        vm.estadosCivis = []
        vm.curriculoFormacoes = []
        vm.curriculoQualificacoes = []
        vm.curriculoExperiencias = []
        vm.querySearchCidade = querySearchCidade
        vm.mostraAddFormacao = mostraAddFormacao
        vm.mostraEditFormacao = mostraEditFormacao
        vm.getNome = getNome
        Workspace.title = "Manutenção de Currículo";

        loadSexos()
        loadSituacoes()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
        loadCurriculoExperiencias()
        loadCurriculoFormacoes()
        loadCurriculoQualificacoes()

        vm.save = save;
        vm.cancel = cancel;
        loadEdit()
        function loadEdit() {
            if ($stateParams.id) {
                Workspace.loading("Carregando...", Curriculo.get({id: $stateParams.id}).$promise.then(function (data) {

                    vm.entity = data

                }))

            } else
                vm.entity = new Curriculo()


        }
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

        function loadSituacoes() {
            return $http.get('data/recrutamento/situacaoCurriculoFormacao.json').then(function (resposta) {
                vm.situacoes = (resposta.data)
            })
        }

        function loadCategoriasCnh() {
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

        function getNome(situacao) {
            
            console.log(situacao,vm.situacoes)
            return vm.situacoes[parseInt(situacao)-1].nome
        }

        function loadCurriculoFormacoes() {
            CurriculoFormacao.curriculoformacao({pessoaId: vm.id}).$promise.then(function (r) {

                vm.curriculoFormacoes = r;

            })

        }

        function loadCurriculoQualificacoes() {
            CurriculoQualificacao.curriculoqualificacao({pessoaId: vm.id}).$promise.then(function (r) {

                vm.curriculoQualificacoes = r;

            })

        }

        function loadCurriculoExperiencias() {
            CurriculoExperiencia.curriculoexperiencia({pessoaId: vm.id}).$promise.then(function (r) {

                vm.curriculoExperiencias = r;

            })

        }

        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }

        function mostraAddFormacao() {
            $mdDialog.show({
                controller: 'CurriculoFormacaoEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-formacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFormacao: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.curriculoFormacoes)
                            vm.curriculoFormacoes = []
                        vm.curriculoFormacoes.push(adicionado)
                    });
        }


        function mostraEditFormacao(formacao) {
            $mdDialog.show({
                controller: 'CurriculoFormacaoEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-formacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFormacao: function () {
                        return angular.copy(formacao);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(formacao, alterado)
            })
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