app=angular.module('frontrello');

app.service('tareasService',
function($http, URL_API_BASE){
	
	var servicio={
		datos:[],
		listar:function(){ 
				$http.get(URL_API_BASE+'tareas').then(
					function(resp){
						this.datos=resp.data;
					},
					function(err){}
			);
		},
		
		agregar:function(tarea) {
			return $http.post(URL_API_BASE+'tareas',tarea);
		},
		eliminar:function(tarea){
			return $http.delete(URL_API_BASE+'tareas/'+tarea.id);
		},
		refresh:function(tarea){
			return $http.put(URL_API_BASE+'tareas',tarea);
		}
	};
	return servicio;
	
}
);