/**
 * login package.
 */
package com.epam.login.service;
import java.util.List;


import com.epam.login.models.ProposeEvent;
import com.epam.login.vto.LoginVTO;
/*
 * Imports.
 */
/**
 * @author vThakur
 * Admin Service.
 */
/*
 * Interface Admin Service.
 */
public interface AdminService{
	/*
	 * This is to propose event.
	 */
	
	/**
	 * SetProposeEvent.
	 * @param propose propose
	 * @return return return
	 */
	
	public String setproposeEvent(ProposeEvent propose);
	/*
	 * This is to add user.
	 */
 /**
	 * add user.
	 * @param role role.
	 * @param emailId email
	 * @param firstName first name
	 * @param lastName last name
	 * @param competencyName competency name
	 * @param contactNo contact no
	 * @param levelName level name
	 * @return return
	 */
	
	public String addUser(String role,String emailId,String firstName,String lastName,String competencyName,String contactNo,
	    	String levelName);
	/*
	 * This is to delete user.
	 */
	/**
		 * Delete User.
		 * @param userName username
		 * @return return
		 */
	
		public String deleteUser(String userName);
		/*
		 * This is to get List.
		 */
		
		/**
		 * getList.
		 * @return return
		 */
	
		public List<LoginVTO> getList();


		/**
		 * getListLead.
		 * @return return
		 */
		public List<LoginVTO> getListLead();


		/**
		 * getListCoordinator.
		 * @return return
		 */
		public List<LoginVTO> getListCoordinator();


		/**
		 * getListResource.
		 * @return return
		 */
		public List<LoginVTO> getListResource();
		/**
		 * getListComp.
		 * @param compy compy
		 * @return return
		 */
		
		public List<LoginVTO> getListComp(String compy);
		
}
