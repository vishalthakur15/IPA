/*
 * package  com.epam.login.service
 */
package com.epam.login.service;
/*
 * All used Imports.
 */

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.TimeZone;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.login.DAO.PanelDAO;
import com.epam.login.models.Competency;
import com.epam.login.models.Levels;
import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.models.ProposeEvent;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LevelsVTO;
import com.epam.login.vto.LoginVTO;
import com.epam.login.vto.PanelAvailabilityVTO;
import com.epam.login.vto.ProposeEventVTO;

/**
 * Panel Service Implementation.
 * @author anjali.garg
 */
@Service
public class PanelServiceImpl implements PanelService {
		@Autowired
		PanelDAO panel1;
	
		/*
		 * getCalenderDates
		 * @see com.epam.login.service.PanelService#getCalenderDates(javax.servlet.http.HttpServletRequest, java.lang.String)
		 */
		@Override
	public List<ProposeEventVTO> getCalenderDates(HttpServletRequest request,final String name) {
		
		Locale currentLocale = request.getLocale();
		System.out.println(currentLocale);
		TimeZone tzone = Calendar.getInstance().getTimeZone();
		Calendar cal2 = Calendar.getInstance(tzone,currentLocale);
		List<ProposeEvent> list = panel1.getCalnderDates(name);
		
		List<ProposeEventVTO> v_list = new ArrayList<ProposeEventVTO>();
		if(list!=null){
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			ProposeEventVTO v_event = new ProposeEventVTO();
			ProposeEvent    p_event = (ProposeEvent) itr.next();

			if(p_event.getProposeDateTime().getTime() - cal2.getTime().getTime()>0){
				System.out.println("In if");
				System.out.println("In service"+ p_event.getProposeDateTime().getTime());
				BeanUtils.copyProperties(p_event,v_event);
				v_list.add(i++, v_event);
			}
		}
		return v_list;
	}else{
		return v_list;
	}
		
		/*
		 * return v_list1
		 */
		
	/*
	 * Insert Availability.
	 * @see com.epam.login.service.PanelService#insertAvailability(java.util.List, java.lang.String)
	 */
}
	@Override
	public void insertAvailability(List<PanelAvailabilityVTO> panAvail,String una){
		List<PanelAvailability> v_list1 = new ArrayList<PanelAvailability>();
		Iterator itr = panAvail.iterator();
		int i =0;
		while(itr.hasNext()){
			PanelAvailability v_event = new PanelAvailability();
			PanelAvailabilityVTO    p_event = (PanelAvailabilityVTO) itr.next();
			BeanUtils.copyProperties(p_event,v_event);
			v_list1.add(i++, v_event);
		
		}
		panel1.insertAvailability(v_list1,una);
	}
	/*
	 * Remove Event.
	 * @see com.epam.login.service.PanelService#removeEvent(java.lang.String)
	 */
	@Override
	public String removeEvent(String eventName){
		return panel1.removeEvent(eventName);
	}
	
	/* getProposedEvents.
	 * @see com.epam.login.service.PanelService#getProposedEvents(javax.servlet.http.HttpServletRequest, java.sql.Timestamp, java.lang.String)
	 */
	@Override
	public List<ProposeEventVTO> getProposedEvents(HttpServletRequest request,Timestamp date, String compet) {
		System.out.println(compet);
		List<ProposeEvent> list = panel1.getProposedEvents(date,compet);
		List<ProposeEventVTO> v_list1 = new ArrayList<ProposeEventVTO>();
		if(list!=null){
		
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			ProposeEventVTO v_event = new ProposeEventVTO();
			ProposeEvent    p_event = (ProposeEvent) itr.next();
			System.out.println("In service"+ p_event.getProposeDateTime().getTime());
				BeanUtils.copyProperties(p_event,v_event);
				v_list1.add(i++, v_event);
		
		}
		return v_list1;
	}
		else{
			return v_list1;
		}

	/*
	 * return v_list1
	 */

	/* getEvents.
	 * @see com.epam.login.service.PanelService#getEvents(java.lang.String)
	 */
	}
	@Override
	public List<PanelAvailabilityVTO> getEvents(String name) throws ParseException {
		System.out.println(name);
		List<PanelAvailability>	list=panel1.getEvents(name);
		List<PanelAvailabilityVTO> v_list = new ArrayList<PanelAvailabilityVTO>();
		if(list!=null){
		
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			PanelAvailabilityVTO v_event = new PanelAvailabilityVTO();
			PanelAvailability    p_event = (PanelAvailability) itr.next();
			String pDate=        p_event.getAvailability_date();
			 DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			 Date ppDate = df2.parse(pDate);
			 System.out.println("DataBase Date"+ppDate);
			 Date cDate = new Date();
			 System.out.println("current Date"+ cDate);
			 if(ppDate.compareTo(cDate)>0){
				 BeanUtils.copyProperties(p_event,v_event);
				 v_list.add(i++,v_event);
			 }
			 else{
				 System.out.println("Out");
			 }
		}
		return v_list;
	}
		else{
			return v_list;
		}
	/*
	 * return v_list1
	 */
	}
	public String deleteInterview(int id)
	{
		return panel1.delete(id);
    	
	}
	@Override
	public List<PanelAvailabilityVTO> getCalenderDates1(int uid,String date){
	System.out.println(uid);
		List<PanelAvailability> list = panel1.getCalenderDates1(uid,date);
		if(list == null){
			List<PanelAvailabilityVTO> v_list = new ArrayList<PanelAvailabilityVTO>();
			return v_list;
		}
		else{			
		List<PanelAvailabilityVTO> v_list = new ArrayList<PanelAvailabilityVTO>();
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			PanelAvailabilityVTO v_event = new PanelAvailabilityVTO();
			PanelAvailability    p_event = (PanelAvailability) itr.next();
			Login login =p_event.getLogin();
			LoginVTO vlogin = new LoginVTO();
			
				BeanUtils.copyProperties(p_event,v_event);
				BeanUtils.copyProperties(login, vlogin);
				v_event.setLogin(vlogin);
				v_list.add(i++, v_event);
			
		}
		return v_list;
		}
	}

	/* (getCalenderFromTo)
	 * @see com.epam.login.service.PanelService#getCalenderFromTo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PanelAvailabilityVTO> getCalenderFromTo(String eid, String fromDate, String toDate) {
		System.out.println(eid);
		
		List<PanelAvailability> list = panel1.getCalenderFromTo(eid,fromDate,toDate);
		System.out.println("hi");
		if(list!=null)
		{
		List<PanelAvailabilityVTO> v_list = new ArrayList<PanelAvailabilityVTO>();
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			
		PanelAvailabilityVTO v_event = new PanelAvailabilityVTO();
		PanelAvailability    p_event = (PanelAvailability) itr.next();
		LoginVTO vlogin3 = new LoginVTO();
		Login login =p_event.getLogin();
		BeanUtils.copyProperties(login,vlogin3);
		CompetencyVTO vcompetency = new CompetencyVTO();
		Levels level =login.getLevels();
		 LevelsVTO lvto3 = new LevelsVTO();
		 BeanUtils.copyProperties(level,lvto3);
		 
		Competency competency =level.getCompetency();
		BeanUtils.copyProperties(competency,vcompetency);
		lvto3.setCompetency(vcompetency);
		
		vlogin3.setLevels(lvto3);
	
		BeanUtils.copyProperties(p_event,v_event);
		v_event.setLogin(vlogin3);
		v_list.add(i++, v_event);
		}
		return v_list;}
		else{
			return null;
		}
		}
	}

