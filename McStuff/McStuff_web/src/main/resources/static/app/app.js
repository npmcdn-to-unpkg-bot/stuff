
function AppComponent($rootRouter) {
	$rootRouter.navigate(['Home']);
}

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


