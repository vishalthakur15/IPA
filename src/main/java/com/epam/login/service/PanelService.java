/**
 * 
 */
package com.epam.login.service;
/*
 * All Imports.
 */

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import com.epam.login.vto.PanelAvailabilityVTO;
import com.epam.login.vto.ProposeEventVTO;

/**
 * interface PanelService.
 * @author anjali.garg
 */
public interface PanelService {
	
	/**
	 * getCalenderDates.
	 * @param request request
	 * @param name the name
	 * @return return
	 */
	List<ProposeEventVTO> getCalenderDates(HttpServletRequest request, String name);
	/**
	 * getProposedEvents.
	 * @param request request
	 * @param date date
	 * @param compet compet
	 * @return return
	 */
	List<ProposeEventVTO> getProposedEvents(HttpServletRequest request, Timestamp date,String compet);
	/**
	 * localEventNames.
	 * @param localEventNames localEventNames
	 * @param una una
	 */
	void insertAvailability(List<PanelAvailabilityVTO> localEventNames,String una);
	/**
	 * getEvents.
	 * @param name name
	 * @return return
	 * @throws ParseException throws ParseException
	 */
	List<PanelAvailabilityVTO> getEvents(String name) throws ParseException;
	/**
	 * removeEvent.
	 * @param eventName eventName.
	 * @return return
	 */
	public String removeEvent(String eventName);
	
	/**
	 * @param availabilityId
	 * @return
	 */
	public String deleteInterview(int availabilityId);
	 /**
	 * @param uid
	 * @param date
	 * @return
	 */
	List<PanelAvailabilityVTO> getCalenderDates1(int uid,String date);
     /**
     * @param eid
     * @param fromDate
     * @param toDate
     * @return
     */
    public List<PanelAvailabilityVTO> getCalenderFromTo(String eid, String fromDate, String toDate);
}
