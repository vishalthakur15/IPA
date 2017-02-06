/*
 * com.epam.login.service
 */
package com.epam.login.service;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.NoSuchProviderException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Store;
import javax.mail.Transport;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.login.DAO.LoginDAO;
import com.epam.login.models.Competency;
import com.epam.login.models.Contact;
import com.epam.login.models.Levels;
import com.epam.login.models.Login;
import com.epam.login.models.PanelAvailability;
import com.epam.login.models.Roles;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.ContactVTO;
import com.epam.login.vto.LevelsVTO;
import com.epam.login.vto.LoginVTO;
import com.epam.login.vto.RolesVTO;
/*
 * These are the all imports used.
 */


/**
 * LoginServiceImpl.
 * @author gsawhney
 */
@Service
public class LoginServiceImpl implements LoginService {
/**
     * loginDAO.This is a class.
     */
    @Autowired
    private LoginDAO loginDAO;
    private static final int  NUMBER = 443;

    
    /**
     * validate.
     * @param userName 
     * @param encryptedKey
     * save the value in object
     */
   
    @Override
    public LoginVTO validate(final String userName,final  String encryptedKey) {
    String msg;
         Login login1= loginDAO.getByUserName(userName);
         LoginVTO vto = new LoginVTO();
         ContactVTO cvto = new ContactVTO();
         Contact ct = login1.getContact();
         RolesVTO rvto = new RolesVTO();
         Roles role = login1.getRole();
         LevelsVTO lvto = new LevelsVTO();
         Levels level = login1.getLevels();
         System.out.println(level.getCompetency().getCompetencyName());
         Competency competency = level.getCompetency();
         CompetencyVTO cmvto = new CompetencyVTO();
         if(login1!= null){
             if(login1.getEncryptedKey().equals(encryptedKey)) {
                 
            BeanUtils.copyProperties(login1, vto);
            BeanUtils.copyProperties(ct, cvto);
            vto.setContact(cvto);
             
            BeanUtils.copyProperties(role,rvto);
            vto.setRole(rvto);
            BeanUtils.copyProperties(level,lvto);
            vto.setLevels(lvto);
             
            BeanUtils.copyProperties(competency,cmvto);
            vto.getLevels().setCompetency(cmvto);
            return vto;
             
             }else{
                return null;
            }
         }
         else{
        return null;
         }
    }  
    /**
     * LoginServiceImpl.
     * This is the default constructor.
     */
    public LoginServiceImpl() {
        super();
    }
    
    /**
     * checkEmail.
     * @param userName the userName
     * @return string
     */
    public final String checkEmail(final String userName){
       return loginDAO.checkEmail(userName);
            
    }
  
    /**
     * findUserByUsername.
     * @param userName The user name.
     * @return Login
     */
@Override
public final  Login findUserByUsername(final String userName) {
	
	Login login= loginDAO.getByUserName(userName);
	return login;
	
}
/**
     * updatePassword.
     * @param uname 
     * @param password 
     * @return string
     */
public final String updatePassword(final String uname,final String password){
    Login user = loginDAO.updatePassword(uname, password);
    String msg;
    
        if(user!=null)
        {
        loginDAO.updateFirstTimeUser(user.getUserId());
        if(user.getFirstTime()){
        msg="{\"message\":\"Password has been set for first time user\"}";
        }
        else{
        msg="{\"message\":\"Your password has been set\"}";
        }
        }
        else
        {
        msg="{\"message\":\"Invalid User!\"}";
        }
        return msg;
    }  
    
