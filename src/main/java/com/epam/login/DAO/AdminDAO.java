/**
 * package com.epam.login.DAO;

 */
package com.epam.login.DAO;

import java.util.List;

import com.epam.login.models.Login;
import com.epam.login.models.ProposeEvent;

/**
 * @author vThakur
 *
 */

/*
 * AdminDAO interface
 */

public interface AdminDAO {
	
	/**
	 * Set Propose Event.
	 * @param propose propose event
	 * @return return return
	 */
	public String setproposeEvent(ProposeEvent propose);

	
	/**
	 * GetBy Admin DAO.
	 * @param una una
	 * @return return
	 */
	
	public String delete(String una);
	
	/*
	 * This is to delete.
	 */
	
	/**
	 * Add User.
	 * @param role role
     * @param emailId email Id
	 * @param firstName first name
	 * @param lastName last name
	 * @param competencyName competency name
	 * @param contactNo contact no
	 * @param levelName level name
	 * @return return return
	 */
	public String addUser(String role,String emailId,String firstName,String lastName,String competencyName,String contactNo,String levelName);
	/*
	 * This is to add user.
	 */
		
	/**
	 * Get List.
	 * @return return
	 */
	
	/**
	 * @return return
	 */
	public List<Login> getList();
	/**
	 * @return return
	 */
	public List<Login> getListLead();
	/**
	 * @return return
	 */
	public List<Login> getListCoordinator();
	/**
	 * @return return
	 */
	public List<Login> getListResource();
	/**
	 * @return return
	 */
	public List<Login> getListComp(String compy);
	
	
	/*
	 * This is to get List.
	 
	 */
}
