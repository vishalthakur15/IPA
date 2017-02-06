/*
 * package com.epam.login.DAO;
 */
package com.epam.login.DAO;

import java.sql.Timestamp;
import java.util.Iterator;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.login.models.Competency;
import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.models.ProposeEvent;

/**
 *  panelDAOImpl implements PanelDAO.
 * @author anjali.garg
 */
@Repository
@Transactional
public class panelDAOImpl implements PanelDAO{
	/*
	 * PanelDAOImpl implements PanelDAO
	 */
	/**
	 *  sessionFactory.This is a class.
	 */
	@Autowired
	private SessionFactory sessionFactory;
	/* List<ProposeEvent> getCalnderDates.
	 * @see com.epam.login.DAO.PanelDAO#getCalnderDates(java.lang.String)
	 */
	@Override
	public List<ProposeEvent> getCalnderDates(String name) {
		List<ProposeEvent> proposeList;
		String hql = ("from Competency where competencyName= '" + name + "' ");
        List<Competency> list = getSession().createQuery(hql).list();
        Competency competency = list.get(0);
        int compId = competency.getCompetencyId();
        String hql1 = ("from ProposeEvent where competency.competencyId='"+compId+"' ");
        List<ProposeEvent> propose = getSession().createQuery(hql1).list();
        if(propose!=null && !propose.isEmpty())
        {
        	proposeList = propose;
        }
        else{
        	proposeList = null;
        }
        return proposeList;
	}
	/*List<ProposeEvent> getProposedEvents.
	 * @see com.epam.login.DAO.PanelDAO#getProposedEvents(java.sql.Timestamp, java.lang.String)
	 */
	@Override
	public List<ProposeEvent> getProposedEvents(Timestamp date,String comp){
		List<ProposeEvent> proposedList;
		
		String hql = (" from  ProposeEvent where proposeDateTime>='"+ date+"' and competency.competencyName='"+ comp+"'");
		List<ProposeEvent> list = getSession().createQuery(hql).list();
		//Check for list emptiness
        
        if(list!=null && !list.isEmpty())
        {
        	ProposeEvent proposedEvent = list.get(0);
            String desc = proposedEvent.getDescription();
            Timestamp dateTime = proposedEvent.getProposeDateTime();
            int duration = proposedEvent.getDuration();
        	proposedList = list;
        }
        else{
        	proposedList = null;
        }
        return proposedList;
		
	}
	/* insertAvailability.
	 * @see com.epam.login.DAO.PanelDAO#insertAvailability(java.util.List, java.lang.String)
	 */
	@Override
	public void insertAvailability(List<PanelAvailability> panAvail,String una) {
		Session session = getSession();
		
		Iterator itr = panAvail.iterator();
		int i =0;
		while(itr.hasNext()){
			PanelAvailability pa = (PanelAvailability) itr.next();
		String hql = ("from Login login where userName= '" + una+ "' ");
        List<Login> list1 = getSession().createQuery(hql).list();
        Login login1 = list1.get(0);
        int id = login1.getUserId();
        pa.setStatus(true);
        pa.setLogin(login1);
   
		Object obj = session.save(pa);
		}
		
  }
	/*Remove Event.
	 * @see com.epam.login.DAO.PanelDAO#removeEvent(java.lang.String)
	 */
	@Override
	public final String removeEvent(final String eventName) {
		String hql=(" update PanelAvailability panelAvail set panelAvail.status=0 where panelAvail.availability_date='"+eventName+"'");
		int result = getSession().createQuery(hql).executeUpdate();
		return "result";
	}
	/**
	 * return.
	 * @return return
	 */
	private   Session getSession() {
        return sessionFactory.getCurrentSession();
    }

    /**
     * sessionFactory.
     * @param sessionFactory sessionFactory
     */
    public   void setSession(  SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
	/*getEvents.
	 * @see com.epam.login.DAO.PanelDAO#getEvents(java.lang.String)
	 */
	@Override
	public List<PanelAvailability> getEvents(String name) {
		List<PanelAvailability> pList =null;
		String hql = ("from PanelAvailability where login.userName='" + name + "' and status =true");
		List<PanelAvailability> list =  getSession().createQuery(hql).list();
		 if(list!=null && !list.isEmpty())
	        {
	        	pList = list;
	        }
	        else{
	        	pList = null;
	        }
	        return pList;
	}
	
	/* (delete)
	 * @see com.epam.login.DAO.PanelDAO#delete(int)
	 */
	public String delete(int id){
		System.out.println(id);
    	String hql = ("update PanelAvailability pavail  set pavail.status = false where pavail.availabilityId =" + id + "");
    	//initializing  string
		int result = getSession().createQuery(hql).executeUpdate();
		
		return "res";
		
	
	}
	
	/* (getCalenderDates1)
	 * @see com.epam.login.DAO.PanelDAO#getCalenderDates1(int, java.lang.String)
	 */
	@Override
	public List<PanelAvailability> getCalenderDates1(int uid,String date){
		List<PanelAvailability> plist;
		String hql = ("from PanelAvailability where substring(availability_date,1,7)= '" + date + "' and login.userId='"+ uid+"' and status=1 ");
        List<PanelAvailability> list = getSession().createQuery(hql).list();
   
        if(list!=null && !list.isEmpty())
        {
        	plist = list;
        }
        else{
        	plist = null;
        }
        return plist;
	}
	/* (getCalenderFromTo)
	 * @see com.epam.login.DAO.PanelDAO#getCalenderFromTo(java.lang.String, java.lang.String, java.lang.String)
	 */
	@Override
	public List<PanelAvailability> getCalenderFromTo(String eid, String fromDate, String toDate) {
	List<PanelAvailability> plist;
	String hql = ("from PanelAvailability where substring(availability_date,1,10) between '"+fromDate+"' AND '"+toDate+"' and login.contact.emailId='"+ eid+"' and status=1 ");

	List<PanelAvailability> list = getSession().createQuery(hql).list();
	
	        if(list!=null && !list.isEmpty())
	        {
	        plist = list;
	        }
	        else{
	        plist = null;
	        }
	        return plist;
	}
}