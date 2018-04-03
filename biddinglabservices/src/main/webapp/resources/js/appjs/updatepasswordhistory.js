roomExpApp.controller('passwordHistoryController', function($scope, $http) {

	
	$http.get('/updatePasswordHistory').success(function(res) {
		$scope.myData = res;
		console.log($scope.myData);
	})

	$scope.gridOptions = {
		data : 'myData',
		enableRowSelection : true,
		enableCellEditOnFocus : false,
		enableRowSelection : true,
		enablePaging : true,
		multiSelect : true,
		columnDefs : [ {
			field : 'username',
			displayName : 'Updated User Id'
		}, {
			field : 'updateTime',
			displayName : 'TimeStamp'
		} ]
	};
	
	
	
	
});
