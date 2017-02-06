/**
 * package com.epam.login.service.
 */
package com.epam.login.service;
/*
 * These are all imports used.
 */

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.epam.login.DAO.AdminDAO;
import com.epam.login.models.Competency;
import com.epam.login.models.Contact;
import com.epam.login.models.Levels;
import com.epam.login.models.Login;
import com.epam.login.models.ProposeEvent;
import com.epam.login.models.Roles;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.ContactVTO;
import com.epam.login.vto.LevelsVTO;
import com.epam.login.vto.LoginVTO;
import com.epam.login.vto.RolesVTO;

/**
 * @author vThakur
 *
 */

/*
 * AdminServiceImpl implements AdminService.
 */

@Service
public class AdminServiceImpl implements AdminService {
	/**
     * adminDAO.This is a class.
     */
	@Autowired
	private AdminDAO admin;
	/* AdminDao
	 * @see com.epam.login.service.AdminService#setproposeEvent(com.epam.login.models.ProposeEvent)
	 */
	 /**
     * setproposeevent.
     * @param propose propose
     * @return return
   */
	public String setproposeEvent(final ProposeEvent propose){
		//string decleration
		String other;
		//string response
    	String response= admin.setproposeEvent(propose);
    	//initializing  string
    	if("passed".equals(response)){
    		other="{\"message\":\"passed\"}";
    	}
    	else{
    		other="{\"message\":\"fail\"}";
    	}
		return other;
    }
	/*Delete user
	 * @see com.epam.login.service.AdminService#deleteUser(java.lang.String)
	 */
	 /**
     * deleteUser.
     * @param uname uname
     * @return return
   */
	public final String deleteUser(final String uname){
    	//String user
    	String user = admin.delete(uname);
    	//String redirectionLink
    	String redirectionLink = null;
         if("deleted".equals(user))
    	 {
     
        		redirectionLink =  "{\"message\":\"deleted\"}";
         }
    	else{
    		    redirectionLink="{\"message\":\"not_deleted\"}";
    	}
         return redirectionLink;
    } 
	/*
	 *  return redirectionLink
	 */
	
