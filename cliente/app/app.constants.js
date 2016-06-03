(function () {
    angular.module('app').constant("config", {
        appName: "App",
        appVersion: 2.0,
            apiUrl: "http://localhost:8084/rh/api",
            hubUrl: "ws://localhost:8084/rh/hub"
            
    });
})();

