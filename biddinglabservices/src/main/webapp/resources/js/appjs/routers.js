roomExpApp.config([ '$routeProvider', function($routeProvider) {
	$routeProvider.when('/overview', {
		templateUrl : '/templates/overview.html'
	}).when('/settings', {
		templateUrl : '/templates/settings.html'
	}).when('/viewHistory', {
		templateUrl : '/templates/viewHistory.html'
	}).when('/profile', {
		templateUrl : '/templates/profile.html'
	}).when('/createExpense', {
		templateUrl : '/templates/createExpense.html'
	}).when('/viewUserProfile', {
		templateUrl : '/templates/viewUserProfile.html'
	}).when('/menu', {
		templateUrl : '/templates/menu.html'
	}).when('/addItem', {
		templateUrl : '/templates/addItem.html'
	}).when('/edit', {
		templateUrl : '/templates/editItem.html'
	}).when('/delete', {
		templateUrl : '/templates/delete.html'
	}).when('/release', {
		templateUrl : '/templates/release.html'
	}).otherwise({
		redirectTo : '/overview'
	});
} ]);
