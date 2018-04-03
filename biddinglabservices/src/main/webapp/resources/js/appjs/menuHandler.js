roomExpApp.controller("menuItemsController", function($scope, $http) {

	var username = document.getElementById('loginUser').value;
	$scope.user = username;

	$scope.items = [];

	$scope.item = {
		itemId : "-1",
		itemDescription : "",
		itemName : "",
		itemPrice : "",
		active : "",
		available : "",
		username : username
	};

	loadingTheMenuItems();

	function loadingTheMenuItems() {
		$http({
			method : 'GET',
			url : '/menu/items/getMenuItems'
		}).then(function successCallback(response) {
			$scope.items = response.data;
		}, function errorCallback(response) {
			console.log(response.statusText);
		});
	}

	$scope.addItem = function() {
		$http({
			method : 'POST',
			url : '/menu/items/saveMenu',
			headers : {
				'Content-Type' : 'application/json'
			},
			data : angular.toJson($scope.item)
		}).then().success(function(data){
			//$scope.items = data;
			loadingTheMenuItems();
			reset();
		});
	};

	$scope.editItem = function(item) {
				$scope.item.itemId = item.itemId;
				$scope.item.itemDescription = item.itemDescription;
				$scope.item.itemName = item.itemName;
				$scope.item.itemPrice = item.itemPrice;
				$scope.item.active = item.active;
				$scope.item.available = item.available;
	}
	
	$scope.deleteItem = function(item) {
		   $http({
		     method : 'DELETE',
		     url : '/menu/items/deleteMenu',
		     data : angular.toJson(item),
		     headers : {
		     'Content-Type' : 'application/json'
		   }
		   }).then().success(function(data){
			  loadingTheMenuItems();  reset();
		   });
		  }

	function reset() {
		$scope.item.itemId = null;
	    $scope.item.itemDescription = "";
		$scope.item.itemName = ""; 
		$scope.item.itemPrice = "";
		$scope.item.active = ""; 
		$scope.item.available = "";
	}

	
	function disableId(){
		document.getElementById("itemId").disabled = true;
	}

});