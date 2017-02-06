var proxy = window.location.pathname;
angular.module('Home', ['ui.bootstrap', 'ui.bootstrap.datetimepicke']);
angular.module('Home')
.controller('HomeController',
['$scope','$rootScope', '$location','AddUser','DeleteUser','CancelInterview','Lead','CancelInterviewMail','AddCompetency','AvailList','DeleteCompetency','ProposeEvent','AddCompetency','DeleteCompetency','filterFilter','localStorageService','$http',
         function($scope, $rootScope, $location,AddUser,DeleteUser,CancelInterview,Lead,CancelInterviewMail,AddCompetency,AvailList,DeleteCompetency,ProposeEvent,AddCompetency,DeleteCompetency,filterFilter,localStorageService,$http){
$scope.viewpanel=false;
//$rootScope.home = myService.sharedObject.data;
$scope.competency= "Select Competency";
$scope.competency1s= "Select Competency";
$scope.levelname="Select Level";
$scope.hour="Select Hour";
$scope.minute="Select Minute";
$scope.idlist=[];
$scope.idlist1=[];
$scope.competencyName1 = localStorageService.get('competencyName');
$rootScope.user=localStorageService.get('username');
       
$scope.getCompetency = function(com){
	   $scope.competency1 = com;

	if(localStorageService.get('role')==="Admin"){
		 var ipaProxyConstant = window.location.protocol/* + "//" + window.location.host*/;
	        $http.post(ipaProxyConstant+proxy+'/competency/getLevel',{"competencyName":com}).success(function (data, status) {
	        $scope.data2 = data;
	        })

	}else{
 var ipaProxyConstant = window.location.protocol/* + "//" + window.location.host*/;
 $http.post(ipaProxyConstant+proxy+'/competency/getLevel',{"competencyName":$scope.competencyName1}).success(function (data, status) {
 $scope.data2 = data;
 })
	}
}
        
       /*Add User....*/
        $scope.add_user = function () {
        $scope.role=localStorageService.get('role');
        if($scope.levelname=="Select Level")
        {
            return alert("Please select level name");
        }
        AddUser.Add($scope.rolPerson,$scope.firstname,$scope.lastname,$scope.emailaddress,$scope.contactnumber,$scope.competency1,$scope.levelname,function(response) {
            $rootScope.userObject=response;
            if($rootScope.userObject.message=="User_added"){
            alert("User Added");
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
                });
        }
        $scope.add_user1 = function () {
           // $scope.rolePerson=localStorageService.get('role');
            if($scope.levelname=="Select Level")
            {
                return alert("Please select level name");
            }
            
            AddUser.Add($scope.rolPerson,$scope.firstname,$scope.lastname,$scope.emailaddress,$scope.contactnumber,$scope.competencyName1,$scope.levelname,function(response) {
                $rootScope.userObject=response;
                if($rootScope.userObject.message=="User_added"){
                alert("User Added");
                }
                else{
                alert("User already exists!");
                }
                $scope.firstname="";
                $scope.lastname="";
                $scope.emailaddress="";
                $scope.contactnumber="";
                $scope.levelname="Select Level";
                $scope.panelDataLead();
                    });
            }
        $scope.changedValueHour = function(item){
        $scope.hour = item;
        //alert($scope.hour)
        }
        $scope.changedValueMinute = function(item){
        $scope.minute = item;
        //alert($scope.minute)
        }
        $scope.changedValueDate = function(item){
        $scope.date = item;
        }
        
        $scope.propose_event = function () {
        
    $scope.duration=parseInt($scope.hour*60)+ parseInt($scope.minute);
    if($scope.Description==null){
    	return alert("Enter Description");
    }

    if($scope.duration==15||$scope.duration==25 ){
    return alert("Event cannot be proposed for less than half an hour");
    }
   
    if($scope.hour=="Select Hour"||$scope.minute=="Select Minute"){
    return alert("Select Duration");
    }
    else{
    	if(localStorageService.get('role')=="admin"){
    		  if($scope.competency1=="Select Competency")
    	        {
    			  return alert("Please select a competency");
    	        }
    ProposeEvent.Propose($scope.date,$scope.duration,$scope.competency,$scope.Description,$scope.user,function(response) {
    $scope.userObject=response;
    if($scope.userObject.message=="passed"){
    alert("Event Proposed");
    
    
    }
    else {
    alert("Not Submitted");
    }
        });
    $scope.hour   ="Select Hour";
            $scope.minute ="Select Minute";
      $scope.competency="Select Competency";
    $scope.competency1="Select Competency";
    $scope.levelname = "Select Level";
         $scope.Description=null;
    }else{
    	ProposeEvent.Propose($scope.date,$scope.duration,$scope.competencyName1,$scope.Description,$scope.user,function(response) {
    	    $scope.userObject=response;
    	    if($scope.userObject.message=="passed"){
    	     alert("Event Proposed");
    	    }
    	    else {
    	     alert("Not Submitted");
    	    }
    	        });
    	    $scope.hour   ="Select Hour";
    	            $scope.minute ="Select Minute";
    	         $scope.Description=null;
    	
    }
    }
    }
        /*Remove User*/      
        $scope.removeSelected=function(){
        
        $scope.data = filterFilter($scope.data,function(item){
        /*if(item.selected == undefined){
                  return  alert("Please check it out the checkbox");
                 }*/
        if(item.selected){
        $scope.idlist.push(item.userName)
        }
        return !item.selected;
        })
        /*if($scope.idlist.length==0){
        return alert("Please select user!");
        }*/
        
        userNames = $scope.idlist;
        DeleteUser.deleteUser(userNames,function(response){
        alert("User Deleted");
           $scope.display1(); 
          /* $scope.display1(); */
          // $rootScope.userObject=response;
        //alert("User Deleted");
        })
        }
        $scope.removeSelected2=function(){
            
            $scope.data = filterFilter($scope.LeadData123,function(item){
            /*if(item.selected == undefined){
                      return  alert("Please check it out the checkbox");
                     }*/
            if(item.selected){
            $scope.idlist.push(item.userName)
            }
            return !item.selected;
            })
            /*if($scope.idlist.length==0){
            return alert("Please select user!");
            }*/
            
            userNames = $scope.idlist;
            DeleteUser.deleteUser(userNames,function(response){
            alert("User Deleted");
               $scope.display123(); 
               
              /* $scope.display1(); */
              // $rootScope.userObject=response;
            //alert("User Deleted");
            })
            }
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
   $scope.add_competency = function () {
  //alert("hello harsh here!!");
//     debugger;
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
   
   'use strict';
   var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
   $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/competency/get").success(function (data, status) { 
//     alert(status);
       $rootScope.data1 = data;
       localStorage.setItem('data12',JSON.stringify($rootScope.data1));
       $scope.PostDataResponse4 = JSON.parse(localStorage.getItem('data12'));
      localStorageService.set('datas',$scope.PostDataResponse4);
   });
   /*Remove User*/      
   $scope.removeSelected11=function(){
   
   $scope.LeadData123 = filterFilter($scope.LeadData123,function(item){
   /*if(item.selected == undefined){
             return  alert("Please check it out the checkbox");
            }*/
   if(item.selected){
   $scope.idlist.push(item.userName)
   }
   return !item.selected;
   })
   
   
   userNames = $scope.idlist;
   DeleteUser.deleteUser(userNames,function(response){
   alert("User Deleted");
      $scope.display123(); 
      
     
   })
   }
   var arr;
   $scope.removeSelected1=function(){
   //alert("in delete");
   $rootScope.data1 = filterFilter($rootScope.data1,function(item){
   if(item.selected){
   $scope.idlist1.push(item.competencyName);
   //alert("hello");
   }
   return !item.selected;
   })
   competencyNames = $scope.idlist1;
    DeleteCompetency.removeCompetency(competencyNames,function(response){
    $scope.display(); 
    $rootScope.userObject=response;
   })
   }
   $scope.panelData1 = true;
   $scope.panelAdd1 = false;
   $scope.panelEdit1 = false;
   $scope.isDisabled = false;
   $scope.dashboard=function()
   {
       $scope.dashboard0 = false;
       $scope.dashboard1 = false;
       $scope.dashboard2 = false;
   }
  
  $scope.panelData = function () {
  $scope.rolPerson="Panel";
       $scope.dashboard0 = true;
       $scope.dashboard1 = true;
       $scope.dashboard2 = false;
       $scope.panelData1 = true;
       $scope.panelAdd1 = false;
       $scope.panelEdit1 = false;
       $scope.isDisabled = true;
       var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
       $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listPanel").success(function (data, status) { 
       // alert(status);
        /*if(status==200)
        {
        alert("You are successfully logged in");
        }*/
           $scope.data = data;
       })
      // $window.location.reload();
       
   }
  $scope.LeadData = function () {
 $scope.rolPerson="Lead";
 //alert("Lead")
      $scope.dashboard0 = true;
      $scope.dashboard1 = true;
      $scope.dashboard2 = false;
      $scope.panelData1 = true;
      $scope.panelAdd1 = false;
      $scope.panelEdit1 = false;
      $scope.isDisabled = true;
      var ipaProxyConstant = window.location.protocol/* + "//" + window.location.host*/;
      $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listLead").success(function (data, status) { 
          // alert(status);
          /*if(status==200)
          {
          alert("You are successfully logged in");
          }*/
              $scope.data = data;
          })
        //  alert( $scope.data.contact.emailId)
     // $window.location.reload();
      
  }
  $scope.CoordinatorData = function () {
 $scope.rolPerson="Coordinator";
 //alert("Coordinator")
      $scope.dashboard0 = true;
      $scope.dashboard1 = true;
      $scope.dashboard2 = false;
      $scope.panelData1 = true;
      $scope.panelAdd1 = false;
      $scope.panelEdit1 = false;
      $scope.isDisabled = true;
      var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
      $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listCoordinator").success(function (data, status) { 
          // alert(status);
          /*if(status==200)
          {
          alert("You are successfully logged in");
          }*/
              $scope.data = data;
          })
     // $window.location.reload();
      
  }
  $scope.ResourceData = function () {
 $scope.rolPerson="Recruiter"; 
      $scope.dashboard0 = true;
      $scope.dashboard1 = true;
      $scope.dashboard2 = false;
      $scope.panelData1 = true;
      $scope.panelAdd1 = false;
      $scope.panelEdit1 = false;
      $scope.isDisabled = true;
     // $window.location.reload();
      var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
      $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listResource").success(function (data, status) { 
          // alert(status);
          /*if(status==200)
          {
          alert("You are successfully logged in");
          }*/
              $scope.data = data;
          })
  }

   $scope.panelAdd = function () {
  $scope.firstname="";
  $scope.lastname="";
  $scope.emailaddress="";
  $scope.contactnumber="";
  $scope.competency1="Select Competency";
  $scope.levelname="Select Level";
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
   $scope.panelDataLead = function(){
  $scope.rolPerson="Panel";
  //alert("Lead")
       $scope.dashboard0 = true;
       $scope.dashboard1 = true;
       $scope.dashboard2 = false;
       $scope.panelData1 = true;
       $scope.panelAdd1 = false;
       $scope.panelEdit1 = false;
       $scope.isDisabled = true;
  Lead.getPanel($scope.competencyName1,function(response) {
          $scope.LeadData123 = response;
               });
  
   }

   $scope.CoordinatorData1 = function () {
  $scope.rolPerson="Coordinator";
  //alert("Coordinator")
       $scope.dashboard0 = true;
       $scope.dashboard1 = true;
       $scope.dashboard2 = false;
       $scope.panelData1 = true;
       $scope.panelAdd1 = false;
       $scope.panelEdit1 = false;
       $scope.isDisabled = true;
       var ipaProxyConstant = window.location.protocol + "//" + window.location.host;
       $http.post(ipaProxyConstant+"/login/LeadCoordinatorData",{"competencyName":$scope.competencyName1})
          .success(function (data) { 
      $scope.LeadData123 = data;
           })
   }

   $scope.addcompetency = function () {
       $scope.dashboard0 = true;
       $scope.dashboard1 = false;
       $scope.dashboard2 = true;
       $scope.cometeData = true;
       $scope.competAdd1 = true;
       $scope.isDisabled = true;
       //$scope.competEdit1 = true;
   };
   $scope.competAdd = function ()
   {
       $scope.cometeData = false;
       $scope.competAdd1 = false;
       $scope.isDisabled = true;
       //$scope.competEdit1 = true;
   };
   $scope.display=function ()
   {
  var count = 0;
   angular.forEach($scope.data1, function(item){
       if (item.selected) count++;
   });
   if(count > 0 )
    {
    $scope.isDisabled = false;
    }
   else
    {
    $scope.isDisabled = true;
    }
   };
   $scope.display123=function ()
   {
  var count = 0;
   angular.forEach($scope.LeadData123, function(item){
       if (item.selected) count++;
      
   });
   if(count > 0 )
    {
     $scope.isDisabled = false;
    }
   else
    {
     $scope.isDisabled = true;
    }
  
   };
   $scope.display1=function ()
   {
  var count = 0;
   angular.forEach($scope.data, function(item){
       if (item.selected) count++;
   });
   if(count > 0 )
    {
    $scope.isDisabled = false;
    }
   else
    {
    $scope.isDisabled = true;
    }
   };
 
  }])
 

