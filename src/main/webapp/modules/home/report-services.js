'use strict'
var proxy = window.location.pathname;
angular.module('HelloApp').factory('Reports',
	    ['$http','$timeout','$rootScope','$cookieStore','myService','localStorageService',
	    function ($http,$timeout,$rootScope,$cookieStore,myService,localStorageService){
	    	//$rootScope.home = myService.sharedObject.data;
	      //  $scope.competencyName11 = localStorageService.get('competencyName');
	        //$scope.user=localStorageService.get('username');
	        var service1 = {};
	        $rootScope.user=localStorageService.get('username');
	        service1.getData = function (id,sDate,eDate,callback){
	    
    		var ipaProxyConstant = window.location.protocol;
	        $http.post(ipaProxyConstant+proxy+'panel/getObjectFromTo?fromDate='+sDate+'&toDate='+eDate,{

	        	"login":
	        	{
	        	"contact":
	        	{
	        		"emailId":id
	        	}
	        	}
	        	})
	        .success(function (response) {
	        	callback(response);
	            })
	           
	           .error(function(data, status, headers) {
	               // alert("error");
	            });
	        };
	        return service1;
	    }])
