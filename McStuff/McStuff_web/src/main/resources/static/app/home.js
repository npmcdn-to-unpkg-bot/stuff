
function HomeComponent($scope, $element, $attrs) {
	var $comp = this;
	
}

angular.module('home',[])

.component('homeComponent', {
  templateUrl: '/app/home.html',
  controller: HomeComponent,
  bindings: { $router: '<' }
})