var proxy = window.location.pathname;
angular.module('PanelMember')
.controller('PanelMemberController',
		['$http','$scope','$filter', 'listEvents','myService','$location','$rootScope','AddAvailList','CancelInterview','CancelInterviewMail','listEvents','filterFilter','localStorageService',
	        function ($http,$scope, $filter, listEvents, myService,$location, $rootScope, AddAvailList ,CancelInterview,CancelInterviewMail,listEvents,filterFilter, localStorageService) {
			$rootScope.EventsDate1=[];
			$rootScope.ProposeDate1=[];
			$scope.idlist4=[];
			$scope.idlist5=[];
		
			$scope.selectTime = function(timeAA){
				$scope.timeA=timeAA;
			}
			
			$rootScope.user=localStorageService.get('username');
			$scope.hour="Select Hour";
	        $scope.minute="Select Minute";
	        $scope.timeA="Select Time";
			$scope.time;
			var EventsDate = localStorageService.get('datas1');
			var ProposeDate= localStorageService.get('datas2');
			for(var i=0;i<ProposeDate.length;i++){
				if(ProposeDate[i].duration>60)
				{
					var hours = Math.trunc(ProposeDate[i].duration/60);
					var minutes = ProposeDate[i].duration % 60;
					ProposeDate[i].duration = hours +"hrs "+ minutes+"mins";
				}
				else
				{
					ProposeDate[i].duration = ProposeDate[i].duration+"mins";
				}
			$rootScope.ProposeDate1.push(ProposeDate[i]);
			}
			for(var i=0;i<EventsDate.length;i++){
				if(EventsDate[i].duration>60)
				{
				var hours = Math.trunc(EventsDate[i].duration/60);
				  var minutes = EventsDate[i].duration % 60;
				  EventsDate[i].duration = hours +"hrs "+ minutes+"mins";
				}
				else
				{
					EventsDate[i].duration = EventsDate[i].duration+"mins";
				}
				$rootScope.EventsDate1.push(EventsDate[i]);
			}
			$rootScope.hour1;
	$scope.minute1;
	$rootScope.dateSelected;
	$rootScope.home = myService.sharedObject.data;
	var calDate=localStorageService.get('datas');
	$rootScope.selectedDate=[];
	$scope.selectDate1=function(date){
		$rootScope.date1=date;
	}
	$scope.selectHour=function(hour){
		$rootScope.hour1=hour;
	}
	$scope.selectMinute=function(minute){
		$rootScope.minute1=minute;
	}
	$scope.selectDate=function(dt){
		
		$rootScope.dateSelected = new Date(dt);
		var d = new Date(dt);
		$rootScope.selectedDate=[];
		for(var i=0;i<calDate.length;i++){
			var d1=new Date(calDate[i].proposeDateTime);
			var da = d.getDate();
			var m=d.getMonth();
			var m1=d1.getMonth();
			var y=d.getFullYear();
			var y1=d1.getFullYear();
			var da1=d1.getDate();
			var dummy = calDate[i].duration;
			//alert(dummy)
			//calDate[i].duration = dummy;
		if(da===da1 && m===m1 && y===y1)
		{
			//calDate[i].duration =  (parseInt(hours) * 60) + parseInt(minutes);
			
			if(dummy>60)
			{
			 var hours = Math.trunc(dummy/60);
			 var minutes =dummy % 60;
			 dummy = hours +"hrs "+ minutes+"mins";
			 //alert(dummy);
			}
			else
			{
				var hours = 0;
				var minutes = dummy%60;
				dummy= dummy+"mins";
				//alert(dummy);
			}
			$rootScope.selectedDate.push({
				description:calDate[i].description,
					proposeDateTime:calDate[i].proposeDateTime,
						duration:dummy
			});
			
		}
	}
		if($rootScope.selectedDate !="" && $rootScope.selectedDate !=null)
			{
			$scope.status="success";
			}
		else{
			$scope.status="failure";
			//alert("No Interview Proposed On This Date")
		}
}
	 $scope.today = function() {
		    $scope.dt = new Date();
		  };
		  $scope.today();

		  $scope.clear = function() {
		    $scope.dt = null;
		  }; 
		  
		  
		 $rootScope.AddtoList = [];
		 $rootScope.AddInList=[];
		$scope.getAdd=function(){
			
			if($scope.timeA=="Select Time")
	        {
	        	return alert("Please select Time");
	        }
			if($scope.hour=="Select Hour")
	        {
	        	return alert("Please select Hour");
	        }
	        if($scope.minute=="Select Minute")
	        {
	            return alert("Please select Minutes");
	        }
			
			//alert($rootScope.dateSelected);
			var d1=new Date($rootScope.dateSelected);
			var da = d1.getDate();
			var m=parseInt(d1.getMonth())+1;
			var y=d1.getFullYear();
			var cdate1=new Date($rootScope.date1);
			function addZero(x,n) {
			    while (x.toString().length < n) {
			        x = "0" + x;
			    }
			    return x;
			}
		if(isNaN(da))
		{
			alert("No selection.Please select a date!")
		}
		else{
			var dateTime = y+"-"+m+"-"+da+" "+$scope.timeA;
			var duration1 = (parseInt($rootScope.hour1) * 60) +parseInt($rootScope.minute1);
			var dura;
			if(duration1>60)
			{
				var hours = Math.trunc(duration1/60);
				var minutes = duration1 % 60;
				dura = hours +"hrs "+ minutes+"mins";
			}
			else
			{
				dura = duration1+"mins";
			}
			 $rootScope.AddtoList.push({ 
		            
				 availability_date:dateTime,
				 duration:duration1,
				 login:{
					 userName:$scope.user
				 }
		    });
			 $rootScope.AddInList.push({ 
		            
				 availability_date:dateTime,
				 duration:duration1,
				 login:{
					 userName:$scope.user
				 }
		    });
			 alert("Added To List")
		}
		 $scope.add =function(){
			
			  AddAvailList.add1($rootScope.AddtoList,function(response){
				  alert("Availability Saved")
				
				    var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
				       
			            $http.post(ipaProxyConstant+proxy+'panel/getOwnEvents', { userName: $scope.user})
			            .success(function (response) {
			            	$rootScope.EventsDate1 = response;
			            	for(var i=0;i<$rootScope.EventsDate1.length;i++){
			    				if($rootScope.EventsDate1[i].duration>60)
			    				{
			    				  var hours = Math.trunc($rootScope.EventsDate1[i].duration/60);
			    				  var minutes = $rootScope.EventsDate1[i].duration % 60;
			    				  $rootScope.EventsDate1[i].duration = hours +"hrs "+ minutes+"mins";
			    				}
			    				else
			    				{
			    					$rootScope.EventsDate1[i].duration = $rootScope.EventsDate1[i].duration+"mins";
			    				}
			    			}
			            	//$rootScope.AddtoList=[];
			            })
				    
				  
				  
				  
				 $rootScope.AddtoList=[];
				 $rootScope.AddInList=[];
				  $location.path('/PanelLogin');
			
			  })
			  
		 }
		  $scope.hour="Select Hour";
	      $scope.minute="Select Minute"
	   	  $scope.timeA="Select Time";
	      
	  }
		
					   var ipaProxyConstant = window.location.protocol /*+ "//" + window.location.host*/;
				       
			            $http.post(ipaProxyConstant+proxy+'panel/getOwnEvents', { userName: $scope.user})
			            .success(function (response) {
			            	$rootScope.EventsDate1 = response;
			            	for(var i=0;i<$rootScope.EventsDate1.length;i++){
			    				if($rootScope.EventsDate1[i].duration>60)
			    				{
			    				  var hours = Math.trunc($rootScope.EventsDate1[i].duration/60);
			    				  var minutes = $rootScope.EventsDate1[i].duration % 60;
			    				  $rootScope.EventsDate1[i].duration = hours +"hrs "+ minutes+"mins";
			    				}
			    				else
			    				{
			    					$rootScope.EventsDate1[i].duration = $rootScope.EventsDate1[i].duration+"mins";
			    				}
			    			}
			            	//$rootScope.AddtoList=[];
			            })
				    
			 
	
	$scope.events=[];
  $scope.today = function() {
    $scope.dt = new Date();
  };
  $scope.today();

  $scope.clear = function() {
    $scope.dt = null;
  };

  $scope.options = {
    customClass: getDayClass,
    minDate: new Date(),
    showWeeks: true
  };



  $scope.toggleMin = function() {
    $scope.options.minDate = $scope.options.minDate ? null : new Date();
  };

  $scope.toggleMin();

  $scope.setDate = function(year, month, day) {
    $scope.dt = new Date(year, month, day);
  };
  

for(var i=0;i<calDate.length;i++){
	 var cDate =  new Date(calDate[i].proposeDateTime);
	 $scope.events.push({
		 date:cDate,
		 status:'full'
	 })
	}


  function getDayClass(data) {
    var date = data.date,
      mode = data.mode;
    if (mode === 'day') {
      var dayToCheck = new Date(date).setHours(0,0,0,0);

      for (var i = 0; i < $scope.events.length; i++) {
        var currentDay = new Date($scope.events[i].date).setHours(0,0,0,0);

        if (dayToCheck === currentDay) {
          return $scope.events[i].status;
        }
      }
    }

    return '';
  }
  
$scope.removeInterview=function(){
	
	$rootScope.AddInList = filterFilter($rootScope.AddInList,function(List){
   // alert("in function");
      if(List.selected)
    	{
    	  $scope.idlist4.push(List.availability_date,List.duration);
    	}
      return !List.selected;
  
	})
    $scope.isDisabled = true;
	$rootScope.AddtoList=$scope.AddInList;
	$scope.idlist4.clear();
	
}
$scope.isDisabled = true;
$scope.remove=function ()
{
	   var count = 0;
	    angular.forEach($scope.AddInList, function(List){
	        if (List.selected) count++;
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


$scope.cancel=function ()
{
	   var count = 0;
	    angular.forEach($scope.EventsDate1, function(item){
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
  

/*Cancel Own Interview*/      
$scope.cancelSelected=function(){

	$rootScope.EventsDate1 = filterFilter($rootScope.EventsDate1,function(item){
				if(item.selected){
			$scope.idlist5.push(item.availabilityId)
		}
		return !item.selected;
		
	})
	
	
availabilityIds = $scope.idlist5;
	CancelInterview.cancelInterview(availabilityIds,function(response){
		 $scope.isDisabled = true;
		 $scope.getEvents();
		// alert("Interview cancelled!")
	
			  
	})
	
	   CancelInterviewMail.cancelInterviewMail($scope.user,availabilityIds, function (response) {
                	$rootScope.userObject = response;
                	alert("Interview Cancelled!")
	   })
	
	
}


}]);