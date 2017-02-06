
'use strict'
angular.module('Home').factory('ProposeEvent',
	    ['$http','$timeout','$rootScope','$cookieStore',
	     function ($http,$timeout,$rootScope,$cookieStore) {
	        var service = {};
	        service.Propose =function(date,duration,competency,Description,user,callback){
	        	$http.post('http://172.16.111.68:9082/admin/user',{
	        		"proposeDateTime":date,
	        		"duration":duration,
	        		"description":Description,
	        		"login":{
	        			"userName":user
	        		
	        				},
	        		"competency":{
	        			"competencyName":competency
	        				}
	        	})
	        	/*$http.post('http://172.16.111.54:9082/admin/user',{proposeDateTime:date,duration:duration,description:Description}+'?userName='+user+'&competencyName='+competency)*/
	        	.success(function (response) {
            	callback(response);
            })
	        	.error(function(data, status, headers) {
	               alert("Wrong ajax call");
	               // return message; //pass error message back to $scope
	            });
	        };
	        /*service.ClearCredentials = function () {
	        	alert("In clear");
	            $rootScope.globals = {};
	            $cookieStore.remove('globals');
	            $http.defaults.headers.common.Authorization = 'Basic ';
	        };*/
	        return service;
	        	
	          /* Use this for real authentication
	             ----------------------------------------------//
	            $http.post('http://172.16.111.54:9082/login/get', { userName: username, encryptedKey: password })
	                .success(function (response) {
	                	
	                    callback(response);
	                })
	                .error(function(data, status, headers) {
	                    var message = 'Please enter the correct credentials! :(';
	                    return message; //pass error message back to $scope
	                });

	        }*/
	    }]);
	     angular.module('Home').factory('GetUser',
    ['$http', '$timeout','$rootScope','$cookieStore',
    function ($http,$timeout,$rootScope,$cookieStore) {
        var service1 = {};

        service1.get = function (callback) {

            $http.post('http://172.16.111.68:9082/admin/get')
            .success(function (response) {
            	//alert(response);
                callback(response);
            })
           
           .error(function(data, status, headers) {
                var message = 'Please enter the correct credentials! :(';
                return message; //pass error message back to $scope
            });
        };
        return service1;
    }])
	     angular.module('Home').factory('AddUser',
    ['$http', '$timeout','$rootScope','$cookieStore',
    function ($http,$timeout,$rootScope,$cookieStore) {
        var service1 = {};

        service1.Add = function (firstname,lastname,emailaddress,contactnumber,competency,levelname, callback) {
        	/*alert(firstname);*/
            $http.post('http://172.16.111.68:9082/admin/add',{
            	"firstName" : firstname,
            	"lastName" : lastname,
            	"contact":{
            		"emailId" : emailaddress,
            		"contactNo" : contactnumber
            		
            	},
            	"levels":{
            		"competency":{
            			"competencyName" : competency
            			},
            		"levelName" : levelname
            	}
            })
            .success(function (response) {
            	/*alert("In servie");*/
                callback(response);
            })
           
           .error(function(data, status, headers) {
                /*alert("error");*/
            });
        };
        return service1;
    }])
    angular.module('Home').factory('DeleteUser',
    ['$http', '$timeout','$rootScope','$cookieStore',
    function ($http,$timeout,$rootScope,$cookieStore) {
        var service1 = {};

        service1.deleteUser = function (userName,callback){
        	$http.post('http://172.16.111.68:9082/admin/delete',{"userNames":userName}).success(function (response) {
            	 callback(response);
               
            })
           
           .error(function(data, status, headers) {
                alert("error");
            });
        };
        return service1;
    }]);
	     /*angular.module('Home').factory('AddCompetency',
	    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    		        var service1 = {};

	    		        service1.Add = function (selectcompetency,callback) {
	    		        	//alert(selectcompetency);
	    		            $http.post('http://172.16.111.68:9082/competency/insert',{
	    		            	competencyName : selectcompetency
	    		            })
	    		            .success(function (response) {
	    		            	 callback(response);
	    		            })

	    		            .error(function(data, status, headers) {
	    		                var message = 'Please enter the correct credentials! :(';
	    		                return message; //pass error message back to $scope
	    		            });
	    		        };
	    		        return service1;
	    		    }]);
	    			     angular.module('Home').factory('DeleteCompetency',
	    			    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    			    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    			    		        var service1 = {};

	    			    		        service1.removeCompetency = function (competencyName,callback){
	    			    		        //alert("delete:"+competencyName);
	    			    		        //var jsonData=JSON.parse(JSON.stringify(arr));
	    			    		        $http.post('http://172.16.111.68:9082/competency/remove',{"competencyNames":competencyName})
	    			    		        .success(function (response) {
	    			    		        	if(response=="success")
	    			    		        		{
	    			    		        		 alert("Deleted");
	    			    		        		}
	    			    		                callback(response);
	    			    		               
	    			    		            })
	    			    		           
	    			    		           .error(function(data, status, headers) {
	    			    		                alert("error");
	    			    		            });
	    			    		        };
	    			    		        return service1;
	    			    		    }]);*/