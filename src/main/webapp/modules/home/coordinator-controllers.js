angular.module('Home', ['ui.bootstrap', 'ui.bootstrap.datetimepicker']);
angular.module('Home')
.controller('HomeController',
	['$scope','$rootScope', '$location','AddUser','DeleteUser','ProposeEvent','filterFilter','localStorageService','$http',
         function($scope, $rootScope, $location,AddUser,DeleteUser,ProposeEvent,filterFilter,localStorageService,$http){
		//$rootScope.home = myService.sharedObject.data;
		
		//$scope.competency1="Select Competency";
		$scope.competency= "Select Competency";
		$scope.competency1= "Select Competency";
		$scope.levelname="Select Level";
		$scope.idlist=[];
		/*$scope.idlist1=[];*/
        /*$scope.levelname = "Select level";*/
        debugger;
        $scope.add_user = function () {
        if($scope.competency1=="Select Competency")
       {
        	return alert("Please select a competency");
       }
        if($scope.levelname=="Select Level")
        	{
        	return alert("Please select level name");
        	}
       
        AddUser.Add($scope.firstname,$scope.lastname,$scope.emailaddress,$scope.contactnumber,$scope.competency1,$scope.levelname,function(response) {
        	 
            $rootScope.userObject=response;
            if($rootScope.userObject.message=="User_added"){
            
            	alert("User Added");
            	//$location.path('/lead');
            }
            else{
            	alert("User already exists!");
            }
            $scope.firstname="";
            $scope.lastname="";
            $scope.emailaddress="";
            $scope.contactnumber="";
            $scope.competency1="Select Competency";
            $scope.levelname="Select Level";
            /*else
            	{
            	alert("Failed!");
            	}*/
                });
        }
    	$scope.changedValueHour = function(item){
        	$scope.hourStep = item;
        }
        $scope.changedValueMinute = function(item){
        	$scope.minuteStep = item;
        }
        $scope.propose_event = function () {
        

    	$scope.duration=$scope.hourStep*60 +$scope.minuteStep;
    	
    	
    	$scope.user=localStorageService.get('username');
    	/*alert($scope.user);*/
    	if($scope.duration ==0||$scope.duration ==1||$scope.duration ==5 || $scope.duration==10 ||$scope.duration==15||$scope.duration==25 ){
    		return alert("Event cannot be proposed for less than half an hour");
    	}
    	if($scope.competency=="Select Competency"){
    		return alert("Select Competency ");
    	}
    	else{
    		
    	ProposeEvent.Propose($rootScope.date,$scope.duration,$scope.competency,$scope.Description,$scope.user,function(response) {
    	
    	$scope.userObject=response;
    		if($scope.userObject.message=="passed"){
    			alert("Event Proposed");
    		}
    		else {
    			alert("Not Submitted");
    		}
    		
        });
    	$scope.competency="Select Competency";
    	$scope.competency1="Select Competency";
    	$scope.levelname = "Select Level";
    	$scope.date = new Date();
    	$scope.Description=null;
    	}
    }
		
        $http.get("http://172.16.111.68:9082/admin/list").success(function (data, status) { 
        	/*alert(status);*/
        	if(status==200)
        		{
        		alert("You are successfully logged in");
        		}
            $scope.data = data;
        });
        
        /*var arr;*/
        $scope.removeSelected=function(){
        
        	
        	$scope.data = filterFilter($scope.data,function(item){
        		/*if(item.selected == undefined){
                    alert("Please check it out the checkbox");
                 }*/
        		if(item.selected){
        			$scope.idlist.push(item[1])
        		}
        		return !item.selected;
        	})
        	if($scope.idlist.length==0){
        		return alert("Please select user!");
        	}
        	
        	userNames = $scope.idlist;
        	DeleteUser.deleteUser(userNames,function(response){
        			alert("User Deleted");
        	})
        }
        
      /*  $scope.userinfos = [
            { userId: 'John', username: 'Doe', email: 'john@epam.com' },
            { userId: 'Ravi', username: 'Cheganti', email: 'cheganti@epam.com' },
            { userId: 'sadsa', username: 'egg', email: '7yyy@epam.com' },
            { userId: 'qww', username: 'nyt', email: 'nyt@epam.com' },
            { userId: 'qwe', username: 'rew', email: 'rew@epam.com' },
            { userId: '1qqq', username: 'yui', email: 'yui@epam.com' }
        ];*/
       /* $scope.search = function (row) {
            
            return !!((row.item[0].indexOf($scope.query || '') !== -1 || row.username.indexOf($scope.query || '') !== -1));
        };*/
      
        
       // alert($rootScope.hourStep);
  /* -------------------------------------------------------------------------*/
   $scope.validatenumber = function(evt) {
		var theEvent = evt || window.event;
		 var key = theEvent.keyCode || theEvent.which;
		 key = String.fromCharCode(key);
		 var regex = /^[A-z\b]+$/;    // allow only numbers [0-9] 
		 if( !regex.test(key) ) {
		   theEvent.returnValue = false;
		   $scope.errorKeyFlag = true;
		   if(theEvent.preventDefault) theEvent.preventDefault();
		   
}                                                                
}
/*   $scope.add_competency = function () {
  //alert("hello harsh here!!");
//   	debugger;
   AddCompetency.Add($scope.selectcompetency,function(response) {
	   
   	$rootScope.userObject=response;
   	if($rootScope.userObject.message=="passes"){
   		alert("Competency Added");
   	}
   		else
   			{
   			alert("Already Exist");
   			}
   	$scope.selectcompetency="";
   	
   	
     	                });
   }
   */
 /*  'use strict';
   $http.get("http://172.16.111.68:9082/competency/get").success(function (data, status) { 
//   	alert(status);
       $rootScope.data1 = data;
   });
   */
  /* var arr;
   $scope.removeSelected1=function(){
   //alert("in delete");
   $scope.data1 = filterFilter($scope.data1,function(item){
   if(item.selected){
   $scope.idlist1.push(item[0]);
   //alert("hello");
   }
   return !item.selected;
   })
   if($scope.idlist1.length==0){
		return alert("Please select user!");
	}
  
   competencyNames = $scope.idlist1;
  // alert(competencyNames);
    DeleteCompetency.removeCompetency(competencyNames,function(response){
   	$rootScope.userObject=response;
   	//if($rootScope.userObject.deleted=="deleted"){
   		//alert("Deleted");
   alert("Competency Deleted!");
   })
   }*/
   $scope.panelData1 = true;
   $scope.panelAdd1 = false;
   $scope.panelEdit1 = false;
   $scope.isDisabled = false;
  // $scope.isDisabled1 = false;
   $scope.dashboard=function()
   {
       $scope.dashboard0 = false;
       $scope.dashboard1 = false;
      // $scope.dashboard2 = false;
   }
   $scope.panelData = function () {
       $scope.dashboard0 = true;
       $scope.dashboard1 = true;
      // $scope.dashboard2 = false;
       $scope.panelData1 = true;
       $scope.panelAdd1 = false;
       $scope.panelEdit1 = false;
       $scope.isDisabled = false;
   }

   $scope.panelAdd = function () {
       $scope.panelAdd1 = true;
       $scope.panelData1 = false;
       $scope.panelEdit1 = false;
       $scope.isDisabled = true;
   };
   $scope.panelEdit = function () {
       $scope.panelAdd1 = false;
       $scope.panelData1 = false;
       $scope.panelEdit1 = true;

       //alert("hello");
   };
  /* $scope.addcompetency = function () {
       $scope.dashboard0 = true;
       $scope.dashboard1 = false;
       $scope.dashboard2 = true;
       $scope.cometeData = true;
       $scope.competAdd1 = true;
       $scope.isDisabled = false;
       //$scope.competEdit1 = true;
   };
   $scope.competAdd = function ()
   {
       $scope.cometeData = false;
       $scope.competAdd1 = false;
       $scope.isDisabled = true;
       //$scope.competEdit1 = true;
   };*/
	
  }])
 