    /**
     * createPasswordResetTokenForUser.
     * @param user user.
     * @param token the token.
     */
    public final void createPasswordResetTokenForUser(final Login user, final String token)
    {
    loginDAO.saveUserToken(user,token);
    }
   
    
    /**
     * constructResetTokenEmail.
     * @param appUrl appUrl.
     * @param locale locale.
     * @param token token.
     * @param user user.
     * @param email email.
     * @return string
     * @throws AddressException 
     * @throws MessagingException 
     */
    /* (non-Javadoc)
     * @see com.epam.login.service.LoginService#constructResetTokenEmail(java.lang.String, java.util.Locale, java.lang.String, com.epam.login.models.Login, java.lang.String)
     */
    public final String constructResetTokenEmail(final String appUrl,final Locale locale,final String token,final Login user,final String email) throws AddressException, MessagingException  {
    String host = "outlook.office365.com";
        Properties mailServerProperties = System.getProperties();
       
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.port", 587);
        mailServerProperties.put("mail.store.protocol", "imaps");
        mailServerProperties.put("Domain", "BUDAPEST/Auto_AGS_Projects");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
           Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setFrom(new InternetAddress("anjali_garg@epam.com"));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email, false));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new
        InternetAddress("vishal_thakur@epam.com"));
        generateMailMessage.setSubject("Reset Password Link!");
        String emailBody = appUrl + "/index.html#/forgotPassword/" + user.getUserName() +"/" + token;
        generateMailMessage.setText(emailBody);
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("owabud.epam.com", "anjali_garg@epam.com", "No1knows3%");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
return null; 
    }
    
    /**
     * getPasswordResetToken.
     * @param token the token.
     * @return Login
     */
    public final Login getPasswordResetToken(final String token){
    return loginDAO.getPasswordToken(token);
    
    }
    
    
    /**
     * getLoginDAO.
     * 
     * @return save the value in object
     */
/*public final  LoginDAO getLoginDAO() {
return loginDAO;
}
*//**
     * setLoginDAO.
     * @param loginDAO
     * save the value in object
     */
public final  void setLoginDAO( final LoginDAO loginDAO) {
this.loginDAO = loginDAO;
}

public List<LoginVTO> getUserNames(String cname){
List<Login> list= loginDAO.getUserNames(cname);
List<LoginVTO> uvto1 = new ArrayList<LoginVTO>();
Iterator itr = list.iterator();
int i =0;
while(itr.hasNext()){
LoginVTO uvto = new LoginVTO();
Login user = (Login) itr.next();
BeanUtils.copyProperties(user,uvto);
ContactVTO cntct = new ContactVTO();
cntct.setEmailId(user.getContact().getEmailId());
uvto.setContact(cntct);
  uvto1.add(i++,uvto);
  
}
return uvto1;
}

/* (constructCancelEmail)
 * @see com.epam.login.service.LoginService#constructCancelEmail(java.lang.String, java.util.Locale, java.lang.String, java.lang.String, com.epam.login.models.Login, com.epam.login.models.PanelAvailability)
 */
@Override
public String constructCancelEmail(String appUrl, Locale locale, String user, String email, Login userinfo, PanelAvailability  info) throws AddressException, MessagingException  {
System.out.println("in service");
String host = "outlook.office365.com";
        Properties mailServerProperties = System.getProperties();
       
        mailServerProperties.put("mail.smtp.auth", "true");
        mailServerProperties.put("mail.smtp.starttls.enable", "true");
        mailServerProperties.put("mail.smtp.port", 587);
        mailServerProperties.put("mail.store.protocol", "imaps");
        mailServerProperties.put("Domain", "BUDAPEST/Auto_AGS_Projects");
        System.out.println("Mail Server Properties have been setup successfully..");

        // Step2
        System.out.println("\n\n 2nd ===> get Mail Session..");
           Session getMailSession = Session.getDefaultInstance(mailServerProperties, null);
        MimeMessage generateMailMessage = new MimeMessage(getMailSession);
        generateMailMessage.setFrom(new InternetAddress("anjali_garg@epam.com"));
        generateMailMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(email, false));
        generateMailMessage.addRecipient(Message.RecipientType.CC, new
        InternetAddress("vishal_thakur@epam.com"));
        generateMailMessage.setSubject("Interview Cancelled");
System.out.println( userinfo.getUserName());
System.out.println(info.getDuration());
        // emailBody = userinfo.getUserName()+ " " + "has cancelled the interview" + " " + "planned on" + " " + info.getAvailability_date() + " " + "for" + " " + info.getDuration() +" " + "minutes";
