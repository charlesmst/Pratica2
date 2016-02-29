(function () {
    angular.module("app.services").factory("Entry", ["$resource", Entry]);

    function Entry($resource) {
        return $resource('/api/:id', {id: '@id'}, {
            update: {
                method: 'PUT'
            }
        });
    }
})()