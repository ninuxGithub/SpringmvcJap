var app = angular.module('routeApp', [ 'ngRoute' ]);

app.config(function($routeProvider, $locationProvider) {
	$routeProvider.when('/a', {
		templateUrl : "a.html",
		controller : "actrl"
	}).when('/b', {
		templateUrl : "b.html",
		controller : "bctrl",
		resolve : {
			delay : function($q, $timeout) {
				var delay = $q.defer();
				$timeout(delay.resolve, 3000);
				return delay.promise;
			}
		}
	}).otherwise({
		redirectTo : '/a'
	});
});

app.controller('actrl', function($scope, $route) {
	console.dir('enter aaaa');
	$scope.hello = "hello a !";
});

app.controller('bctrl', function($scope, $route) {
	console.dir('enter bbb');
	$scope.hello = "hello b !";
});

app.controller("myctrl", function($scope, $location) {

	$scope.$on("$viewContentLoaded", function() {
		console.log("ng-view content loaded!");
	});

	$scope.$on("$routeChangeStart", function(event, next, current) {
		console.log("route change start!");
	});
})
