app=angular.module('frontend');
app.controller('TareasController',function($scope,tareasService,$log,wsService){
	
	tareasService.listar();
	
//	 wsService.initStompClient('/backend/numeros', function(payload, headers, res) {
//         $log.log(payload)
//         //$scope.$apply();
//       });
//	
	
});