roomExpApp.controller("settingCtrl", function($scope, $http) {

	$scope.usersecureinfo = {
		username : "",
		password : "",
		passwordConfirm : ""
	};

	$scope.updatePassword = function() {
		$http({
			method : 'PUT',
			url : '/updatePassword',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.usersecureinfo)
		}).then(_success, _error);
	};

	function loadSettings() {
		$http({
			method : 'GET',
			url : '/getSecureInfo'
		}).then(function successCallback(response) {
			$scope.usersecureinfo = response.data;
		}, function errorCallback(response) {
		});
	}
	;

	loadSettings();

	function _success(response) {
		reset();
		console.log(response.statusText);
		alert("success : " + response.statusText);
	}
	;

	function _error(response) {
		console.log(response.statusText);
		alert("error : " + response.statusText);
	}
	;
	
	function reset() {
		$scope.usersecureinfo.password = "";
		$scope.usersecureinfo.passwordConfirm = "";
	}


});
