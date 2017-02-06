/*
 * package com.epam.login.controllers.
 */
package com.epam.login.controllers;
/*
 * All used Imports.
 */


import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.login.models.Competency;
import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.models.ProposeEvent;
import com.epam.login.service.PanelService;
import com.epam.login.vto.PanelAvailabilityVTO;
import com.epam.login.vto.ProposeEventVTO;
import com.google.gson.Gson;

@RestController
@RequestMapping("/panel")
public class PanelController {
	@Autowired
	PanelService panel;
	/**
	 * getCalendarDates.
	 * @param request request
	 * @param compe compe
	 * @return return
	 */
	@RequestMapping(value = "/calenderDates", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final String  getCalenderDates(HttpServletRequest request,@RequestBody Competency compe){
		 List<ProposeEventVTO> list = panel.getCalenderDates(request,compe.getCompetencyName());
		
		 if(list==null){
			 return null;
		 }
		 
		 else{
		String json = new Gson().toJson(list);
		return json;
		 }
		 
	}

	/**
	 *  getproposedEvents.
	 * @param request request
	 * @param proEve proEve
	 * @return return
	 */
	@RequestMapping(value = "/getProposedEvents", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final String  getproposedEvents(HttpServletRequest request,@RequestBody ProposeEvent proEve){
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = new Date();
		System.out.println(dateFormat.format(date)); //2014/08/06 15:59:48
			String currentTime = dateFormat.format(date);
			String comp = proEve.getCompetency().getCompetencyName();
			System.out.println(comp);
			System.out.println("In constructorr PRopose Time"+currentTime); 
			
			java.sql.Timestamp ts = java.sql.Timestamp.valueOf( currentTime ) ;
			SimpleDateFormat noMilliSecondsFormatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
			System.out.println("["+noMilliSecondsFormatter.format(ts));
			proEve.setProposeDateTime(Timestamp.valueOf(currentTime));
			System.out.println("In controller"+proEve.getProposeDateTime());
			
			  
		    List<ProposeEventVTO> list = panel.getProposedEvents(request,ts,comp);
		
		

		    String json = new Gson().toJson(list);
		return json;
	}
	/**
	 * insertAvailability.
	 * @param request request
	 * @param panAvail panAvail
	 * @throws ParseException ParseException
	 */
	@RequestMapping(value = "/insertAvailability", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final void  insertAvailability(HttpServletRequest request,@RequestBody PanelAvailabilityVTO panAvail) throws ParseException{
		List<PanelAvailabilityVTO> localEventNames=null;
    	localEventNames=panAvail.getEventNames();
          panel.insertAvailability(localEventNames,localEventNames.get(0).getLogin().getUserName());

    		
    }


	/**
	 * getOwnEvents.
	 * @param request request
	 * @param login login
	 * @return return
	 * @throws ParseException  ParseException
	 */
	@RequestMapping(value = "/getOwnEvents", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final String getOwnEvents(HttpServletRequest request,@RequestBody Login login) throws ParseException{
		System.out.println(login.getUserName());
		List<PanelAvailabilityVTO> list =	panel.getEvents(login.getUserName());
		String json = new Gson().toJson(list);
		return json;
		
	}
	
	@RequestMapping(value = "/cancelInterview", method = RequestMethod.POST,consumes="application/json")
	public @ResponseBody String cancelInterview(@RequestBody final PanelAvailability pavail ) {
	List<Integer> localAvailabilityIds = null;
		localAvailabilityIds = pavail.getAvailabilityIds();
	
		if (localAvailabilityIds  != null && localAvailabilityIds .size() > 0) {
			for (int availabilityId : localAvailabilityIds ) {
				
			panel.deleteInterview(availabilityId);
			}
		}
	
		return "{\"deleted\":\"deleted\"}";
		
		
	}
	/**
	 * @param pan
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getObject", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final String  getCalenderDates(@RequestBody PanelAvailabilityVTO pan) throws ParseException{
		
		
		int uid = pan.getLogin().getUserId();
		System.out.println("HI>....................."+uid);
		String date = pan.getAvailability_date();
		System.out.println(date);
		DateFormat dateFormat = new SimpleDateFormat("yyyy-MM");
		Date dated =dateFormat.parse(date);
		System.out.println("date"+dateFormat.format(dated));
		System.out.println(uid);
        //to convert Date to String, use format method of SimpleDateFormat class.
        String strDate = dateFormat.format(dated);
        System.out.println(strDate +"hey"+uid);
		 List<PanelAvailabilityVTO> list = panel.getCalenderDates1(uid,strDate);
		String json = new Gson().toJson(list);
		return json;
		
	}
	/**
	 * @param fromDate
	 * @param toDate
	 * @param pan
	 * @return
	 * @throws ParseException
	 */
	@RequestMapping(value = "/getObjectFromTo", method = RequestMethod.POST,produces={"application/json"})
	public @ResponseBody final String  getCalenderFromTo(@RequestParam (value="fromDate",required=false) String fromDate,@RequestParam(value="toDate",required=false) String toDate,@RequestBody PanelAvailabilityVTO pan) throws ParseException{
	String eid = pan.getLogin().getContact().getEmailId();
	System.out.println(eid);
	System.out.println(fromDate);
	System.out.println(toDate);
	int i=0;
	List<PanelAvailabilityVTO> list = panel.getCalenderFromTo(eid,fromDate,toDate);

	String json = new Gson().toJson(list);
	return json;
	
	}
	

	
	
}
