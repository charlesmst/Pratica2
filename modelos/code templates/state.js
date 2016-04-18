 .state('${state}', {
	url: '/${state}',
	views: {
		"": {
			templateUrl: 'app/${subfolder}/${state}.tmpl.html',
			controller: "${controller}Controller",
			controllerAs: "crudVm",
		}
	}
})
.state('${state}add', {
	url: '/${state}/add',
	views: {
		"": {
			templateUrl: 'app/${subfolder}/${state}.edit.tmpl.html',
			controller: "${controller}EditController",
			controllerAs: "crudVm",
		}
	}
})
.state('${state}edit', {
	url: '/${state}/edit/:id',
	views: {
		"": {
			templateUrl: 'app/${subfolder}/${state}.edit.tmpl.html',
			controller: "${controller}EditController",
			controllerAs: "crudVm",
		}
	}
})