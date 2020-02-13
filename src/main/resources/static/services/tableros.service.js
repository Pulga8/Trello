app=angular.module('frontrello');

app.service('tablerosService',
function($http, URL_API_BASE){
	
	var servicio={
		datos:[],
		listar:function(){
			$http.get(URL_API_BASE+'tableros').then(
					function(resp){
						this.datos=resp.data;
					},
					function(err){}
			);
		},
		
		agregar:function(tablero) {
			return $http.post(URL_API_BASE+'tableros',tablero);
		},
		eliminar:function(tablero){
			return $http.delete(URL_API_BASE+'tableros/'+tablero.id);
		},
		refresh:function(tablero){
			return $http.put(URL_API_BASE+'tableros',tablero);
		}
	};
	return servicio;
	
}
);