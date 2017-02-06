/*myApp.controller("aboutCtrl", function ($scope, $rootScope, $http) {
   
   $rootScope.home = "";

    var onCompleate = function(response){
    	alert("response data:"+response.data);
        $scope.names = response.data;
        alert("scope data:"+$scope.names);
    };

    var onError =function(response){
        $scope.error = 'No data found';
    };

    $http.get("http://localhost:9080/login/id/geet123")
     .then(onCompleate, onError);
    var loginService = angular.module('loginService', [])
    loginService.factory('login', ['$http', function ($http) {

        var urlBase = '';
        var login = {};

        login.getStudents = function () {
            return $http.get(urlBase+'/id/geet123');
        };

        StudentDataOp.addStudent = function (stud) {
            return $http.post(urlBase + '/AddStudent', stud);
        };
        return login;

    }]);

});
*/