/*
 * com.epam.login.DAO
 */
package com.epam.login.DAO;
/*
 * These are all imports used.
 */

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.transaction.Transactional;

import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.login.models.Competency;
import com.epam.login.models.Contact;
import com.epam.login.models.Levels;
import com.epam.login.models.Login;
import com.epam.login.models.ProposeEvent;
import com.epam.login.models.Roles;
/**
 *  AdminDAOImpl.
 * @author vThakur
 */
/*
 * AdminDAOImpl implements AdminDAO.
 */


@Repository
@Transactional
public class AdminDAOImpl implements AdminDAO {
	/*
	 * Magic Number.
	 */
	private static final int  FIVEHUNDRED = 500;
	/*
	 * Magic Number.
	 */
	private static final int  FOUR = 4;
	/*
	 * Magic Number.
	 */
	private static final int  SIX = 6;
	/*
	 * AdminDAOImpl implements AdminDAO
	 */
	/**
     * sessionFactory.This is a class.
     */
	@Autowired
	private SessionFactory sessionFactory;
	
	
	/* Thus is to set propose event.
	 * propose event.
	 * @see com.epam.login.DAO.AdminDAO#setproposeEvent(com.epam.login.models.ProposeEvent)
	 */
	/* (non-Javadoc)
	 * @see com.epam.login.DAO.AdminDAO#setproposeEvent(com.epam.login.models.ProposeEvent)
	 */
	@Override
	public String setproposeEvent(final ProposeEvent propose){
		Session session = getSession();
		String userName = propose.getLogin().getUserName();
		//initializing  string
		String competencyName = propose.getCompetency().getCompetencyName();
		//initializing  string
		
		String hql = ("from Login login where userName= '" + userName + "'");
		//initializing  string
        List<Login> list = getSession().createQuery(hql).list();
        Login login = list.get(0);
		ProposeEvent event = new ProposeEvent(propose);
		event.setLogin(login);
		String hql1 = ("from Competency competency where competencyName= '" + competencyName + "' ");
		//initializing  string
        List<Competency> list1 = getSession().createQuery(hql1).list();
        Competency competency= list1.get(0);
		event.setCompetency(competency);
		Object obj = session.save(event);
		if(obj!=null){
			return "passed";
		}
		else{
			return "fail";
	}
  }
	/*
	 * This is to get session.
	 * get session.
	 */
	/**
	 * @return return.
	 */
	private   Session getSession() {
        return sessionFactory.getCurrentSession();
    }
	/*
	 * This is to set session.
	 * Set Session.
	 */
    /**
     * @param sessionFactory sessionFactory 
     */
    public void setSession(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
  
    /* This is to get List
     * get list.
     * @see com.epam.login.DAO.AdminDAO#getList()
     */
    @Override
   	public final List<Login> getList() {
       	List<Login> list = new ArrayList<Login>();
   		Criteria criteria = getSession().createCriteria(Login.class)
   										.createAlias("role", "roles");
   		criteria.add(Restrictions.eq("active", Boolean.TRUE));
   		criteria.add(Restrictions.eq("roles.roleId", 2));
   		list = criteria.list();
   		return list;
   		//return null;
   	}
    /**
	 * This is to delete.
	 * @param una una.
	 * @return return return
	 */
  
    public String delete(final String una) {
    	String hql = ("update Login login set login.active = 0 where userName ='" + una + "'");
    	//initializing  string
		int result = getSession().createQuery(hql).executeUpdate();
		
		return "deleted";
	}
    /*
	 * This is to add user.
	 */
    
	/*Add user
	 * @see com.epam.login.DAO.AdminDAO#addUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
	 */
    @Override
	public String addUser(final String role,final String emailId,final String firstName,final String lastName,final String competencyName,
			final String contactNo,final String levelName) {
		String message = "";
		//initializing  string
		Session session = getSession();
		String hql00 = ("from Contact contact where emailId ='" + emailId + "'");
		//initializing  string
		List list00 = session.createQuery(hql00).list();
		
		if (list00.isEmpty()) {
			Contact ct = new Contact(emailId, contactNo);
			session.save(ct);
			Login login1 = new Login();
			login1.setContact(ct);
			login1.setFirstName(firstName);
			login1.setLastName(lastName);
			Random r = new Random();
			int rand = r.nextInt(500) + 1;
			String randstr = Integer.toString(rand);
			int length = firstName.length();
			if(length == 1)
			{
				
				login1.setUserName(firstName.substring(0, 1).concat("ee").concat(randstr));
			}
			else
			{
			
			login1.setUserName(firstName.substring(0, 2).concat(randstr));
			}
			
			int lengthOfRandomString = 6;

			String alphaNumericCharacters = "abcdefghijklmnopqrstuvwxyz" + "ABCDEFGHIJLMNOPQRSTUVWXYZ" + "1234567890";

			// Use StringBuilder in place of String to avoid unnecessary object
			// formation
			StringBuilder result = new StringBuilder();

			for (int i = 0; i < lengthOfRandomString; i++) {
				result.append(alphaNumericCharacters.charAt(r.nextInt(alphaNumericCharacters.length())));
			}
			
			login1.setEncryptedKey(result.toString());

			// -----------------------------------------------------------------------------------------
			
			String hql0 = ("from Contact contact where emailId ='" + emailId + "'");
			//initializing  string
			List list0 = getSession().createQuery(hql0).list();

			Contact contact = (Contact) list0.get(0);

			login1.setContact(contact);
	
			
			  String hql1 = ("from Roles roles where roleName ='"+role+"'"); 
			//initializing  string
			  List list1 = getSession().createQuery(hql1).list();
			  Roles roles= (Roles)list1.get(0); 
			  login1.setRole(roles);
			
			 
			
			String hql2 = ("from Levels levels where levelName ='" + levelName + "'");
			//initializing  string
			List list2 = getSession().createQuery(hql2).list();
			Levels level = (Levels) list2.get(0);
			String hql = ("from Competency competency where competencyName ='" + competencyName + "'");
			//initializing  string
			List list = getSession().createQuery(hql).list();
			Competency competency = (Competency) list.get(0);
		
			level.setCompetency(competency);
			login1.setLevels(level);
			login1.setFirstTime(true);
			login1.setActive(true);
			
			Object obj = session.save(login1);
			if (obj != null) {
				//session.getTransaction().commit();
				message = "User_added";
			} 
		} else {
			Contact contact0 = (Contact) list00.get(0);
			if(contact0.getLogin().getActive()==true)
					message = "User_already_exist";
			else{
				contact0.getLogin().setActive(true);
				message= "User_added";
			}
		}
		return message;
	

	}
    /* (getListLead)
     * @see com.epam.login.DAO.AdminDAO#getListLead()
     */
    @Override
	public List<Login> getListLead() {
		List<Login> list = new ArrayList<Login>();
		Criteria criteria = getSession().createCriteria(Login.class)
										.createAlias("role", "roles");
		criteria.add(Restrictions.eq("active", Boolean.TRUE));
		criteria.add(Restrictions.eq("roles.roleId", 3));
		list = criteria.list();
		return list;
	}
    /*
     * getListCoordinator.
     * @see com.epam.login.DAO.AdminDAO#getListCoordinator()
     */
	@Override
	public List<Login> getListCoordinator() {
		List<Login> list = new ArrayList<Login>();
		Criteria criteria = getSession().createCriteria(Login.class)
										.createAlias("role", "roles");
		criteria.add(Restrictions.eq("active", Boolean.TRUE));
		criteria.add(Restrictions.eq("roles.roleId", 4));
		list = criteria.list();
		return list;
	}
	/*
	 * getListResource.
	 * @see com.epam.login.DAO.AdminDAO#getListResource()
	 */
	@Override
	public List<Login> getListResource() {
		List<Login> list = new ArrayList<Login>();
		Criteria criteria = getSession().createCriteria(Login.class)
										.createAlias("role", "roles");
		criteria.add(Restrictions.eq("active", Boolean.TRUE));
		criteria.add(Restrictions.eq("roles.roleId", 5));
		list = criteria.list();
		return list;
	}
	/*
	 * getListComp.
	 * @see com.epam.login.DAO.AdminDAO#getListComp()
	 */
	@Override
	public List<Login> getListComp(String compy) {
		System.out.println(compy);
		String hql = ("from Login login where login.levels.competency.competencyName='" + compy + "'and login.role.roleId='" + 2 + "' and active=1");
		 List<Login> list = getSession().createQuery(hql).list();
		 if(list != null && !list.isEmpty())
	        {
	        	return list;       
	        }
	        return null;
		
	}

    
}