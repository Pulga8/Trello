app=angular.module('frontrello');

app.service('listasService',
function($http, URL_API_BASE){
	
	var servicio={
		datos:[],
		listar:function(){
			$http.get(URL_API_BASE+'tableros/listas').then(
					function(resp){
						this.datos=resp.data;
					},
					function(err){}
			);
		},
		
		agregar:function(lista) {
			return $http.post(URL_API_BASE+'tableros/listas',lista);
		},
		eliminar:function(lista){
			return $http.delete(URL_API_BASE+'tableros/listas/'+lista.id);
		},
		refresh:function(lista){
			return $http.put(URL_API_BASE+'tableros/listas',lista);
		}
	};
	return servicio;
	
}
);