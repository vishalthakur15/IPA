'use strict';
var proxy = window.location.pathname;
angular.module('Authentication').factory('PasswordConfService',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};
        
        service1.Flogin = function (username,password,passwordConfirmation, callback) {

            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
        	
            $http.post(ipaProxyConstant+proxy+'login/update', { userName: username, encryptedKey: password })
            .success(function (response) {

                callback(response);
            })
           
           .error(function(data, status, headers) {
                var message = 'Please enter the correct credentials! :(';
                return message; //pass error message back to $scope
            });
        };
        return service1;
    
       }])
       //--------------------------------------------------------------------------
       angular.module('Authentication').factory('listEvents',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};
        
        service1.List = function (username, callback) {

            /* Dummy authentication for testing, uses $timeout to simulate api call
             ----------------------------------------------*/
        var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
       
            $http.post(ipaProxyConstant+ proxy+'panel/getOwnEvents', { userName: username})
            .success(function (response) {

                callback(response);
            })
           
           .error(function(data, status, headers) {
                var message = 'Please enter the correct credentials! :(';
                return message; //pass error message back to $scope
            });
        };
        return service1;
    
       }])

angular.module('Authentication').factory('ProposedEve',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};
        
        service1.Flist =function(competency,callback){
       
        var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
        $http.post(ipaProxyConstant+proxy+'panel/getProposedEvents',{
        
        "competency":{
        "competencyName":competency
        }
        })
        .success(function (data,status) {
        // alert(data[0].proposeDateTime)
        callback(data)
         
         //alert("getDate$"+$scope.datay);
        // getDay($scope.datay);
        })
        
        .error(function(data, status, headers) {
        });
        };
        return service1;
    
       }])
       //-------------------------------------------------------------------------
       
       //--------------------------------------------------------------------------
       angular.module('Authentication').factory('ListOfDate',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};
        
        service1.Flist =function(competency,callback){
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
       		$http.post(ipaProxyConstant+proxy+'panel/calenderDates',{
       				"competencyName":competency
       			})
       			.success(function (data,status) {
       			// alert(data[0].proposeDateTime)
       			callback(data)
       			 
       			  //alert("getDate$"+$scope.datay);
       			 // getDay($scope.datay);
       			})
       			
       			.error(function(data, status, headers) {
       			});
        };
        return service1;
    
       }])
    angular.module('Authentication').factory('PasswordConfigureService',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};
        //alert('In Service');
        service1.Flogin = function (username,token,password,passwordConfirmation, callback) {
        	
            /* Dummy authentication for testing, uses $timeout to simulate api call
           
             ----------------------------------------------*/
        
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
            $http.post(ipaProxyConstant+proxy+'login/user/changePassword',
            		{"userName": username,"token":token, "encryptedKey": password}
            )
            .success(function (response) {
            	callback(response);
            })
           
           .error(function(data, status, headers) {
                var message = 'Please enter the correct credentials! :(';
                return message; //pass error message back to $scope
            });
        };
        return service1;
    }])
    angular.module('Authentication').factory('ResetPasswordConfService',
    ['$http', '$timeout',
    function ($http,$timeout) {
        var service1 = {};

        service1.Flogin = function (username,password,passwordConfirmation, callback) {

            /* Dummy authentication for testing, uses $timeout to simulate api call
           
             ----------------------------------------------*/
     	var ipaProxyConstant = window.location.protocol;
            $http.post(ipaProxyConstant+proxy+'login/resetPassword',{userName: username })
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
angular.module('Authentication').factory('AuthenticationService',
    ['Base64', '$http', '$cookieStore', '$rootScope', '$timeout',
    function (Base64, $http, $cookieStore, $rootScope, $timeout) {
        var service = {};
        var service1 = {};
        //---------------------------------------------------------------------
        service.ForgotPWD = function (emailaddress, callback) {
            //alert(emailaddress + "From service");
        	//alert("button clicked");
        	var ipaProxyConstant = window.location.protocol;
            $http.post(ipaProxyConstant+proxy+'login/resetPassword',{userName: emailaddress })
                .success(function (response) {
                 callback(response);
                });
        };
        //--------------------------------------------------------------------------------
       
//---------------------------------------------------------------------------------------------
        service.Login = function (username, password, callback) {

          /* Use this for real authentication
             ----------------------------------------------*///
        	var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host;*/
            $http.post(ipaProxyConstant+proxy+'login/get', { userName: username, encryptedKey: password })
                .success(function (data,response,status) {
                	
                    callback(data);
                })
                .error(function(data, status, headers) {
                	
                    callback(status);
            })
        };
        //-----------------------------------------------------------------------------------------
       

        service.SetCredentials = function (username, password) {
            var authdata = Base64.encode(username + ':' + password);

            $rootScope.globals = {
                currentUser: {
                    username: username,
                    authdata: authdata
                }
            };

            $http.defaults.headers.common['Authorization'] = 'Basic ' + authdata; // jshint ignore:line
            $cookieStore.put('globals', $rootScope.globals);
        };

        service.ClearCredentials = function () {
            $rootScope.globals = {};
            $cookieStore.remove('globals');
            $http.defaults.headers.common.Authorization = 'Basic ';
        };

        return service;
    }])

.factory('Base64', function () {
    /* jshint ignore:start */

    var keyStr = 'ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=';

  return {
        encode: function (input) {
           var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            do {
                chr1 = input.charCodeAt(i++);
                chr2 = input.charCodeAt(i++);
                chr3 = input.charCodeAt(i++);

                enc1 = chr1 >> 2;
                enc2 = ((chr1 & 3) << 4) | (chr2 >> 4);
                enc3 = ((chr2 & 15) << 2) | (chr3 >> 6);
                enc4 = chr3 & 63;

                if (isNaN(chr2)) {
                    enc3 = enc4 = 64;
                } else if (isNaN(chr3)) {
                    enc4 = 64;
                }

                output = output +
                    keyStr.charAt(enc1) +
                    keyStr.charAt(enc2) +
                    keyStr.charAt(enc3) +
                    keyStr.charAt(enc4);
                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";
            } while (i < input.length);

            return output;
        },

        decode: function (input) {
            var output = "";
            var chr1, chr2, chr3 = "";
            var enc1, enc2, enc3, enc4 = "";
            var i = 0;

            // remove all characters that are not A-Z, a-z, 0-9, +, /, or =
            var base64test = /[^A-Za-z0-9\+\/\=]/g;
            if (base64test.exec(input)) {
                window.alert("There were invalid base64 characters in the input text.\n" +
                    "Valid base64 characters are A-Z, a-z, 0-9, '+', '/',and '='\n" +
                    "Expect errors in decoding.");
            }
            input = input.replace(/[^A-Za-z0-9\+\/\=]/g, "");

            do {
                enc1 = keyStr.indexOf(input.charAt(i++));
                enc2 = keyStr.indexOf(input.charAt(i++));
                enc3 = keyStr.indexOf(input.charAt(i++));
                enc4 = keyStr.indexOf(input.charAt(i++));

                chr1 = (enc1 << 2) | (enc2 >> 4);
                chr2 = ((enc2 & 15) << 4) | (enc3 >> 2);
                chr3 = ((enc3 & 3) << 6) | enc4;

                output = output + String.fromCharCode(chr1);

                if (enc3 != 64) {
                    output = output + String.fromCharCode(chr2);
                }
                if (enc4 != 64) {
                    output = output + String.fromCharCode(chr3);
                }

                chr1 = chr2 = chr3 = "";
                enc1 = enc2 = enc3 = enc4 = "";

            } while (i < input.length);

            return output;
        }
    };

    /* jshint ignore:end */
});

	