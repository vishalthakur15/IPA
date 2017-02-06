'use strict'
var proxy = window.location.pathname;
angular.module('Home').factory('ProposeEvent',
	    ['$http','$timeout','$rootScope','$cookieStore',
	     function ($http,$timeout,$rootScope,$cookieStore) {
	        var service = {};
	        service.Propose =function(date,duration,competency,Description,user,callback){
	        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
	        	$http.post(ipaProxyConstant+proxy+'admin/user',{
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
	        	/*$http.post('http://172.16.111.54:9080/admin/user',{proposeDateTime:date,duration:duration,description:Description}+'?userName='+user+'&competencyName='+competency)*/
	        	.success(function (response) {
            	callback(response);
            })
	        	.error(function(data, status, headers) {
	               alert("Wrong ajax call");
	               // return message; //pass error message back to $scope
	            });
	        };
	      
	        return service;
	       
	    }])
	     angular.module('Home').factory('GetUser',
    ['$http', '$timeout','$rootScope','$cookieStore',
    function ($http,$timeout,$rootScope,$cookieStore) {
        var service1 = {};

        service1.get = function (callback) {
        	var ipaProxyConstant = window.location.protocol/* + "//" + window.location.host*/;
            $http.post(ipaProxyConstant+proxy+'admin/get')
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

        service1.Add = function (role,firstname,lastname,emailaddress,contactnumber,competency,levelname, callback) {
        	
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
            $http.post(ipaProxyConstant+proxy+'admin/add',{
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
            	},
            	"role":{
            		"roleName":role
            	}
            })
            .success(function (response) {
            	callback(response);
            	/*$http.get("http://172.16.111.54:9081/admin/list");*/
            //	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
            //   $http.get(ipaProxyConstant+var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
              //  $http.get(ipaProxyConstant+proxy+'admin/list') .success(function (response) {
            //	$rootScope.data1 = data;
              //  })
                
                //$http.get("http://172.16.111.54:9081/admin/list");
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
        	//alert("in delete")
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
        	//alert(userName)
        	$http.post(ipaProxyConstant+proxy+'admin/delete',{"userNames":userName}).success(function (response) {
        		        		if(response.deleted=="deleted")
        		{
        		// alert("User Deleted1");
        		}
        				          callback(response);
            	
               
            })
           
           .error(function(data, status, headers) {
                alert("error");
            });
        };
        return service1;
    }])
	     angular.module('Home').factory('DeleteEvent',
	    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    		        var service1 = {};

	    		        service1.deleteEvent = function (availability,callback){
	    		        	var ipaProxyConstant = window.location.protocol/* + "//" + window.location.host*/;
	    		        	$http.post(ipaProxyConstant+proxy+'panel/deleteEvent',{"userNames":userName}).success(function (response) {
	    		        		        		if(response.deleted=="deleted")
	    		        		{
	    		        		// alert("User Deleted");
	    		        		}
	    		        				          callback(response);
	    		            	
	    		               
	    		            })
	    		           
	    		           .error(function(data, status, headers) {
	    		                alert("error");
	    		            });
	    		        };
	    		        return service1;
	    		    }])
	     //-------------------------------------------------
	     angular.module('Home').factory('AddCompetency',
	    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    		        var service1 = {};
	    		      
	    		        service1.Add = function (selectcompetency,callback) {
	    		        	 //alert("add");
	    		        	//alert(selectcompetency);
	    		        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
								    		            $http
										.post(
												ipaProxyConstant+proxy+'competency/insert',{
	    		            	competencyName : selectcompetency
	    		            })
	    		            .success(function (response) {
	    		            	 callback(response);
	    		            	 $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/competency/get").success(function (data, status) { 
		            	   //	alert("call back add");
	    		            	       $rootScope.data1 = data;
	    		            	   });
	    		            })

	    		            .error(function(data, status, headers) {
	    		                var message = 'Please enter the correct credentials! :(';
	    		                return message; //pass error message back to $scope
	    		            });
	    		        };
	    		        return service1;
	    		    }])
	     angular.module('Home').factory('DeleteCompetency',
	    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    		        var service1 = {};
	    		      
	    		        service1.removeCompetency = function (competencyName,callback){
	    	    		        	var ipaProxyConstant = window.location.protocol ;
	    		        $http.post(ipaProxyConstant+proxy+'competency/remove',{"competencyNames":competencyName})
	    		        .success(function (response) {
	    		        	if(response[0].deleted=="deleted")
	    		        		{
	    		        		 alert("Competency Deleted");
	    		        		}
	    		                callback(response);
	    		               // alert("call back remove");
	    		                var ipaProxyConstant = window.location.protocol;
	    		                $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/competency/get").success(function (data, status) { 
//	    		                	alert(status);
	    		                    $rootScope.data1 = data;
	    		                });

	    		               
	    		            })
	    		           
	    		           .error(function(data, status, headers) {
	    		               // alert("error");
	    		            });
	    		        };
	    		        return service1;
	    		    }])
	     angular.module('Home').factory('AddAvailList',
	    		    ['$http', '$timeout','$rootScope','$cookieStore',
	    		    function ($http,$timeout,$rootScope,$cookieStore) {
	    		        var service1 = {};
	    		      
	    		        service1.add1 = function (availability,callback){
	    		    
	    	    		var ipaProxyConstant = window.location.protocol ;
	    		        $http.post(ipaProxyConstant+proxy+'panel/insertAvailability',{"eventNames":availability})
	    		        .success(function (response) {
	    		        	callback(response);
	    		            })
	    		           
	    		           .error(function(data, status, headers) {
	    		               // alert("error");
	    		            });
	    		        };
	    		        return service1;
	    		    }])
	    		    angular.module('Home').factory('AvailList',
	    		    ['$http', '$timeout','$rootScope','$cookieStore','$q',
	    		    function ($http,$timeout,$rootScope,$cookieStore,$q) {
	    		        var service1 = {};
	    		        service1.getList = function (userIdentity,date,callback){
	    		        	var deferred = $q.defer();
	    		        	var ipaProxyConstant = window.location.protocol ;
	    		        	 $http.post(ipaProxyConstant+proxy+'panel/getObject',{
	 	    		        	"login":{"userId":userIdentity,},
		    		        	"availability_date":date})   		        	
	    		        	       .success(function(data) { 	    		        	
	    		        	          deferred.resolve(data);	    		        	
	    		        	       }).error(function(msg, code) {	    		        	
	    		        	          deferred.reject(msg);
	    		        	          });
	    		        	
	    		        	     return deferred.promise;
	    		        }
	    		        	
	    	    		/*
						 * var ipaProxyConstant = window.location.protocol +
						 * "//" + window.location.host;
						 * $http.post(ipaProxyConstant+'/panel/getObject',{
						 * "login":{ "userId":userIdentity, },
						 * "availability_date":date })
						 * 
						 * .success(function (response) { callback(response); })
						 * 
						 * .error(function(data, status, headers) {
						 * alert("error"); }); };
						 
	    		        return service1;
	    		        */
	    		        return service1;
	    		    }]);
