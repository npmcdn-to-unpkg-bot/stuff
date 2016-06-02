
(function() {

	'use strict';

	angular.module('app', [
		'ngComponentRouter',
		'home'
	])
	
	.config(function($locationProvider) {
		$locationProvider.html5Mode(true);
	})
	
	.value('$routerRootComponent', 'app')
	
	.component('app', {
		templateUrl: '/app/app.html',
		controller: AppComponent,
		$routeConfig: [
			{path: '/home', name: 'Home', component: 'homeComponent'}
		]
	});
	
	function AppComponent($rootRouter) {
		var $comp = this;
	
		$rootRouter.navigate(['Home']);
	}

})();

