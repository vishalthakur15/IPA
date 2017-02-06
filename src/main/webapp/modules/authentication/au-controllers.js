'use strict';
var proxy = window.location.pathname;
angular.module('Authentication')
.controller('LoginController',
    ['$scope', '$rootScope','$http', '$location','AuthenticationService', 'ListOfDate','ProposedEve','listEvents','localStorageService',
    function ($scope,$http, $rootScope,$location, AuthenticationService,ListOfDate,ProposedEve,listEvents,localStorageService) {
            AuthenticationService.ClearCredentials();
           // $rootScope.home = myService.sharedObject.data;
            //alert($scope.username);
            $scope.login = function (){
            	$scope.dataLoading = true;
            	AuthenticationService.Login($scope.username, $scope.password, function(data) {
            		//alert(data.error);
            		if(data===404){
            			$scope.errorFlag = true;
                  		$scope.dataLoading = false;
                  		return;
            		}
            		else{
            			
            		$rootScope.userObject = data;
            		window.localStorage.setItem('token',$rootScope.userObject.token);
            		var roleName = localStorage.getItem('token');
            		$rootScope.role = roleName;
            		var PostDataResponse1=$rootScope.role;
            		localStorageService.set('role',PostDataResponse1);
            		
            		
            		window.localStorage.setItem('username',$scope.username);
            		$rootScope.username=$scope.username;
            		var PostDataResponse = $rootScope.username; 
        	    	localStorageService.set('username', PostDataResponse);
            		
            		window.localStorage.setItem('competency',$rootScope.userObject.levels.competency.competencyName);
            		var comp = localStorage.getItem('competency');
            		$rootScope.compy = comp;
            		var PostDataResponse2=$rootScope.compy;
            		localStorageService.set('competencyName',PostDataResponse2);
            		
            		window.localStorage.setItem('role',$rootScope.userObject.role.roleName);
            		var roleName = localStorage.getItem('role');
            		$rootScope.role = roleName;
            		var PostDataResponse1=$rootScope.role;
            		localStorageService.set('role',PostDataResponse1);
            		
            		if($rootScope.userObject.firstTime){
            			AuthenticationService.SetCredentials($scope.username, $scope.password);
            			$location.path('/firsttimelogin');
            		}
            	    else if($rootScope.userObject.role.roleId==1){
            	    	/*alert("1")
            	    	alert("login")*/
            	    	AuthenticationService.SetCredentials($scope.username, $scope.password);                    
            	    	$rootScope.home = "";
            	    	$location.path('/');
            	    }
            	else if($rootScope.userObject.role.roleId==2){
            		ListOfDate.Flist($rootScope.compy, function(data) {
             			$scope.userObject1 = data;
             		 	localStorage.setItem('datas',JSON.stringify($scope.userObject1));
             			$scope.PostDataResponse4 = JSON.parse(localStorage.getItem('datas'));
                 		localStorageService.set('datas',$scope.PostDataResponse4);
                 		AuthenticationService.SetCredentials($scope.username, $scope.password);                    
                 		 listEvents.List($rootScope.username,function(data){
                             $scope.userObject2 = data;
                                 localStorage.setItem('dataE',JSON.stringify($scope.userObject2));
                                 $scope.PostDataResponse5 = JSON.parse(localStorage.getItem('dataE'));
                                     localStorageService.set('datas1',$scope.PostDataResponse5);
                                        ProposedEve.Flist($rootScope.compy,function(data){
                                         $scope.userObject3 = data;
                                             localStorage.setItem('dataP',JSON.stringify($scope.userObject3));
                                             $scope.PostDataResponse6 = JSON.parse(localStorage.getItem('dataP'));
                                                 localStorageService.set('datas2',$scope.PostDataResponse6);
                                        $rootScope.home = "";
                                        $location.path('/PanelLogin');
                                     });
                             })
                         });
            	}
            	else if($rootScope.userObject.role.roleId==3){
            		//alert("3")
            			
            				
            				AuthenticationService.SetCredentials($scope.username, $scope.password);                    
            				$rootScope.home = "";
            				$location.path('/LeadLogin');
            			
            	}
            	else if($rootScope.userObject.role.roleId==4){
            		AuthenticationService.SetCredentials($scope.username, $scope.password);                    
                    $rootScope.home = "";
                    $location.path('/CoordinatorLogin');
            	}
            	else if($rootScope.userObject.role.roleId==5){
            		AuthenticationService.SetCredentials($scope.username, $scope.password);                    
                    $rootScope.home = "";
                    $location.path('/ResourceLogin');
            	}
            		
            		//angular.mock.dump(data);$scope.login = function();
            		}
            		
            	//	$scope.login1();
            		
            });
            	
        };
        $scope.login1 = function(){
        	
        	
			
		};
    }])
