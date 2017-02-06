var proxy = window.location.pathname;
angular.module('HelloApp', ["ng-fusioncharts"])
.controller('MyController', 
[  'myService','$scope', '$http','$rootScope','$location', 'Reports' ,'localStorageService',
                              function (myService , $scope , $http , $rootScope , $location,  Reports , localStorageService) {
    $scope.data1=[];
    $scope.reports=false;
    $scope.PanelData=[];
    $scope.PanelData1=[];
   // $rootScope.home = myService.sharedObject.data;
    $scope.competencyName11 = localStorageService.get('competencyName');
    $rootScope.user=localStorageService.get('username');
    var ipaProxyConstant = window.location.protocol
    $http.post(ipaProxyConstant+proxy+'admin/listPanelComp',
    		
    
    		{ competencyName:  $scope.competencyName11 }
    ).success(function (data, status) { 
        
        $scope.PanelData1 = data;
        console.log("Data"+$scope.PanelData1[0].contact.emailId);
        passV($scope.PanelData1);
    })
    function passV(panel1){
   $rootScope.Arrayy1=[];
   for(var i=0;i<panel1.length;i++){
   $rootScope.Arrayy1.push({
   eid1: panel1[i].contact.emailId
    })    }
   // alert($rootScope.Arrayy[0].eid)
    }
    var ipaProxyConstant = window.location.protocol ;
          $http.get(ipaProxyConstant+proxy+'admin/listPanel').success(function (data, status) { 
        
              $scope.PanelData = data;
              console.log("Data"+$scope.PanelData[0].contact.emailId);
              passValue($scope.PanelData);
          })
          function passValue(panel){
         $rootScope.Arrayy=[];
         for(var i=0;i<panel.length;i++){
         $rootScope.Arrayy.push({
         eid: panel[i].contact.emailId
          })
          }
         // alert($rootScope.Arrayy[0].eid)
          }
          $scope.sub
        
    $scope.submit1 = function () {
      var pdf = new jsPDF('p', 'pt', 'letter');

   pdf.cellInitialize();
   pdf.setFontSize(10);
   $.each( $('#reportsview tr'), function (i, row){
       $.each( $(row).find("td, th"), function(j, cell){
           var txt = $(cell).text().trim().split(" ").join("\n") || " ";
           var width = 90;
           pdf.cell(10, 100, width, 30, txt, i);
       });
   });
   pdf.save('sample-file.pdf');
   };
   
   $scope.competencyName;
   mySplit = function(string, nb) {
   var array = string.toString().split(' ');
   return array[nb];
}
   $scope.Submitt=function(dt,emailid)
   {
	   $rootScope.st;

	   //$scope.IsVisible = true;
	   for(var i=0;i< $scope.PanelData1.length;i++)
		   {
		   if( $scope.PanelData1[i].contact.emailId==emailid)
			   {
			   //alert("e")
			   
			   $scope.Submit(dt);
			 $scope.IsVisible = true;
			   }
		   else
			   {
			   //alert($scope.PanelData1[i].contact.emailId)
			   //alert(emailid)
			 
			   $rootScope.st='n';
			   $scope.category=[];
			   $scope.data=[];
			   graph($scope.category,$scope.data)
			   $scope.IsVisible = false;
			  
			   }
		   }
	  
   }
$scope.Submit = function(dates){
	$scope.IsVisible = true;
$rootScope.Array1=[];
$scope. month1 = mySplit(dates,1);
$scope.year1 = mySplit(dates,3)
var firstOfMonth = new Date(dates.getFullYear(),dates.getMonth(), 1); 
 
var weekday = new Array(7);
   weekday[0] = "Sunday";
   weekday[1] = "Monday";
   weekday[2] = "Tuesday";
   weekday[3] = "Wednesday";
   weekday[4] = "Thursday";
   weekday[5] = "Friday";
   weekday[6] = "Saturday";
var n = firstOfMonth.getDay(); 

var month = parseInt(dates.getMonth())+1;
var year = dates.getYear();
year = parseInt(year) +1900
month = parseInt(month)
if(month<10)
{
$rootScope.startDate=year+"-0"+month+"-01"
}
else
{
$rootScope.startDate=year+"-"+month+"-01"
}
if(month==1||month==3||month==5||month==7||month==8||month==10||month==12){
$rootScope.Array1=[];
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
if(month<10)
{
$rootScope.endDate=year+"-0"+month+"-31"
}
else
{
$rootScope.endDate=year+"-"+month+"-31"
}
//$rootScope.endDate=year+"-"+month+"-31"
}
else if(month==2 && (year%400 == 0||year%4==0)){
$rootScope.Array1=[];
for(var i=1;i<30;i++){
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
if(month<10)
{
$rootScope.endDate=year+"-0"+month+"-29"
}
else
{
$rootScope.endDate=year+"-"+month+"-29"
}
//$rootScope.endDate=year+"-"+month+"-29"
}
else if(month==2&& !(year%400 == 0||year%4==0)){
$rootScope.Array1=[];
if(month<10)
{
$rootScope.endDate=year+"-0"+month+"-28"
}
else
{
$rootScope.endDate=year+"-"+month+"-28"
}
//$rootScope.endDate=year+"-"+month+"-28"
for(var i=1;i<29;i++){
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
else{
$rootScope.Array1=[];
if(month<10)
{
$rootScope.endDate=year+"-0"+month+"-30"
}
else
{
$rootScope.endDate=year+"-"+month+"-30"
}
//$rootScope.endDate=year+"-"+month+"-30"
for(var i=1;i<31;i++){
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
Reports.getData($scope.emailid, $rootScope.startDate , $rootScope.endDate , function(response){
$scope.user = $scope.emailid
$scope.e = $scope.user.substring(0, $scope.user.indexOf('@'));
if(response==null)
{
	 $scope.reports=false;
$scope.data123=[];
$scope.category=[];
$scope.data=[];
graph($scope.category,$scope.data)
return alert("No Data Found!")
}
else
{
	 $scope.reports=true;
$scope.value=[];
$scope.compe = response[0].login.levels.competency.competencyName;
$scope.category=[];
$scope.data=[];
$scope.available=[];
$scope.available = response;
console.log("Avail"+$scope.available[0].availability_date)
for(var  i = 1;i<=$rootScope.Array1.length;i++){
var object1={
"label": ""+i+""
}
$scope.category.push(object1)
} 
for(var i =0;i<$rootScope.Array1.length;i++){
var duration = 0;
dateS = i+1;
var onlyDate
for(var j =0;j<$scope.available.length;j++){
var dateC=new Date($scope.available[j].availability_date);
onlyDate = dateC.getDate();
if(dateS==onlyDate){
duration = duration+parseInt($scope.available[j].duration)/60;
}
}
$scope.data = $scope.data.concat({
"date":i+1,
"value":duration
})
}
$scope.data123=[];
var n= $rootScope.Array1.length/2;
for(var i =0;i<$rootScope.Array1.length/2;i++){
var duration = 0;
dateS = i+1;
if(n% 1===0){
var onu = parseInt($rootScope.Array1.length/2);
$scope.data123.push ({
"day":$rootScope.Array1[i].day,
"date":i+1,
"value":$scope.data[i].value,
"day1":$rootScope.Array1[i+onu].day,
"date1":i+onu+1,
"value1":$scope.data[i+onu].value,
});
}
else{
var onu = parseInt($rootScope.Array1.length/2);
$scope.data123.push({
"day":$rootScope.Array1[i].day,
"date":i+1,
"value":$scope.data[i].value,
"day1":$rootScope.Array1[i+onu].day,
"date1":i+onu+1,
"value1":$scope.data[i+onu].value,
});
}
}
}
 
/* for(var z = 0;z<$scope.data.length;z++){
for(var y =0;y<$scope.available.length;y++){
var datee= new Date($scope.available[y].availability_date);
var datee1 = datee.getDate();
if($scope.data[z].date == datee1){
$scope.data[z].duration1.push({
"dura":$scope.available[y].availability_date,
"Hdura":parseInt($scope.available[y].duration)/60
})
}
}
}*/
graph($scope.category,$scope.data)
})
}
graph = function(category,data){
var categoryObj = {
"category" : category
};
var finalList = [];
finalList.push(categoryObj);
console.log(finalList);
var finalObj = {
"categories" : finalList
}
console.log(finalObj);
var listy = [];
listy.push(finalObj);
var valueObj={
"data":data
}
var finalList1=[];
finalList1.push(valueObj);
var finalObj1={
"dataset":finalList1
}
listy.push(finalObj1)
FusionCharts.ready(function () {
console.log("in fxn");
console.log(listy);
console.log(listy[0].categories);
console.log("listy[1]"+listy[1].dataset);
var revenueChart = new FusionCharts({
type: 'scrollColumn2d',
renderAt: 'chart-container',
width: 1250,
height: '350',
dataFormat: 'json',
dataSource: {
"chart": {
"caption": "Availability Report - "+$scope.emailid,
"subcaption": $scope.competencyName,
"xaxisname": "Dates",
"yaxisname": "Duration",
"placeValuesInside" : "1",
"usePlotGradientColor": "1",
"plotGradientColor": "#003366",
"palettecolors": "#3399FF",
"baseFontSize":"14",
"outCnvBaseFontSize": "11",
"palettecolors": "#3399FF",
"rotateValues": "1",
"valueFontColor" : "#ffffff",
"numberSuffix": "hrs",
"exportFileName": "Reports"+$scope.emailid+""+$scope.competencyName,
"exportEnabled": "1",
               //Configure scrollbar
"scrollShowButtons" : "1",
"scrollColor" : "#1aaf5d",
"scrollHeight" : "12",
"scrollPadding" : "2",
"theme" : "fint"
},
"categories":listy[0].categories,
"dataset": listy[1].dataset
}
});

revenueChart.render(); 
	
});

}
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
   openedYr: false
 };


}]);