.controller('AdminDateController',
['$rootScope',
function ($scope, $timeout,$rootScope) {
  $scope.dateTimeNow = function() {
    $scope.date = new Date();
  };
  $scope.dateTimeNow();
  $scope.toggleMinDate = function() {
    var minDate = new Date();
    //$scope.minDate = $scope.minDate ? null : new Date();
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
 /* $scope.disabled = function(calendarDate, mode) {
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
}])
.controller('DatepickerDemoCtrls',function($scope,AvailList,localStorageService,$rootScope,$http,$q) {
	$scope.user=localStorageService.get('username');
$scope.Submit=function(competency,dt){
if($scope.competency123=="Select Competency"){
$scope.viewpanel=false;
return alert("Select a Competency ")
}
else{
  $rootScope.arrayOfMembers=[];
  var x=0;
  var dates= new Date(dt);
  var month = new Array();
  month[0] = "January";
  month[1] = "February";
  month[2] = "March";
  month[3] = "April";
  month[4] = "May";
  month[5] = "June";
  month[6] = "July";
  month[7] = "August";
  month[8] = "September";
  month[9] = "October";
  month[10] = "November";
  month[11] = "December";
  var m = month[dates.getMonth()];
  var y=dates.getFullYear();
$scope.month=m;
$scope.year=y;
  var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
  $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listPanel").success(function (data, status) { 
    $scope.PanelList=[];
       $scope.data = data;
      
       for(var i=0;i<$scope.data.length;i++){
       var id =$scope.data[i].userId; 
       /*for(var j=0;j<$scope.EventsDate1.length;j++){*/
     //  alert($scope.competency)
       if($scope.data[i].levels.competency.competencyName===competency){
        $scope.viewpanel=true;
        $scope.PanelList.push({
        "fName":$scope.data[i].firstName,
        "lName":$scope.data[i].lastName,
        "userId":$scope.data[i].userId,
        "statuses":[]
        
       })
       
       }
      }
       local($scope.PanelList);
     })
     .error(function(data){
     alert("error")
     })
  
     local = function(PanelList){
     $scope.PanelListD =[];
     $scope.PanelListD =  PanelList;
     $scope.datesfrom=[]; 
    for(var i=0;i<$scope.PanelListD.length;i++){
    var iPromise = $q.when(i);
    var promise= AvailList.getList($scope.PanelListD[i].userId,dates,function(response){})
    $q.all([iPromise,promise]).then(function(result){
    $scope.ListAvail = result[1];
    $rootScope.arrayOfMembers.push({
    "list": $scope.ListAvail
    })
    console.log(result[0] + " : " +result[1]);
     $scope.datesfrom=[]; 
     for(var j=0;j<$scope.ListAvail.length;j++){
     var date2 =new Date($scope.ListAvail[j].availability_date);
     var onlyDate = date2.getDate();
     $scope.datesfrom.push({
    date: onlyDate 
    })
     }
     /*for(var i =0;i<$scope.datesfrom.length;i++){
    	 alert($scope.datesfrom[i].date)
     }*/
     
    var Array2=[];
    var Array3=[];
    var flag=false;
    for(var i=1;i<$rootScope.Array1.length;i++){
    for(var l=0;l<$scope.datesfrom.length;l++){
    flag = false;
    var onlyDate = $scope.datesfrom[l].date
    if(onlyDate==$rootScope.Array1[i].date){
    flag = true;
    break;
     
    }else{
    flag = false;
    }
    }
    if(flag==true){
    Array3[i]=1; 
    }
    else{
    Array3[i] =0;
    }
   }
    for(var z=1;z<Array3.length;z++){
    if(Array3[z]==1){
    Array2.push({
    status:'full',
    linkId:1,
    date:z
    
    })
    }else{
    Array2.push({
    status:'partial'
    })
    }
    }
    $scope.PanelListD[result[0]].statuses = Array2;
     });
    }
    
     }
  var date44 =new Date(dates);
   var onlyDate44 = date44.getDate();
   //alert(dates.getDay())
   var firstOfMonth = new Date(dates.getFullYear(),dates.getMonth(), 1);
   //alert(firstOfMonth)
   //alert(firstOfMonth.getDay());
var month = parseInt(dates.getMonth());
var year = dates.getYear();
var weekday = new Array(7);
   weekday[0] = "Su";
   weekday[1] = "Mo";
   weekday[2] = "Tu";
   weekday[3] = "We";
   weekday[4] = "Th";
   weekday[5] = "Fr";
   weekday[6] = "Sa";
var n = firstOfMonth.getDay();
  // var n = weekday[onlyDate44.getDay()];
  // alert(n)
$rootScope.Array2=[];
if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<32;i++){
$rootScope.Array1.push({
day:weekday[n],
date:i
})
//alert("n"+n)
// alert($rootScope.Array1[i].date)
if(n==6)
{
n=0
}
else
{
n=n+1
}
}

}
else if(month==1 && (year%400 == 0||year%4==0)){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<30;i++){
$rootScope.Array1.push({
day:weekday[n],
date:i
})
if(n==6)
{
n=0
}
else
{
n=n+1
}
}
}
else if(month==1&& !(year%400 == 0||year%4==0)){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<29;i++){
$rootScope.Array1.push({
day:weekday[n],
date:i
})
if(n==6)
{
n=0
}
else
{
n=n+1
}
}
}
else{
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<31;i++){
$rootScope.Array1.push({
day:weekday[n],
date:i
})
if(n==6)
{
n=0
}
else
{
n=n+1
}
}

}
//alert(competency+" "+date)
} // end of else
}
//$scope.data3=localStorageService.get('datas');
//var length =$scope.data3.length;
//alert($scope.data3[length-1].competencyName)
//$scope.data3[length-1].competencyName;
$scope.competency123 = "Select Competency"
//     $scope.competency=$rootScope.data1[0].competencyName;
/*var ipaProxyConstant = window.location.protocol + "//" + window.location.host;
   $http.get(ipaProxyConstant+"/competency/get").success(function (data, status) { 
//     alert(status);
    $rootScope.data2=[];
    $scope.comp = data[0].competencyName;
    for(var i=1;i<data.length;i++){
    $rootScope.data2.push({
    competencyName:data[i].competencyName
    })
    }
    //$scope.competency = $scope.comp;
      // $rootScope.data1 = data;
      // $scope.competency =$rootScope.data1[0].competencyName;
   });*/