/*.controller('forgetControoller',
	    ['$scope', '$rootScope', '$location','ForgotPasswordConfService','myService',
	    function ($scope, $rootScope, $location, AuthenticationService, myService) {
	        // reset login status
	  
	  
	        AuthenticationService.ClearCredentials();
	        $rootScope.home = myService.sharedObject.data;
	        $scope.ForgotPWD = function () {
	            $scope.dataLoading = true;
	            AuthenticationService.Login($scope.username, function(response) {
	            	 window.localStorage.setItem('username',$scope.username);
	             	$rootScope.username=$scope.username;
	             	
	                if(response=="true") {
	                    AuthenticationService.SetCredentials($scope.username);
	                   
	                    $rootScope.home = "";
	                    $location.path('/');
	                } 
	                else if(response=="InvalidUser") {
	                    AuthenticationService.SetCredentials($scope.username);
	                    $location.path('/login');
	                }
	                
	                else if(response=="InvalidUser") {
	                    $scope.error = response.message;
	                    alert($scope.error);
	                    alert("Hello error generated !");
	                    $scope.dataLoading = false;
	                }
	                
	                else {
	                	alert("Wrong credentials entered !");
	                	
	                	$location.path('/home');
	                }
	            });
	        };
	    }])*/
.controller('resetPasswordController',
	    ['$scope', '$rootScope', '$location','ResetPasswordConfService','myService',
	    function ($scope, $rootScope, $location,AuthenticationService, myService) {
	        // reset login status
	        AuthenticationService.ClearCredentials();
	        $rootScope.home = myService.sharedObject.data;
	        $scope.login = function () {
	            $scope.dataLoading = true;
	            AuthenticationService.Login($scope.username, $scope.password, function(response) {
	            	 window.localStorage.setItem('username',$scope.username);
	             	$rootScope.username=$scope.username;
	             	
	                if(response=="true") {
	                    AuthenticationService.SetCredentials($scope.username);
	                   
	                    $rootScope.home = "";
	                    $location.path('/');
	                } 
	                else if(response=="firsttimelogin") {
	                    AuthenticationService.SetCredentials($scope.username, $scope.password);
	                    $location.path('/firsttimelogin');
	                }
	                
	                /*else if(response=="InvalidUser") {
	                    $scope.error = response.message;
	                    alert($scope.error);
	                    alert("Hello error generated !");
	                    $scope.dataLoading = false;
	                }*/
	                
	              
	            });
	          
	        };
	    }]);
