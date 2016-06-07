(function () {
    angular.module('app').constant("config", {
        appName: "App",
        appVersion: 2.0,
            imageUrl:"http://localhost:8384/rh/images", 
            apiUrl: "http://localhost:8384/rh/api",
            hubUrl: "ws://localhost:8384/rh/hub"
            
    });
})();

