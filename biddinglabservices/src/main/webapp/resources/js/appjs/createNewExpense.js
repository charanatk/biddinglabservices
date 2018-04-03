roomExpApp.controller("createNewExpenseController", function($scope, $http) {

	

	$scope.exp = {
		username : "",
		amount : "",
		expinfo : ""
	};

	$scope.addNewExpense = function() {
		$http({
			method : 'PUT',
			url : '/addNewExpense',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.exp)
		}).then(_success, _error);
	};

	loadExpTemplate();

	function loadExpTemplate() {
		$http({
			method : 'GET',
			url : '/loadExpTemplate'
		}).then(function successCallback(response) {
			$scope.exp = response.data;
		}, function errorCallback(response) {
		});
	}

	function _success(response) {
		reset();
		console.log(response.statusText);
	}

	function _error(response) {
		reset();
		console.log(response.statusText);
	}

	function reset() {
		$scope.exp.amount = "";
		$scope.exp.expinfo = "";
	}

});
