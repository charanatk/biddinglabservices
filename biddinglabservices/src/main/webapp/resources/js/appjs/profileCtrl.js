roomExpApp.controller("profileCtrl", function($scope, $http) {
	
	var username = document.getElementById('loginUser').value;

	$scope.updateUserInfoForm = undefined;
	$scope.profile = {
		id : "-1",
		username : username,
		emailId : "",
		mobNum : "",
		address : ""
	}

	$scope.updateUserInfoForm = function() {
		console.log(username);
		$http({
			method : 'PUT',
			url : '/updateProfile',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.profile)
		}).then(_success, _error);
	};

	loadProfile();
	
	function loadProfile() {
		console.log(username);
		$http({
			method 	: 'POST',
			url 	: '/getProfile',
			headers : {
						'Content-Type' : 'application/json'
					  },
			data 	: angular.toJson($scope.profile)
		}).then(function successCallback(response) {
			$scope.profile = response.data;
		}, function errorCallback(response) {
		});
	}
	

	function _success(response) {
		console.log(response.statusText);
		alert("success : " + response.statusText);
	}

	function _error(response) {
		console.log(response.statusText);
		alert("error : " + response.statusText);
	}

});