    /* List LoginVto.
     * @see com.epam.login.service.AdminService#getList()
     */
	 /**
	  * Get List.
	  * @return return
	  */
	public final List<LoginVTO> getList(){
    	
    	List<Login> user = admin.getList();
    	List<LoginVTO> vList = new ArrayList<LoginVTO>(); 
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
	/*
	 *  return vList.
	 */
   
    /* Add User
     * @see com.epam.login.service.AdminService#addUser(java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String, java.lang.String)
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
	 * @return return
	 */
	public final String addUser(final String role,final String emailId,final String firstName,final String lastName,final String competencyName,final String contactNo,
    		final String levelName){
    	String msg = null;
    
    String obj = admin.addUser(role,emailId,firstName,lastName,competencyName,contactNo,levelName);
    if("User_added".equalsIgnoreCase(obj))
    {
    	
    	msg = "{\"message\":\"User_added\"}";
    }
  
    else
    {
    	msg = "{\"message\":\"User_already_exist\"}";
    }
    return msg;
    }
	/* ListLoginVto.
	 * @see com.epam.login.service.AdminService#getListLead()
	 */
	@Override
	public List<LoginVTO> getListLead() {
		List<Login> user1 = admin.getListLead();
    	List<LoginVTO> vList1 = new ArrayList<LoginVTO>(); 
    	Iterator iterator = user1.iterator();
    	int i=0;
    	while(iterator.hasNext()){
    		LoginVTO vlogin1 = new LoginVTO();
    		Login login1 = (Login)iterator.next();
    		ContactVTO cvto1 = new ContactVTO();
            Contact ct1 = login1.getContact();
            RolesVTO rvto1 = new RolesVTO();
            Roles role1 = login1.getRole();
            LevelsVTO lvto1 = new LevelsVTO();
            Levels level1 = login1.getLevels();
            Competency competency1 = level1.getCompetency();
            CompetencyVTO cmvto1 = new CompetencyVTO();
            
    		BeanUtils.copyProperties(login1,vlogin1);
    		BeanUtils.copyProperties(ct1, cvto1);
    		vlogin1.setContact(cvto1);
    	
    		BeanUtils.copyProperties(role1,rvto1);
       	 	vlogin1.setRole(rvto1);
       	 	BeanUtils.copyProperties(level1,lvto1);
       	 	vlogin1.setLevels(lvto1);
       	 	BeanUtils.copyProperties(competency1,cmvto1);
       	 	vlogin1.getLevels().setCompetency(cmvto1);
       	 	vList1.add(i++,vlogin1);
    	}
    	return vList1;
	}
	/*
	 *  return vList1.
	 */
	
	/*
	 * List<LoginVTO>.
	 * @see com.epam.login.service.AdminService#getListCoordinator()
	 */
	@Override
	public List<LoginVTO> getListCoordinator() {
		List<Login> user2 = admin.getListCoordinator();
    	List<LoginVTO> vList2 = new ArrayList<LoginVTO>(); 
    	Iterator iterator = user2.iterator();
    	int i1=0;
    	while(iterator.hasNext()){
    		LoginVTO vlogin2 = new LoginVTO();
    		Login login2 = (Login)iterator.next();
    		ContactVTO cvto2 = new ContactVTO();
            Contact ct2 = login2.getContact();
            RolesVTO rvto2 = new RolesVTO();
            Roles role2 = login2.getRole();
            LevelsVTO lvto2 = new LevelsVTO();
            Levels level2 = login2.getLevels();
            Competency competency2 = level2.getCompetency();
            CompetencyVTO cmvto2 = new CompetencyVTO();
            
    		BeanUtils.copyProperties(login2,vlogin2);
    		BeanUtils.copyProperties(ct2, cvto2);
    		vlogin2.setContact(cvto2);
    		BeanUtils.copyProperties(role2,rvto2);
       	 	vlogin2.setRole(rvto2);
       	 	BeanUtils.copyProperties(level2,lvto2);
       	 	vlogin2.setLevels(lvto2);
       	 	BeanUtils.copyProperties(competency2,cmvto2);
       	 	vlogin2.getLevels().setCompetency(cmvto2);
       	 	vList2.add(i1++,vlogin2);
    	}
    	return vList2;
	}
	/*
	 *  return  vList2.
	 */
	
	/*  List<LoginVTO>.
	 * @see com.epam.login.service.AdminService#getListResource()
	 */
	@Override
	public List<LoginVTO> getListResource() {
		List<Login> user3 = admin.getListResource();
    	List<LoginVTO> vList3 = new ArrayList<LoginVTO>(); 
    	Iterator iterator = user3.iterator();
    	int i2=0;
    	while(iterator.hasNext()){
    		LoginVTO vlogin3 = new LoginVTO();
    		Login login3 = (Login)iterator.next();
    		ContactVTO cvto3 = new ContactVTO();
            Contact ct3 = login3.getContact();
            RolesVTO rvto3 = new RolesVTO();
            Roles role3 = login3.getRole();
            LevelsVTO lvto3 = new LevelsVTO();
            Levels level3 = login3.getLevels();
            Competency competency3 = level3.getCompetency();
            CompetencyVTO cmvto3 = new CompetencyVTO();
            
    		BeanUtils.copyProperties(login3,vlogin3);
    		BeanUtils.copyProperties(ct3, cvto3);
    		vlogin3.setContact(cvto3);
    		BeanUtils.copyProperties(role3,rvto3);
       	 	vlogin3.setRole(rvto3);
       	 	BeanUtils.copyProperties(level3,lvto3);
       	 	vlogin3.setLevels(lvto3);
       	 	BeanUtils.copyProperties(competency3,cmvto3);
       	 	vlogin3.getLevels().setCompetency(cmvto3);
       	 	vList3.add(i2++,vlogin3);
    	}
    	return vList3;
	}
	/*
	 * return vList3
	 */
	
	
	/*  List<LoginVTO>.
	 * @see com.epam.login.service.AdminService#getListComp()
	 */
	@Override
	public List<LoginVTO> getListComp(String compy) {
		System.out.println(compy);
		List<Login> user3 = admin.getListComp(compy);
    	List<LoginVTO> vList3 = new ArrayList<LoginVTO>(); 
    	Iterator iterator = user3.iterator();
    	int i2=0;
    	while(iterator.hasNext()){
    		LoginVTO vlogin3 = new LoginVTO();
    		Login login3 = (Login)iterator.next();
    		ContactVTO cvto3 = new ContactVTO();
            Contact ct3 = login3.getContact();
            RolesVTO rvto3 = new RolesVTO();
            Roles role3 = login3.getRole();
            LevelsVTO lvto3 = new LevelsVTO();
            Levels level3 = login3.getLevels();
            Competency competency3 = level3.getCompetency();
            CompetencyVTO cmvto3 = new CompetencyVTO();
            
    		BeanUtils.copyProperties(login3,vlogin3);
    		BeanUtils.copyProperties(ct3, cvto3);
    		vlogin3.setContact(cvto3);
    		BeanUtils.copyProperties(role3,rvto3);
       	 	vlogin3.setRole(rvto3);
       	 	BeanUtils.copyProperties(level3,lvto3);
       	 	vlogin3.setLevels(lvto3);
       	 	BeanUtils.copyProperties(competency3,cmvto3);
       	 	vlogin3.getLevels().setCompetency(cmvto3);
       	 	vList3.add(i2++,vlogin3);
    	}
    	return vList3;
	}
	
}



