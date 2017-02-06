'use strict';

// declare modules
angular.module('Authentication',[]);
angular.module('Home',[]);
angular.module('PanelMember',[]);
angular.module('HelloApp',[]);
angular.module('TATeam',[]);

var app = angular.module('myApp', [
    'Authentication',
    'Home',
    'ngRoute',
    'ngCookies',
    'LocalStorageModule',
    'PanelMember',
    'HelloApp',
    'TATeam'
])
.config(['$routeProvider', function ($routeProvider) {
    $routeProvider
        .when('/login', {
            controller: 'LoginController',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })
         .when('/userName', {
            controller: 'resetPasswordController',
            templateUrl: 'modules/home/views/userName.html'
        })
 
          .when('/forgot', {
            controller: 'forgetControoller',
            templateUrl: 'modules/authentication/views/login.html',
            hideMenus: true
        })

         .when('/firsttimelogin', {
             controller: 'FirstLoginController', 
             templateUrl: 'modules/authentication/views/firsttimelogin.html',
             hideMenus: true,
         })
         
         .when('/firsttimehome', {
        	 controller: 'HomeFirsttimeController',
        	 templateUrl: 'modules/home/views/firsttimehome.html'
         })
          .when('/forgotPassword', {
        	 controller: 'FirstLoginController',
        	 templateUrl: 'modules/home/views/forgotPassword.html'
         })
         .when('/resetPassword', {
      controller: 'resetPasswordController',
      templateUrl: 'modules/home/views/firsttimehome.html'
         })
        .when('/', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/home.html'
        })
        .when('/LeadLogin', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/lead.html'
        })
        .when('/CoordinatorLogin', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/coordinator.html'
        })
         .when('/ResourceLogin', {
            controller: 'HomeController',
            templateUrl: 'modules/home/views/resource.html'
        })
        .when('/PanelLogin', {
            controller: 'PanelMemberController',
            templateUrl: 'modules/home/views/PanelMember.html'
        })
         .when('/viewPanel',{
        	controller: 'DatepickerDemoCtrls',
        	templateUrl:'modules/home/views/ViewPanel.html'
        })
         .when('/viewPanel',{
        	controller: 'DatepickerDemoCtrls',
        	templateUrl:'modules/home/views/ViewPanel.html'
        })
        .when('/reports',{
        	controller: 'MyController',
        	templateUrl:'modules/home/views/Reports.html'
        })
         .when('/TAviewPanel',{
        	controller: 'Datepicker',
        	templateUrl:'modules/home/views/TAViewPanel.html'
        })
         .when('/TAreports',{
        	controller: 'Datepicker',
        	templateUrl:'modules/home/views/TAReports.html'
        })

        .otherwise({ redirectTo: '/login' });
}])
.run(['$rootScope', '$location', '$cookieStore', '$http','$routeParams',
    function ($rootScope, $location, $cookieStore, $http,$routeParams) {
        // keep user logged in after page refresh
        $rootScope.globals = $cookieStore.get('globals') || {};
        if ($rootScope.globals.currentUser) {
            $http.defaults.headers.common['Authorization'] = 'Basic ' + $rootScope.globals.currentUser.authdata; // jshint ignore:line
        }
 
        $rootScope.$on('$locationChangeStart', function (event, next, current) {
        	//console.log(current.split('/'));
        	//debugger;
            // redirect to login page if not logged in
        	if ($location.path() !== '/login' && !$rootScope.globals.currentUser) {
            	if(current.split('/').length >6 || next.split('/').length >6){
            		window.localStorage.setItem('keyval',current.split('/')[6]);
            		window.localStorage.setItem('token',current.split('/')[7]);
            		$location.path('/firsttimelogin');
            	}   
            else{
       		 $location.path('/login');
        		}  
        	}
        });
    }]);
var $scope;
var app = angular.module('miniapp', []);
function Ctrl($scope) {
    // Can replace this with: ng-init="checkboxSelection = '1'".
    $scope.checkboxSelection = '1';
    
    // Can use parseInt(x, 10) on $scope.checkboxSelection or index.toString() if you want to remove the single quotes you see in isCheckboxSelected('1').
    $scope.isCheckboxSelected = function(index) {
        return index === $scope.checkboxSelection;
    };
}

