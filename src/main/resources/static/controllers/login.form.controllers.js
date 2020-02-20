angular.module('frontend')
.controller('LoginFormController', 
function LoginFormController(
		$rootScope, $scope, $localStorage,
		$uibModalInstance, 
		coreService,$log) {
	$scope.title="Ingreso";
	
	$scope.user={name:"",password:""};
	
	
	$scope.login = function () {
		coreService.login($scope.user).then(
			function (resp) {
				if (resp.status === 200) {
					$localStorage.userdata = resp.data;
					$rootScope.loginData = $localStorage.userdata;
					$localStorage.logged = true;
					$rootScope.loginOpen = false;
					userService.cargarPorUsername($rootScope.loginData.username).then(
						function (resp) {
							$window.location.reload();
							$rootScope.currentLoggedUser = resp.data[0];
						},
						function (err) {
							$rootScope.openErrorModal(err);
						}
					);
					$uibModalInstance.dismiss(true);
				} else {
					delete $localStorage.userdata;
					$localStorage.logged = false;
				}
			},
			function (respErr) {
				$log.log(respErr);
			}
		);
	};  
}); 



























