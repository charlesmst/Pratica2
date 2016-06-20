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
//        vm.curriculoFormacoes = []
//        vm.curriculoQualificacoes = []
//        vm.curriculoExperiencias = []
        vm.querySearchCidade = querySearchCidade
        vm.mostraAddFormacao = mostraAddFormacao
        vm.mostraEditFormacao = mostraEditFormacao
        vm.mostraAddQualificacao = mostraAddQualificacao
        vm.mostraEditQualificacao = mostraEditQualificacao
        vm.mostraAddExperiencia = mostraAddExperiencia
        vm.mostraEditExperiencia = mostraEditExperiencia
        vm.getNome = getNome
        Workspace.title = "Currículo";

        loadSexos()
        loadSituacoes()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
//        loadCurriculoExperiencias()
//        loadCurriculoFormacoes()
//        loadCurriculoQualificacoes()

        vm.save = save;
        vm.cancel = cancel;
        loadEdit()
        function loadEdit() {
            Workspace.loading("Carregando...", Curriculo.get({id: $stateParams.id || 0}).$promise.then(function (data) {

                vm.entity = data

            }))
        }
        function save($event, $valid) {
            if (!$valid)
                return;
//            vm.entity.curriculoExperiencias = vm.curriculoExperiencias;
//            vm.entity.curriculoFormacoes = vm.curriculoFormacoes;
//            vm.entity.curriculoQualificacoes = vm.curriculoQualificacoes;
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            //$state.go(state)
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

            // console.log(situacao, vm.situacoes)
            return vm.situacoes[parseInt(situacao) - 1].nome
        }

//        function loadCurriculoFormacoes() {
//            CurriculoFormacao.curriculoformacao({pessoaId: vm.id}).$promise.then(function (r) {
//
//                vm.curriculoFormacoes = r;
//
//            })
//        }
//
//        function loadCurriculoQualificacoes() {
//            CurriculoQualificacao.curriculoqualificacao({pessoaId: vm.id}).$promise.then(function (r) {
//
//                vm.curriculoQualificacoes = r;
//
//            })
//
//        }
//
//        function loadCurriculoExperiencias() {
//            CurriculoExperiencia.curriculoexperiencia({pessoaId: vm.id}).$promise.then(function (r) {
//
//                vm.curriculoExperiencias = r;
//
//            })
//
//        }

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

                        if (!vm.entity.pessoa.curriculoFormacoes)
                            vm.entity.pessoa.curriculoFormacoes = []
                        vm.entity.pessoa.curriculoFormacoes.push(adicionado)
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

        function mostraAddQualificacao() {
            $mdDialog.show({
                controller: 'CurriculoQualificacaoEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-qualificacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosQualificacao: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.pessoa.curriculoQualificacoes)
                            vm.entity.pessoa.curriculoQualificacoes = []
                        vm.entity.pessoa.curriculoQualificacoes.push(adicionado)
                    });
        }


        function mostraEditQualificacao(qualificacao) {
            $mdDialog.show({
                controller: 'CurriculoQualificacaoEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-qualificacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosQualificacao: function () {
                        return angular.copy(qualificacao);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(qualificacao, alterado)
            })
        }

        function mostraAddExperiencia() {
            $mdDialog.show({
                controller: 'CurriculoExperienciaEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-experiencia.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosExperiencia: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.pessoa.curriculoExperiencias)
                            vm.entity.pessoa.curriculoExperiencias = []
                        vm.entity.pessoa.curriculoExperiencias.push(adicionado)
                    });
        }


        function mostraEditExperiencia(experiencia) {
            $mdDialog.show({
                controller: 'CurriculoExperienciaEditController as modalVm',
                templateUrl: 'app/recrutamento/curriculo-experiencia.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosExperiencia: function () {
                        return angular.copy(experiencia);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(experiencia, alterado)
            })
        }



        function callbackSave(r) {
            Workspace.showMessage("Registro Salvo!")
           // $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
        }

    }

})()