angular.module('Authentication').controller('HomeFirsttimeController', ['$scope', '$location', '$rootScope', function ($scope, $location, $rootScope) {

	$rootScope.home = "";
}]);
angular.module('Authentication').controller('HomeController', ['$scope', '$location', '$rootScope', function ($scope, $location,  $rootScope) {
	
	$rootScope.home = "";
}]);
angular.module('Authentication').controller('userName', ['$scope', '$location', '$rootScope', function ($scope, $location,  $rootScope) {
	
	$rootScope.home = "";
}]);
angular.module('Authentication').controller('FirstLoginController', 
		['$scope','$routeParams', '$location','localStorageService', 'PasswordConfService','PasswordConfigureService', 'myService', '$rootScope',
		 function ($scope, $routeParams,$location,localStorageService, PasswordConfService,PasswordConfigureService, myService, $rootScope) {
			 	
	if(window.localStorage.getItem('keyval'))
	{
    	$scope.token=window.localStorage.getItem('token');
    	$scope.username=window.localStorage.getItem('keyval');
    	$scope.password = null;
        $rootScope.home = myService.sharedObject.data;
        $scope.passwordConfirmation = null;
        var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,12})");
        var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");

        $scope.analyze = function (value) {
        	//alert(value);
            if (strongRegex.test(value)) {
                //$scope.passwordStrength["background-color"] = "green";
                $scope.myForm.password.$valid = true;
                

            } else if (mediumRegex.test(value)) {
                //$scope.passwordStrength["background-color"] = "orange";
            } else {
                //$scope.passwordStrength["background-color"] = "red";
                $scope.myForm.password.$valid = false;
            }
        };
        $scope.analyzeCfm = function (value) {
            if ($scope.password == $scope.passwordConfirmation && $scope.myForm.password.$valid) {
                $scope.myForm.passwordConfirmation.$valid = true;
            } else {
                $scope.myForm.passwordConfirmation.$valid = false;
            }

            
        };
        $scope.Flogin = function () {
            $scope.dataLoading = true;
            PasswordConfigureService.Flogin( $scope.username,$scope.token,$scope.password,$scope.passwordConfirmation, function (response) {
            	$location.path('/home');
            });
        };
	}
    else
    {
    	$scope.username=window.localStorage.getItem('username');  
    	   
    	$scope.password = null;
    	$rootScope.home = myService.sharedObject.data;
    	$scope.passwordConfirmation = null;
    	$scope.isDisabled = false;
    	var strongRegex = new RegExp("^(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9])(?=.*[!@#\$%\^&\*])(?=.{8,12})");
    	//var mediumRegex = new RegExp("^(((?=.*[a-z])(?=.*[A-Z]))|((?=.*[a-z])(?=.*[0-9]))|((?=.*[A-Z])(?=.*[0-9])))(?=.{6,})");

    	$scope.analyze = function (value) {
    	//alert(value);
        if (strongRegex.test(value)) {
            //$scope.passwordStrength["background-color"] = "green";
            $scope.myForm.password.$valid = true;
            

        } /*else if (mediumRegex.test(value)) {
            //$scope.passwordStrength["background-color"] = "orange";
        }*/ else {
            //$scope.passwordStrength["background-color"] = "red";
            $scope.myForm.password.$valid = false;
        }
    };
    $scope.analyzeCfm = function (value) {
        if ($scope.password === $scope.passwordConfirmation && $scope.myForm.password.$valid) {
        	//alert("hello")
            $scope.myForm.passwordConfirmation.$valid = true;
            
        } else {
            $scope.myForm.passwordConfirmation.$valid = false;
            
        }
    };
    $scope.disableButton = function() {
        $scope.isDisabled = $scope.myForm.passwordConfirmation.$valid;
        if($scope.isDisabled){
        	$scope.Flogin = function () {
        	//alert($scope.username)
                $scope.dataLoading = true;
                PasswordConfService.Flogin( $scope.username,$scope.password, $scope.passwordConfirmation, function (response) {
                	$scope.userObject=response;
                	if($scope.userObject.message=="Password has been set for first time user"){
                		alert("Password has been set for first time user");
                	}else{
                		alert("Invalid User!");
                	}
                	 $location.path('/home');
                });
            };
        }
        else{
        	alert("Invalid")
        	 AuthenticationService.ClearCredentials();
        	$location.path('/firsttimelogin');
        }
    }
    
    
    }
}]);

angular.module('Authentication').directive('passwordConfirm', ['$parse', function ($parse) {
    return {
        restrict: 'A',
        scope: {
            matchTarget: '='
        },
        require: 'ngModel',
        link: function link(scope, elem, attrs, ctrl) {
            var validator = function (value) {
                ctrl.$setValidity('match', value === scope.matchTarget);
                return value;
            }

            ctrl.$parsers.unshift(validator);
            ctrl.$formatters.push(validator);

            // This is to force validator when the original password gets changed
            scope.$watch('matchTarget', function (newval, oldval) {
                validator(ctrl.$viewValue);
            });

        }
    };
}]);
angular.module('Authentication').directive('modal', function () {
    return {
        restrict: 'EA',
        scope: {
            title: '=modalTitle',
            header: '=modalHeader',
            body: '=modalBody',
            footer: '=modalFooter',
            callbackbuttonleft: '&ngClickLeftButton',
            callbackbuttonright: '&ngClickRightButton',
            handler: '=lolo'
        },
        templateUrl: 'modules/authentication/views/ForgotPasswordPopup.html',
        transclude: true,
        controller: function ($scope) {
            $scope.handler = 'pop';
        },
    };

});

angular.module('Authentication').controller
('forgetControoller',['$scope', '$rootScope', '$location','AuthenticationService', 'myService', 
function ($scope, $rootScope, $location, AuthenticationService,myService) {
	 $scope.header = 'Forgot Password!';
            $scope.handler = 'pop';
            AuthenticationService.ClearCredentials();
            $rootScope.home = myService.sharedObject.data;
            $scope.Forgotpwd = function () {
            	 $scope.dataLoading = true;
                AuthenticationService.ForgotPWD($scope.emailaddress, function (response) {
                	$rootScope.userObject = response;
                	//alert($rootScope.userObject.message)
                	if($rootScope.userObject.message== "You cannot reset Password") {
                		  $scope.emailaddress="";
                		$scope.error = "You already have Reset Password link in your email";
                         $location.path('/home');
                       
                	}
                	else if($rootScope.userObject.message== "done!"){
                		 $scope.emailaddress="";
                		$scope.error="Link Sent!";
                		 $scope.dataLoading=false;
                	
                		 $location.path('/home');
                	}
                	else if($rootScope.userObject.message== "Invalid User"){
                		 $scope.emailaddress="";
                		$scope.error="User Not found!";
                		
                	}
                	$location.path('/home');
                  //  $scope.error = response.message;
                });
            }
           
}]);
