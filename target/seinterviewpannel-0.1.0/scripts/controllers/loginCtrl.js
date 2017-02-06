myApp.controller("loginCtrl", function ($scope, $location, $window, $rootScope, myService) {
   // $scope.firstName = "Test";
    $rootScope.home = myService.sharedObject.data;
    });