//  $scope.competency="Select Competency"
 $scope.role=localStorageService.get('role');
 $rootScope.Array1=[];
 //$scope.EventsDate1 = localStorageService.get('datas1');
 
// $scope.PanelList=[];
 $scope.today = function() {
    $scope.dt = new Date();
    //CHANGE
    $scope.dtYr = new Date();
    //CHANGEEND
  };
  $scope.today();
/*
  //$scope.clear();
  $scope.clear = function () {
    $scope.dt = null;
  };*/

  $scope.open = function($event) {
    $scope.status.opened = true;
  };
  
  //CHANGE
  $scope.openYr = function($event) {
    $scope.status.openedYr = true;
  };
  //CHANGEEND

  $scope.dateOptions = {
    formatYear: 'yyyy',
    startingDay: 1,
    minMode: 'month'
  };
  
  $scope.dateOptionsYr = {
    formatYear: 'yyyy',
    startingDay: 1,
    minMode: 'year'
  };

  $scope.formats = ['MM/yyyy'];
  $scope.format = $scope.formats[0];
  
  $scope.formatsYr = ['yyyy'];
  $scope.formatYr = $scope.formatsYr[0];
  
  $scope.status = {
    opened: false,
    //CHANGE
    openedYr: false
    //CHANGEEND
  };

