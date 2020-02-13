angular.module('frontrello').config( 
	function($routeProvider,$locationProvider, $httpProvider, $localStorageProvider){
		
		$localStorageProvider.setKeyPrefix('frontrello/');
		$httpProvider.defaults.withCredentials = true;
		$httpProvider.interceptors.push('APIInterceptor');
		
		$routeProvider
		
		.when('/main',{
			templateUrl:'views/main.html',
			controller: 'MainController'
		})
		
		.when('/tablero',{
			templateUrl:'views/tablero.html',
			controller: 'TableroController'
		})
		.otherwise({
			redirectTo : '/main'
		})
		
	}  
);