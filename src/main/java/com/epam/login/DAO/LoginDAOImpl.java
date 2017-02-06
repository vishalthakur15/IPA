package com.epam.login.DAO;

/*
 * These are all imports used.
 */
import java.io.Serializable;
/**
 * package includes class LoginDAOImpl.
 */
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.login.models.Competency;
import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;

import ch.qos.logback.core.net.SyslogOutputStream;

/**
 * LoginDAOImpl.
 * 
 * @author vthakur
 */
@Repository
@Transactional
public class LoginDAOImpl implements LoginDAO, Serializable {
	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 1069490702864158532L;
	/*
	 * LoginDAOImpl implements LoginDAO
	 */
	/**
	 * sessionFactory.This is a class.
	 */
	@Autowired
	private SessionFactory sessionFactory;

	// sessionFactory
	/**
	 * This is LoginDaoImpl. This is a default Constructor
	 */
	public LoginDAOImpl() {
		super();
		// calls main class
	}

	/**
	 * This is to getSession.
	 * 
	 * @return save the value in object
	 */
	private Session getSession() {
		return sessionFactory.getCurrentSession();
		// return sessionFactory
	}

	/**
	 * setSession.
	 * 
	 * @param sessionFactory
	 *            Session Factory save the value in object
	 */
	public final void setSession(final SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

	private static Login flag3;

	/**
	 * This is to getByUserName.
	 * 
	 * @param unad
	 *            UserName save the value in object
	 */
	@Override
	public final Login getByUserName(final String una) {
		Login login = null;
		String hql = ("from Login login where userName= '" + una + "' " +"and active=true");
		List<Login> list = getSession().createQuery(hql).list();
	
		if(list!=null && !list.isEmpty()){
			login = list.get(0);
			
		}
		else{
			login = null;
			System.out.println("In else daoImpl");
		}
		return login;
	}

	

	private static String flag4;

	/**
	 * This is to checkEmail.
	 * 
	 * @param userName
	 *            userName save the value in object
	 */
	@Override
	public final String checkEmail(final String userName) {

		String hql = ("select emailId from Contact where login.userName='" + userName + "'");
		// initialize String
		List list = getSession().createQuery(hql).list();
		if (list != null) {
			flag4 = (String) list.get(0);
		}

		return flag4;
	}

	private static Login flag5;

	/**
	 * updatePassword.
	 * 
	 * @param uname
	 *            password save the value in object
	 */
	@Override
	public final Login updatePassword(final String uname, final String password) {
		String hql = ("update Login login set login.encryptedKey ='" + password + "' where userName='" + uname + "' ");
		// initialize String
		int result = getSession().createQuery(hql).executeUpdate();
		if (result == 1) {
			String hql1 = ("from Login where  userName='" + uname + "' ");
			// initialize String
			List list = getSession().createQuery(hql1).list();
			if (!list.isEmpty()) {

				flag5 = (Login) list.get(0);
			}
			// initialize variable
		}
		return flag5;
		
	}


	
	/**
	 * This is to saveUserToken.
	 * 
	 * @param user
	 *            token user's token save the value in object
	 */
	private static final int THOUSAND = 1000;
	// initialize variable
	private static final int SIXTY = 60;
	// initialize variable
	private static final int TWEFOUR = 24;

	// initialize variable
	@Override
	public final int saveUserToken(final Login user, final String token) {
		String uname = user.getUserName();
		// initialize string

		Date today = new Date();
		// date function
		Date expiry1 = new Date(today.getTime() + (THOUSAND * SIXTY * SIXTY * TWEFOUR));
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH);

		String expiry = sdf.format(expiry1);
		// initialize string

		String hql = ("update Login login set login.token= '" + token + "'" + " where userName='" + uname + "'");
		// initialize string
		getSession().createQuery(hql).executeUpdate();
		String hql1 = ("update Login login set login.expiryDate ='" + expiry + "' " + " where userName='" + uname
				+ "'");
		// initialize string
		return getSession().createQuery(hql1).executeUpdate();

	}

	/**
	 * This is to updateFirstTimeUser.
	 * 
	 * @param userId
	 *            userId save the value in object
	 */
	@Override
	public final int updateFirstTimeUser(final int userId) {
		String hql = ("update Login login set login.firstTime=0 where userId='" + userId + "'");
		// initialize string
		return getSession().createQuery(hql).executeUpdate();

	}

	/**
	 * This is getPasswordToken.
	 * 
	 * @param token
	 *            token save the value in object
	 */
	@Override
	public final Login getPasswordToken(final String token) {
		String hql = ("from Login login where login.token='" + token + "'");
		// initialize string

		return (Login) getSession().createQuery(hql).list().get(0);

	}// end of loop

	@Override
	public List<Login> getUserNames(String cname){
		System.out.println("in Dao");
		String hql = ("from Login login where login.role.roleId=3 and login.active=1 and login.levels.competency.competencyName='"+ cname +"'"); 
		 List<Login> list = getSession().createQuery(hql).list();
		 if(list != null && !list.isEmpty())
	        {
	        	return list;
	        }
	        return null;
		
	
	}
	

	private static PanelAvailability flag8;
	/* (getInfoById)
	 * @see com.epam.login.DAO.LoginDAO#getInfoById(int)
	 */
	@Override
	public PanelAvailability getInfoById(int availabilityId) {
		System.out.println("in dao");
		String hql=("from PanelAvailability pavail  where pavail.availabilityId='" + availabilityId + "'");
		List<PanelAvailability>list=  getSession().createQuery(hql).list();
		
			if (list != null) {
			flag8 =  list.get(0);
		
		}
		return flag8;
	
	}

	private static Login flag7;
	/* (getuserInfo)
	 * @see com.epam.login.DAO.LoginDAO#getuserInfo(int)
	 */
	@Override
	public Login getuserInfo(int userid) {
		  System.out.println("in dao");
			String hql=("from Login login where login.userId='" + userid + "'");
			List<Login>list=  getSession().createQuery(hql).list();

			if (list != null) {
				flag7 = (Login) list.get(0);
				System.out.println(flag7);
			}
			return flag7;
		
	}
		
	


	/* (getLeadList)
	 * @see com.epam.login.DAO.LoginDAO#getLeadList(java.lang.String)
	 */
	@Override
	public List<Login> getLeadList(String name) {
	String hql=("from Login where levels.competency.competencyName='" + name + "' and role.roleId = 2 and active=1");
	List<Login>list=  getSession().createQuery(hql).list();
	return list;
	 
	}

	/* (getLeadCoordinatorList)
	 * @see com.epam.login.DAO.LoginDAO#getLeadCoordinatorList(java.lang.String)
	 */
	@Override
	public List<Login> getLeadCoordinatorList(String name) {
	String hql=("from Login where levels.competency.competencyName='" + name + "' and role.roleId = 4 and active=1");
	List<Login>list=  getSession().createQuery(hql).list();
	return list;
	}}// end of loop
	

