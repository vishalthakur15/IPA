/**
 * Package com.epam.login.DAO.
 */
package com.epam.login.DAO;

import java.sql.Timestamp;
import java.util.List;

import com.epam.login.models.PanelAvailability;
import com.epam.login.models.ProposeEvent;

/**
 * @author anjali.garg
 *Panel Dao.
 */
public interface PanelDAO {
	/**
	 * getCalendarDates.
	 * @param name name
	 * @return return
	 */
	public List<ProposeEvent> getCalnderDates(String name);
	/**
	 * getProposedEvents.
	 * @param date date
	 * @param compet compet
	 * @return return
	 */
	public List<ProposeEvent> getProposedEvents(Timestamp date, String compet);
	/**
	 * InsertAvailability.
	 * @param v_list1 v_list1
	 * @param una una
	 */
	public void insertAvailability(List<PanelAvailability> v_list1,String una);
	/**
	 *  getEvents.
	 * @param name name
	 * @return return
	 */
	public List<PanelAvailability> getEvents(String name);
	/**
	 * removeEvent.
	 * @param competencyName competencyName
	 * @return return
	 */
	public String removeEvent(String competencyName);
	
	/**
	 * delete.
	 * @param id id
	 * @return return
	 */
	public String delete(int id);
	/**
	 * getCalenderDates1.
	 * @param uid uid
	 * @param date date
	 * @return return
	 */
	public List<PanelAvailability> getCalenderDates1(int uid,String date);
	/**
	 * getCalenderFromTo.
	 * @param eid eid
	 * @param fromDate fromDate
	 * @param toDate toDate
	 * @return return
	 */
	public List<PanelAvailability> getCalenderFromTo(String eid, String fromDate, String toDate);
	
	
}
