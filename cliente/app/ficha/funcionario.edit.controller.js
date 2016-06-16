(function () {
    'use strict';
    angular.module('app').controller('FuncionarioEditController', ['$mdToast', '$http', 'Pessoa', '$state', '$stateParams', 'Workspace', '$mdDialog', 'Cor', 'EstadoCivil', 'Escolaridade', 'Cidade', 'Banco', 'TipoSanguineo', 'VinculoEmpregaticio', 'NecessidadeEspecial', 'config', '$scope', FuncionarioEditController]);

    var state = "ficha"
    function FuncionarioEditController($mdToast, $http, Pessoa, $state, $stateParams, Workspace, $mdDialog, Cor, EstadoCivil, Escolaridade, Cidade, Banco, TipoSanguineo, VinculoEmpregaticio, NecessidadeEspecial, config, $scope) {
        var vm = this;
        vm.entity = {}
        vm.mostraAddCargo = mostraAddCargo;
        vm.mostraEditCargo = mostraEditCargo
        vm.mostraAddQualificacao = mostraAddQualificacao;
        vm.querySearchCidade = querySearchCidade
        vm.mostraEditQualificacao = mostraEditQualificacao
        vm.mostraEditFerias = mostraEditFerias
        vm.mostraAddFerias = mostraAddFerias
        vm.mostraEditFaixa = mostraEditFaixa
        vm.mostraAddFaixa = mostraAddFaixa
        vm.mostraEditDependente = mostraEditDependente
        vm.mostraAddDependente = mostraAddDependente
        vm.mostraEditFalta = mostraEditFalta
        vm.mostraAddFalta = mostraAddFalta
        vm.mostraEditAcidente = mostraEditAcidente
        vm.mostraAddAcidente = mostraAddAcidente
        vm.mostraEditAdvertencia = mostraEditAdvertencia
        vm.mostraAddAdvertencia = mostraAddAdvertencia
        vm.querySearchPessoa = querySearchPessoa
        vm.querySearchNecessidade = querySearchNecessidade;
        $scope.changePhoto = changePhoto
        vm.habilitaFuncionario = habilitaFuncionario
        vm.cor = []
        vm.escolaridade = []
        vm.funcionarioCache = {}
        loadSexos()
        loadEscolaridades()
        loadEstadosCivis()
        loadCategoriasCnh()
        loadBancos()
        loadTipoSanguineo()
        loadVinculos()
        loadCores();
        Workspace.title = "Ficha Funcional";
        console.log($stateParams)
        vm.imageChanged = false
        if ($stateParams.id) {
            Workspace.loading("Carregando...", Pessoa.get({id: $stateParams.id}).$promise.then(function (data) {

                vm.entity = data
                vm.funcionarioCache = vm.entity.funcionario
                if (vm.entity.hasOwnProperty("dataNascimento") && vm.entity.dataNascimento)
                    vm.entity.dataNascimento = Workspace.toDate(vm.entity.dataNascimento)
                vm.isFuncionario = typeof (vm.entity.funcionario) !== "undefined" && vm.entity.funcionario !== null;
                console.log("isFuncionario", vm.isFuncionario)
                if (vm.entity.funcionario && vm.entity.funcionario.funcionarioCargos.length > 0)
                    vm.funcionarioAtivo = vm.entity.funcionario.funcionarioCargos[0]

                vm.entity.necessidadeEspecials = vm.entity.necessidadeEspecials || []
                setImage()
            }))

        } else {
            vm.entity = new Pessoa()
            vm.entity.necessidadeEspecials = vm.entity.necessidadeEspecials || []
            setImage()

        }
        loadCor()
        vm.save = save;
        vm.cancel = cancel;
        function save($event, $valid) {
            if (!$valid)
                return;
            if (vm.imageChanged)
                vm.entity.imagem = vm.image
            Workspace.loading("Salvando...", vm.entity.$save(callbackSave, callbackError).$promise)
        }
        function cancel() {
            $state.go(state)
        }
        function callbackSave(r) {
            Workspace.showMessage("Registro Salvo!")
            $state.go(state)

        }
        function callbackError() {
            Workspace.showMessage("Ocorreu um erro ao salvar o registro!")
        }



        function mostraAddCargo() {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.entity.funcionario.funcionarioCargos)
                            vm.entity.funcionario.funcionarioCargos = []
                        vm.entity.funcionario.funcionarioCargos.push(adicionado)
                        if (!vm.funcionarioAtivo)
                            vm.funcionarioAtivo = adicionado;
                    });
        }

        function mostraEditCargo(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioCargoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-cargo.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosCargo: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }


        function querySearchCidade(texto) {
            return Cidade.query({
                filter: texto,
                limit: 10
            }).$promise
        }

        function mostraAddQualificacao() {
            $mdDialog.show({
                controller: 'FuncionarioQualificacaoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-qualificacao.edit.tmpl.html',
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

                        if (!vm.funcionarioAtivo.funcionarioQualificacaos || vm.funcionarioAtivo.funcionarioQualificacaos.length === 0)
                            vm.funcionarioAtivo.funcionarioQualificacaos = []
                        vm.funcionarioAtivo.funcionarioQualificacaos.push(adicionado)
                    });
        }

        function mostraEditQualificacao(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioQualificacaoEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-qualificacao.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosQualificacao: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }


        function mostraAddAcidente() {
            $mdDialog.show({
                controller: 'FuncionarioAcidenteEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-acidente.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosAcidente: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.funcionarioAtivo.funcionarioAcidentes)
                            vm.funcionarioAtivo.funcionarioAcidentes = []
                        vm.funcionarioAtivo.funcionarioAcidentes.push(adicionado)
                    });
        }

        function mostraEditAcidente(cargo) {
            $mdDialog.show({
                controller: 'FuncionarioAcidenteEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-acidente.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosAcidente: function () {
                        return angular.copy(cargo);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(cargo, alterado)
            })
        }
        function mostraAddFerias() {
            $mdDialog.show({
                controller: 'FuncionarioFeriasEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-ferias.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFerias: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.funcionarioAtivo.feriases)
                            vm.funcionarioAtivo.feriases = []
                        vm.funcionarioAtivo.feriases.push(adicionado)
                    });
        }

        function mostraEditFerias(ferias) {
            console.log("mostraEditFerias", ferias)
            $mdDialog.show({
                controller: 'FuncionarioFeriasEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-ferias.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFerias: function () {
                        return angular.copy(ferias);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(ferias, alterado)
            })
        }



        function mostraAddAdvertencia() {
            $mdDialog.show({
                controller: 'FuncionarioAdvertenciaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-advertencia.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosAdvertencia: function () {
                        return {};

                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.funcionarioAtivo.funcionarioCargoHasAdvertenciaTipos)
                            vm.funcionarioAtivo.funcionarioCargoHasAdvertenciaTipos = []
                        vm.funcionarioAtivo.funcionarioCargoHasAdvertenciaTipos.push(adicionado)
                    });
        }

        function mostraEditAdvertencia(Advertencia) {
            console.log("mostraEditAdvertencia", Advertencia)
            $mdDialog.show({
                controller: 'FuncionarioAdvertenciaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-advertencia.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosAdvertencia: function () {
                        return angular.copy(Advertencia);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(Advertencia, alterado)
            })
        }

        function mostraAddFaixa() {
            $mdDialog.show({
                controller: 'FuncionarioFaixaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-faixa.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFaixa: function () {
                        return {};
                    },
                    funcionarioAtivo: function () {
                        return vm.funcionarioAtivo
                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.funcionarioAtivo.funcionarioFaixas)
                            vm.funcionarioAtivo.funcionarioFaixas = []
                        vm.funcionarioAtivo.funcionarioFaixas.push(adicionado)
                    });
        }

        function mostraEditFaixa(Faixa) {
            console.log("mostraEditFaixa", Faixa)
            $mdDialog.show({
                controller: 'FuncionarioFaixaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-faixa.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFaixa: function () {
                        return angular.copy(Faixa);
                    },
                    funcionarioAtivo: function () {
                        return vm.funcionarioAtivo
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(Faixa, alterado)
            })
        }

        function mostraAddFalta() {
            $mdDialog.show({
                controller: 'FuncionarioFaltaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-falta.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFalta: function () {
                        return {};
                    }
                }

            })
                    .then(function (adicionado) {
                        console.log("Resposta da modal", adicionado)

                        if (!vm.funcionarioAtivo.funcionarioCargoHasMotivoFaltas)
                            vm.funcionarioAtivo.funcionarioCargoHasMotivoFaltas = []
                        vm.funcionarioAtivo.funcionarioCargoHasMotivoFaltas.push(adicionado)
                    });
        }

        function mostraEditFalta(Falta) {
            console.log("mostraEditFalta", Falta)
            $mdDialog.show({
                controller: 'FuncionarioFaltaEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-falta.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosFalta: function () {
                        return angular.copy(Falta);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(Falta, alterado)
            })
        }

        function mostraAddDependente() {
            $mdDialog.show({
                controller: 'FuncionarioDependenteEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-dependente.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosDependente: function () {
                        return {};
                    }
                }

            })
                    .then(function (adicionado) {

                        if (!vm.entity.funcionario.dependentes)
                            vm.entity.funcionario.dependentes = []
                        vm.entity.funcionario.dependentes.push(adicionado)
                    });
        }

        function mostraEditDependente(Dependente) {
            console.log("mostraEditDependente", Dependente)
            $mdDialog.show({
                controller: 'FuncionarioDependenteEditController as modalVm',
                templateUrl: 'app/ficha/funcionario-dependente.edit.tmpl.html',
                parent: angular.element(document.body),
                clickOutsideToClose: false,
                resolve: {
                    DadosDependente: function () {
                        return angular.copy(Dependente);
                    }
                }

            }).then(function (alterado) {
                console.log("Resposta da modal", alterado)
                angular.extend(Dependente, alterado)
            })
        }
        function loadCor() {
            Cor.query().$promise.then(function (resposta) {
                vm.cor = resposta;
            })

        }

        function loadVinculos() {
            VinculoEmpregaticio.query().$promise.then(function (resposta) {
                vm.vinculosEmpregaticios = resposta;
            })
        }

        function loadBancos() {
            Banco.query().$promise.then(function (resposta) {
                vm.bancos = resposta;
            })

        }
        function loadTipoSanguineo() {
            TipoSanguineo.query().$promise.then(function (resposta) {
                vm.tiposSanguineos = resposta;
            })

        }
        function loadSexos() {
            vm.sexos = [
                {
                    id: 0,
                    nome: "Feminino"
                }, {
                    id: 1,
                    nome: "Masculino"
                }
            ]
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

        function loadCores() {
            Cor.query().$promise.then(function (resposta) {
                vm.cores = resposta;
            })
        }
        function querySearchNecessidade(query) {
            return NecessidadeEspecial.query({
                filter: query
            }).$promise;
        }
        function querySearchPessoa(query) {
            return Pessoa.query({
                filter: query
            }).$promise;
        }
        function setImage() {
            vm.image = config.imageUrl + "/" + (vm.entity.imagem || "0.jpg")
        }
        function changePhoto() {
            $scope.$apply(function () {
                var reader = new FileReader();
                reader.onloadend = function () {
                    $scope.$apply(function () {
                        if (reader.result.startsWith("data:image")) {
                            vm.image = reader.result;
                            vm.imageChanged = true
                        }
                    })
                }
                var file = document.getElementById('inputDialog').files[0]
                if (file) {
                    reader.readAsDataURL(file);
                }
            })
        }

        function habilitaFuncionario() {
            if (vm.isFuncionario) {
                vm.entity.funcionario = vm.funcionarioCache || {}
            } else {
                vm.funcionarioCache = vm.entity.funcionario
                vm.entity.funcionario = undefined
            }
        }
    }


})()