angular.module('Home').factory('CancelInterview',
	    ['$http', '$timeout','$rootScope','$cookieStore',
	    function ($http,$timeout,$rootScope,$cookieStore) {
	        var service = {};

	        service.cancelInterview = function (availabilityIds,callback){
	        	var ipaProxyConstant = window.location.protocol ;
	        	$http.post(ipaProxyConstant+proxy+'panel/cancelInterview',{"availabilityIds":availabilityIds}).success(function (response) {
	        		        		if(response.deleted=="deleted")
	        		{
	        		// alert("Deleted");
	        		}
	        				          callback(response);
	        				        
	            	
	               
	            })
	           
	           .error(function(data, status, headers) {
	                alert("error");
	            });
	        };
	        return service;
	    }])
	    
	    angular.module('Home').factory('CancelInterviewMail',
	    ['$http', '$timeout','$rootScope','$cookieStore',
	    function ($http,$timeout,$rootScope,$cookieStore) {
	        var service = {};

	        service.cancelInterviewMail = function (username,availabilityIds,callback){
	        	var ipaProxyConstant = window.location.protocol ;
	        	$http.post(ipaProxyConstant+proxy+'login/cancelInterviewMail',{"userName":username,"availabilityIds":availabilityIds}).success(function (response) {
	        		 
	                        callback(response);
	                               	 
	            })
	           
	           .error(function(data, status, headers) {
	              //  alert("error");
	            });
	        };
	        return service;
	    }])
	     angular.module('Home').factory('Lead',
   ['$http', '$timeout','$rootScope','$cookieStore',
   function ($http,$timeout,$rootScope,$cookieStore) {
       var service = {};

       service.getPanel = function (competency,callback){
        var ipaProxyConstant = window.location.protocol;
        $http.post(ipaProxyConstant+proxy+'login/LeadPanelData',{"competencyName":competency
        })
        .success(function (response) {
                       callback(response);
                      /* var ipaProxyConstant = window.location.protocol;
                       $http.post(ipaProxyConstant+proxy+'login/LeadPanelData',{"competencyName":competency*/
                       
           })
     
       };
       return service;
   }])
