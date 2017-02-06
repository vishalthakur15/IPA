var proxy = window.location.pathname;
angular.module('TATeam')
.controller('Datepicker',function($scope,AvailList,localStorageService,$rootScope,$http,$q) {
$scope.Submit=function(dt){
	$scope.competencyName1 = localStorageService.get('competencyName');
	  $rootScope.user=localStorageService.get('username');

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
  $http.get(ipaProxyConstant+proxy+"admin/listPanel").success(function (data, status) { 
  $scope.PanelList=[];
  $scope.data = data;
      
       for(var i=0;i<$scope.data.length;i++){
       var id =$scope.data[i].userId; 
       /*for(var j=0;j<$scope.EventsDate1.length;j++){*/
     //  alert($scope.competency)
       if($scope.data[i].levels.competency.competencyName === $scope.competencyName1){
    	 //  alert($scope.competencyName1);
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

}// end of Submit
$scope.data3=localStorageService.get('datas');
var length =$scope.data3.length;
//alert($scope.data3[length-1].competencyName)
//$scope.data3[length-1].competencyName;
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
 $scope.EventsDate1 = localStorageService.get('datas1');
 
 $scope.PanelList=[];
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

  $rootScope.arrayOfMembers=[];
  var x=0;
  var dates= new Date($scope.dt);
  var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
     $http.get(ipaProxyConstant+proxy+"admin/listPanel").success(function (data, status) { 
    $scope.PanelList=[];
       $scope.data = data;
       for(var i=0;i<$scope.data.length;i++){
       var id =$scope.data[i].userId; 
       /*for(var j=0;j<$scope.EventsDate1.length;j++){*/
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

}/*
   $scope.hey=function(dt){
  
  $rootScope.arrayOfMembers=[];
  var x=0;
  var dates= new Date(dt);
  var ipaProxyConstant = window.location.protocol ;
  $http.get(ipaProxyConstant+proxy+"admin/listPanel").success(function (data, status) { 
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

}*/
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