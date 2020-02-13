app=angular.module('frontrello');
app.controller('TareasController',function($scope,tareasService,$log,wsService){
	
	tareasService.listar();
	
//	 wsService.initStompClient('/backend/numeros', function(payload, headers, res) {
//         $log.log(payload)
//         //$scope.$apply();
//       });
//	
	
});