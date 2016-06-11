(function () {
    angular.module("app").controller("LoginController", ["Workspace", "Authorization", "$state", LoginController])

    function LoginController(Workspace, Authorization, $state) {
        var vm = this;
        Workspace.title = "Login";
        vm.submit = submit;
        vm.registrar = registrar;
        vm.entity = {}
        function submit(e, valid) {
            if (!valid)
                return;
            Workspace.loading("Autenticando...", Authorization.login(vm.entity.usuario, vm.entity.senha).then(function (data) {
                Workspace.showMessage("Usuário autenticou com sucesso!")
                $state.go(Authorization.defaultState)
            }, function () {
                Workspace.showMessage("Usuário ou senha incorreto!")
                vm.entity.senha = "";
            }))
        }
        function registrar(){
            $state.go("usuarioadd");
        }
    }
})()

