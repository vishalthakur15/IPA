package com.epam.login.DAO;
//imports login DAO
/**
 * package includes class LoginDAO.
 */

import java.util.ArrayList;
import java.util.List;

import com.epam.login.models.Login;
//imports login models
import com.epam.login.models.PanelAvailability;

/**
 * LoginDAO.
 * @author vthakur
 */

public interface LoginDAO {
	/*
	 * LoginDAO interface
	 */

	/**
	 * GetByUsername DAO.
	 * @param userName the userName
	 * @return return
	 */
	public Login getByUserName(String userName);
    /*
     * initialize getByUserName
     */
	/**
	 * Check Email DAO.
	 * @param userName the userName
	 * @return return
	 */
	public String checkEmail(String userName);
	//initialize checkEmail

	/**
	 * Update Password DAO.
	 * @param email the email
	 * @param password password
	 * @return return
	 */
	public Login updatePassword(String email, String password);
	//initialize updatePassword
	/**
	 * Update First Time User DAO.
	 * @param userId the userId
	 * @return return
	 */
	public int updateFirstTimeUser(int userId);
	//initialize updateFirstTimeUser
	/**
	 * @param token the token
	 * @return return
	 */
	public Login getPasswordToken(String token);
	//initialize getPasswordToken

	/**
	 * Save user token DAO.
	 * @param uname the uname
	 * @param token the token
	 * @return return
	 */
	int saveUserToken(Login uname, String token);
	/*
	 * This is a saveUserToken method
	 */
	/*
	 * Get user token DAO.
	 * @param cname the cname
	 * @return return
	 */
	public List<Login> getUserNames(String cname);
	/*
	 * Get user.
	 * @param cname the cname
	 * @return return
	 */
	public Login getuserInfo(int userid);
	/*
	 * Get info.
	 * @param availabilityId the availabilityId
	 * @return return
	 */
	public PanelAvailability getInfoById(int availabilityId);
	/*
	 * Get lead.
	 * @param name the name
	 * @return return
	 */
	public List<Login> getLeadList(String name);
	/*
	 * Get lead.
	 * @param name the name
	 * @return return
	 */
	public List<Login> getLeadCoordinatorList(String name);

}