.controller('DateTimePickerDemoCtrl',
		['$rootScope',
function ($scope, $timeout,$rootScope) {
  $scope.dateTimeNow = function() {
    $scope.date = new Date();
  };
  $scope.dateTimeNow();
  
  $scope.toggleMinDate = function() {
    var minDate = new Date();
    // set to yesterday
    minDate.setDate(minDate.getDate());
    $scope.dateOptions.minDate = $scope.dateOptions.minDate ? null : minDate;
  };
   
  $scope.dateOptions = {
    showWeeks: false,
    startingDay: 0
  };
  
  $scope.toggleMinDate();
  
  // Disable weekend selection
/*  $scope.disabled = function(calendarDate, mode) {
    return mode === 'day' && ( calendarDate.getDay() === 0|| calendarDate.getDay() === 6 );
  };*/
  
  $scope.open = function($event,opened) {
    $event.preventDefault();
    $event.stopPropagation();
    $scope.dateOpened = true;
  };
  
  $scope.dateOpened = false;
  
  $scope.format = "dd-MMM-yyyy";
  $scope.minuteStep = 1;
  $scope.hourStep = 1;

  $scope.timeOptions = {
    hourStep: [0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10],
    minuteStep: [0,1, 5, 10, 15, 25, 30,45]
  };

  $scope.showMeridian = true;
  $scope.timeToggleMode = function() {
    $scope.showMeridian = !$scope.showMeridian;
  };
  
  $scope.$watch("date", function(date) {
    // read date value
  }, true);
  
  $scope.resetHours = function() {
    $scope.date.setHours(1);
  };
}]);