(function(){
    angular.module("app").controller("TopController",["SharedData",TopController]);
    
    function TopController(SharedData){
        var vm = this;
        vm.sharedData = SharedData;
    }
})()

