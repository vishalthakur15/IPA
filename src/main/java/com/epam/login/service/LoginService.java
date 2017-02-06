package com.epam.login.service;
import java.util.List;
/**
 * package includes class LoginService .
 */
import java.util.Locale;

import javax.mail.MessagingException;
import javax.mail.internet.AddressException;

import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.vto.LoginVTO;
/*
 * Imports.
 */

/*
 * Interface Login Service.
 */
/**
 * LoginService.
 * @author gsawhney
 */

public interface LoginService {

    /**
     * @param userName the userName.
     * @param encryptedKey the encryptedKey.
     * @return return 
     */
    public LoginVTO validate(String userName, String encryptedKey);
    
    
    /**
     * @param string the string.
     * @return return
     */
    public String checkEmail(String string);
    
   
    /**
     * @param uname the uname.
     * @param password the password.
     * @return return
     */
    public String updatePassword(String uname,String password);

  
    /**
     * @param userName the userName.
     * @return return
     */
    public Login findUserByUsername(String userName);
	
   
	/**
	 * @param user the user.
	 * @param token the token.
	 */
	public void createPasswordResetTokenForUser(Login user, String token);
	
	
	/**
	 * @param appUrl the appUrl
	 * @param locale the locale
	 * @param token the token
	 * @param user the user
	 * @param email the email
	 * @return return
	 * @throws MessagingException 
	 */
	public String constructResetTokenEmail(String appUrl, Locale locale, String token, Login user, String email) throws MessagingException;
	
	
	/**
	 * @param token the token
	 * @return return
	 */
	public Login getPasswordResetToken(String token);


	/**
	 * constructCancelEmail.
	 * @param appUrl appUrl
	 * @param locale locale
	 * @param user  user
	 * @param email email
	 * @param info 
	 * @return
	 * @throws MessagingException 
	 * @throws AddressException 
	 */
	public String constructCancelEmail(String appUrl, Locale locale, String user, String email,Login userinfo, PanelAvailability  info) throws AddressException, MessagingException;


	/**
	 * @param cname
	 * @return
	 */
	public List<LoginVTO> getUserNames(String cname);



	
	/**
	 * @param availabilityId
	 * @return
	 */
	public PanelAvailability getInfoById(int availabilityId);


	/**
	 * @param userid
	 * @return
	 */
	public Login getuserInfo(int userid);





	/**
	 * @param name
	 * @return
	 */
	public List<LoginVTO> getLeadPAnel(String name);


	/**
	 * @param name
	 * @return
	 */
	public List<LoginVTO> getLeadCoordinator(String name);








}
