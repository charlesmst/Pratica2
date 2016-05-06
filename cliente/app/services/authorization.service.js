(function () {
    "use strict";
    angular.module("app").service("Authorization", ["store", "$http", "$q", "config", "AuthorizationData", Authorization])
    angular.module("app").service("AuthorizationData", ["store", AuthorizationData])

    function AuthorizationData(store) {
        var vm = this;
        vm.currentUser = null;

        vm.setCurrentUser = setCurrentUser;
        vm.getCurrentUser = getCurrentUser;

        function setCurrentUser(user) {
            vm.currentUser = user;
            store.set('user', user);

        }

        function getCurrentUser() {
            if (!vm.currentUser) {
                vm.currentUser = store.get('user');
            }
            return vm.currentUser;
        }
    }
    function Authorization(store, $http, $q, config, AuthorizationData) {
        var vm = this;

        vm.setCurrentUser = setCurrentUser;
        vm.getCurrentUser = getCurrentUser;
        vm.logout = logout;
        vm.getNivel = getNivel;
        vm.login = login;
        vm.loadNiveis = loadNiveis;
        vm.niveis = []
        vm.defaultState = "login";
        loadNiveis();

        function loadNiveis() {
            return $q(function (resolve, reject)
            {
                vm.getNivel().then(function (n) {
                    $http.get("data/niveis/" + n + ".json").then(function (e) {
                        vm.niveis = e.data.menus;
                        vm.defaultState = e.data.inicial;
                        resolve()
                    },reject);

                })
            });

        }
        function login(user, pass) {
//            console.log(arguments)
            return $q(function (resolve, reject) {
                $http.post(config.apiUrl + "/authorize", {
                    usuario: user,
                    senha: pass
                }).then(function (e) {

                    vm.setCurrentUser(e.data).then(resolve, reject);
                }, reject);
            });
        }
        function logout() {
            AuthorizationData.setCurrentUser(null);
            return loadNiveis();
        }
        function setCurrentUser(user) {
            return $q(function (resolve, reject) {
                AuthorizationData.setCurrentUser(user);
                vm.loadNiveis().then(resolve, reject);
            })
        }

        function getCurrentUser() {
            return AuthorizationData.getCurrentUser();
        }

        function getNivel() {
            return $q(function (resolve, reject) {
                if (!AuthorizationData.getCurrentUser())
                    resolve(0);
                else
                    $http.get(config.apiUrl + "/authorize").then(function (r) {
   
                        resolve(r.data.nivel)
                    }, reject);
            });
        }

    }

})()

