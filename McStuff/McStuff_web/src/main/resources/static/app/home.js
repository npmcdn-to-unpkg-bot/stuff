
(function() {

	'use strict';

	angular.module('home',[])
	
	.component('homeComponent', {
		templateUrl: '/app/home.html',
		controller: HomeComponent,
		bindings: { $router: '<' }
	});
		
	function HomeComponent($location, $scope, $element, $attrs) {
		var $comp = this;
		debugger;
	}

})();