String emailBody = userinfo.getUserName() + "(" + userinfo.getContact().getEmailId() + ")" + " " + "has cancelled the interview" + " " + "planned on" + " " 
        + info.getAvailability_date() + " " + "for" + " " + info.getDuration() +" " + "minutes" +".";         
generateMailMessage.setText(emailBody);
        System.out.println("Mail Session has been created successfully..");

        // Step3
        System.out.println("\n\n 3rd ===> Get Session and Send mail");
        Transport transport = getMailSession.getTransport("smtp");

        // Enter your correct gmail UserID and Password
        // if you have 2FA enabled then provide App Specific Password
        transport.connect("owabud.epam.com", "anjali_garg@epam.com", "No1knows3%");
        transport.sendMessage(generateMailMessage, generateMailMessage.getAllRecipients());
        transport.close();
return null; 
    }
/* (getInfoById)
 * @see com.epam.login.service.LoginService#getInfoById(int)
 */
@Override
public PanelAvailability getInfoById(int availabilityId) {
return loginDAO.getInfoById(availabilityId);
}
/* (getuserInfo)
 * @see com.epam.login.service.LoginService#getuserInfo(int)
 */
@Override
public Login getuserInfo(int userid) {
return loginDAO.getuserInfo(userid);
}

/* (getLeadPAnel)
 * @see com.epam.login.service.LoginService#getLeadPAnel(java.lang.String)
 */
@Override
public List<LoginVTO> getLeadPAnel(String name) {
List<Login> user = loginDAO.getLeadList(name);
   List<LoginVTO> vList = new ArrayList<LoginVTO>();
   if(user!=null){
   Iterator iterator = user.iterator();
   int i4=0;
   while(iterator.hasNext()){
   LoginVTO vlogin = new LoginVTO();
   Login login = (Login)iterator.next();
   ContactVTO cvto = new ContactVTO();
           Contact ct = login.getContact();
           RolesVTO rvto = new RolesVTO();
           Roles role = login.getRole();
           LevelsVTO lvto = new LevelsVTO();
           Levels level = login.getLevels();
           Competency competency = level.getCompetency();
           CompetencyVTO cmvto = new CompetencyVTO();
           
   BeanUtils.copyProperties(login,vlogin);
   BeanUtils.copyProperties(ct, cvto);
   vlogin.setContact(cvto);
   BeanUtils.copyProperties(role,rvto);
       vlogin.setRole(rvto);
       BeanUtils.copyProperties(level,lvto);
       vlogin.setLevels(lvto);
       BeanUtils.copyProperties(competency,cmvto);
       vlogin.getLevels().setCompetency(cmvto);
       vList.add(i4++,vlogin);
   }
   return vList;
 
}
   else{
   return null; 
   }
 }
/* (getLeadCoordinator)
 * @see com.epam.login.service.LoginService#getLeadCoordinator(java.lang.String)
 */
@Override
public List<LoginVTO> getLeadCoordinator(String name) {
List<Login> user = loginDAO.getLeadCoordinatorList(name);
   List<LoginVTO> vList = new ArrayList<LoginVTO>();
   if(user!=null){
   Iterator iterator = user.iterator();
   int i4=0;
   while(iterator.hasNext()){
   LoginVTO vlogin = new LoginVTO();
   Login login = (Login)iterator.next();
   ContactVTO cvto = new ContactVTO();
           Contact ct = login.getContact();
           RolesVTO rvto = new RolesVTO();
           Roles role = login.getRole();
           LevelsVTO lvto = new LevelsVTO();
           Levels level = login.getLevels();
           Competency competency = level.getCompetency();
           CompetencyVTO cmvto = new CompetencyVTO();
           
   BeanUtils.copyProperties(login,vlogin);
   BeanUtils.copyProperties(ct, cvto);
   vlogin.setContact(cvto);
   BeanUtils.copyProperties(role,rvto);
       vlogin.setRole(rvto);
       BeanUtils.copyProperties(level,lvto);
       vlogin.setLevels(lvto);
       BeanUtils.copyProperties(competency,cmvto);
       vlogin.getLevels().setCompetency(cmvto);
       vList.add(i4++,vlogin);
   }
   return vList;
 
}
   else{
   return null; 
   }
}


}