/*  $rootScope.arrayOfMembers=[];
  var x=0;
  var dates= new Date($scope.dt);
  var ipaProxyConstant = window.location.protocol + "//" + window.location.host;
     $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listPanel").success(function (data, status) { 
    $scope.PanelList=[];
       $scope.data = data;
       for(var i=0;i<$scope.data.length;i++){
       var id =$scope.data[i].userId; 
       for(var j=0;j<$scope.EventsDate1.length;j++){
       if($scope.data[i].levels.competency.competencyName===competency){
      
        $scope.PanelList.push({
        "fName":$scope.data[i].firstName,
        "lName":$scope.data[i].lastName,
        "userId":$scope.data[i].userId,
        "statuses":[]
        
       })
       
       }
      }
       local($scope.PanelList);
     })
     .error(function(data){
     alert("error")
     })
    // debugger;
     local = function(PanelList){
     $scope.PanelListD =[];
     $scope.PanelListD =  PanelList;
     
    for(var i=0;i<$scope.PanelListD.length;i++){
    var iPromise = $q.when(i);
    var promise= AvailList.getList($scope.PanelListD[i].userId,dates,function(response){})
    $q.all([iPromise,promise]).then(function(result){
    $scope.ListAvail = result[1];
    $rootScope.arrayOfMembers.push({
    "list": $scope.ListAvail
    })
    console.log(result[0] + " : " +result[1]);
     $scope.datesfrom=[]; 
     for(var j=0;j<$scope.ListAvail.length;j++){
     var date2 =new Date($scope.ListAvail[j].availability_date);
     var onlyDate = date2.getDate();
     $scope.datesfrom.push({
    date: onlyDate 
    })
     }  
    var Array2=[];
    var Array3=[];
    var flag=false;
    for(var i=1;i<$rootScope.Array1.length;i++){
    for(var l=0;l<$scope.datesfrom.length;l++){
    flag = false;
    var onlyDate = $scope.datesfrom[l].date
    if(onlyDate==$rootScope.Array1[i].date){
    flag = true;
    break;
     
    }else{
    flag = false;
    }
    }
    if(flag==true){
    Array3[i]=1; 
    }
    else{
    Array3[i] =0;
    }
   }
    for(var z=1;z<Array3.length;z++){
    if(Array3[z]==1){
    Array2.push({
    status:'full',
    linkId:1,
    date:z
    
    })
    }else{
    Array2.push({
    status:'partial'
    })
    }
    }
    $scope.PanelListD[result[0]].statuses = Array2;
     });
    }
    
     }
var month = parseInt(dates.getMonth());
var year = dates.getYear();
$rootScope.Array2=[];
if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<32;i++){
$rootScope.Array1.push({
date:i
})
}

}
else if(month==1 && (year%400 == 0||year%4==0)){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<30;i++){
$rootScope.Array1.push({
date:i
})
}
}
else if(month==1&& !(year%400 == 0||year%4==0)){
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<29;i++){
$rootScope.Array1.push({
date:i
})
}
}
else{
$rootScope.Array1=[];
$rootScope.Array1.push({
	date:"Name"
	})
for(var i=1;i<31;i++){
$rootScope.Array1.push({
date:i
});
}

}*/
/*   $scope.hey=function(dt){
  
  $rootScope.arrayOfMembers=[];
  var x=0;
  var dates= new Date(dt);
  var ipaProxyConstant = window.location.protocol ;
  $http.get(ipaProxyConstant+"/seinterviewpannel-0.1.0/admin/listPanel").success(function (data, status) { 
    $scope.PanelList=[];
       $scope.data = data;
       for(var i=0;i<$scope.data.length;i++){
       var id =$scope.data[i].userId; 
       for(var j=0;j<$scope.EventsDate1.length;j++){
       //alert($scope.competency)
       if($scope.data[i].levels.competency.competencyName===$scope.competency){
      
        $scope.PanelList.push({
        "fName":$scope.data[i].firstName,
        "lName":$scope.data[i].lastName,
        "userId":$scope.data[i].userId,
        "statuses":[]
        
       })
       
       }
      }
       local($scope.PanelList);
     })
     .error(function(data){
     alert("error")
     })
    // debugger;
     local = function(PanelList){
     $scope.PanelListD =[];
     $scope.PanelListD =  PanelList;
    for(var i=0;i<$scope.PanelListD.length;i++){
    var iPromise = $q.when(i);
    var promise= AvailList.getList($scope.PanelListD[i].userId,dates,function(response){})
    $q.all([iPromise,promise]).then(function(result){
    $scope.ListAvail = result[1];
    $rootScope.arrayOfMembers.push({
    "list": $scope.ListAvail
    })
    console.log(result[0] + " : " +result[1]);
     $scope.datesfrom=[]; 
     for(var j=0;j<$scope.ListAvail.length;j++){
     var date2 =new Date($scope.ListAvail[j].availability_date);
     var onlyDate = date2.getDate();
     $scope.datesfrom.push({
    date: onlyDate 
    })
     }  
    var Array2=[];
    var Array3=[];
    var flag=false;
    for(var i=1;i<$rootScope.Array1.length;i++){
    for(var l=0;l<$scope.datesfrom.length;l++){
    flag = false;
    var onlyDate = $scope.datesfrom[l].date
    if(onlyDate==$rootScope.Array1[i].date){
    flag = true;
    break;
     
    }else{
    flag = false;
    }
    }
    if(flag==true){
    Array3[i]=1; 
    }
    else{
    Array3[i] =0;
    }
   }
    for(var z=1;z<Array3.length;z++){
    if(Array3[z]==1){
    Array2.push({
    status:'full',
    linkId:1,
    date:z
    
    })
    }else{
    Array2.push({
    status:'partial'
    })
    }
    }
    $scope.PanelListD[result[0]].statuses = Array2;
     });
    }
    
     }
var month = parseInt(dates.getMonth());
var year = dates.getYear();
 $rootScope.Array2=[];
if(month==0||month==2||month==4||month==6||month==7||month==9||month==11){
$rootScope.Array1=[];

for(var i=1;i<32;i++){
$rootScope.Array1.push({
date:i
})
}

}
else if(month==1 && (year%400 == 0||year%4==0)){
$rootScope.Array1=[];

for(var i=1;i<30;i++){
$rootScope.Array1.push({
date:i
})
}
}
else if(month==1&& !(year%400 == 0||year%4==0)){
$rootScope.Array1=[];

for(var i=1;i<29;i++){
$rootScope.Array1.push({
date:i
})
}
}
else{
$rootScope.Array1=[];

for(var i=1;i<31;i++){
$rootScope.Array1.push({
date:i
});
}

}

}*/// end of if
   $scope.selected =function(userId,date){
  $rootScope.userData=[];
for(var i=0;i<$rootScope.arrayOfMembers.length;i++){
//alert($rootScope.arrayOfMembers.length)
//alert(userId)
//alert($rootScope.arrayOfMembers[i].list[0].login.userId);
if($rootScope.arrayOfMembers[i].list[0]!=null){
if($rootScope.arrayOfMembers[i].list[0].login.userId==userId){
//alert("userID"+$rootScope.arrayOfMembers[i].list[0].login.userId)
for(var j =0;j<$rootScope.arrayOfMembers[i].list.length;j++){
var date12 = new Date($rootScope.arrayOfMembers[i].list[j].availability_date)
var onlyDate1 = date12.getDate();
if(onlyDate1==date){
var date1 =  mySplit(date12,0)+" "+mySplit(date12,1) + " " +mySplit(date12,2) + " " + mySplit(date12,3);
var time = mySplit(date12,4)
$rootScope.userData.push({
"fname":$rootScope.arrayOfMembers[i].list[j].login.firstName,
"lname":$rootScope.arrayOfMembers[i].list[j].login.lastName,
"date":date1,
"time":time,
"duration":$rootScope.arrayOfMembers[i].list[j].duration
});
}
}
}
}
else{
continue;
}
}
  }
   mySplit = function(string, nb) {
   var array = string.toString().split(' ');
   return array[nb];
}
  
  if($scope.role=="admin"){
  }
  else{
  }
});