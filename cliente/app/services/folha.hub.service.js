(function () {
    'use strict';

    angular
        .module('app')
        .service('FolhaHub', FolhaHub);

    FolhaHub.$inject = ['config', 'AuthorizationData', "$rootScope"];
    function FolhaHub(config, AuthorizationData, $rootScope) {
        var vm = this;
        vm.sendMessage = sendMessage;
        vm.connect = connect;
        vm.disconnect = disconnect;
        vm.items = []
        vm.itemsObject = {}
        ////////////////
        function connect() {
            disconnect()
            vm.ws = new WebSocket(config.hubUrl + "/folha?Authorization=asdasdas" + encodeURIComponent(AuthorizationData.getCurrentUser().token))
            console.log(vm.ws)
            vm.ws.onopen = onOpen;
            vm.ws.onclose = onClose;
            vm.ws.onerror = onError;
            vm.ws.onmessage = onMessageWrapper;
        }
        function onMessageWrapper(evt) {

            console.log("Mensagem recebida", evt.data)
            $rootScope.$apply(function () {
                onMessage(evt)
            })
        }
        function onMessage(evt) {
            var data = JSON.parse(evt.data)

            switch (data.action) {
                case "receiveAll":
                    vm.itemsObject = data.data;
                    vm.items = []
                    for (var a in vm.itemsObject) {
                        var value = vm.itemsObject[a]
                        vm.items.push(value)

                    }
                    break;
                case "add":
                    vm.itemsObject[data.data.id] = data.data;
                    vm.items.push(data.data)

                    break;
                case "progress":
                case "statusText":
                    angular.extend(vm.itemsObject[data.data.id], data.data)
                    break;
            }
            console.log(vm.items)
        }
        function onOpen() {
            console.log("Conexão estabelecida")
            sendMessage("receiveAll");

        }

        function onClose() {
            console.log("Conexão fechada")
        }

        function onError() {
            console.log("Erro na conexão", arguments)
        }
        function disconnect() {
            if (vm.ws)
                vm.ws.close()
        }

        function sendMessage(action, data) {
            if (vm.ws.readyState === vm.ws.OPEN) {
                console.log("Mandando mensagem", action, data);
                vm.ws.send(JSON.stringify({
                    "action": action,
                    "data": data
                }))
            } else {
                console.warn("Send message sendo chamado antes da conexão estar aberta")
            }
        }
    }
})();