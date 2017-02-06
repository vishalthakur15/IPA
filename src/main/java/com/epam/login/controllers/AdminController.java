/*
 * com.epam.login.controllers.
 */
package com.epam.login.controllers;

/*
 * These are all imports used.
 */
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;


import com.epam.login.models.Login;
import com.epam.login.models.ProposeEvent;
import com.epam.login.service.AdminService;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LoginVTO;
import com.google.gson.Gson;

/**
 * Admin Controller Class.
 * @author vThakur
 */


/*
 * This is Rest Controller.
 */
@RestController
@RequestMapping("/admin")
/*
 * This is admin controller class
 */

public class AdminController {
	private static final int ONE = 1;
	/*
	 * Magic Number.
	 */
	private static final int TWO = 2;
	/*
	 * Magic Number.
	 */
	private static final int THREE = 3;
	/*
	 * Magic Number.
	 */
	private static final int FOUR = 4;
	/*
	 * Magic Number.
	 */
	private static final int FIVE = 5;
	/*
	 * Magic Number.
	 */
	private static final int SIX = 6;
	/*
	 * Magic Number.
	 */
	private static final int SEVEN = 7;
	/*
	 * Magic Number.
	 */
	private static final int EIGHT = 8;
	/*
	 * Magic Number.
	 */

	@Autowired
	private AdminService admin;

	/*
	 * AdminService admin.
	 * 
	 */
	
	/**
	 * This is to propose event.
	 * 
	 * @param event event
	 *            
	 * @return return return
	 */
	@RequestMapping(value = "/user", method = RequestMethod.POST)
	public String proposeEvent(@RequestBody final ProposeEvent event) {
		return admin.setproposeEvent(event);

	}

	/**
	 * This is to delete.
	 * 
	 * @param login
	 *            login
	 * @return return
	 */
	@RequestMapping(value = "/delete", method = RequestMethod.POST)
	public @ResponseBody String delete(@RequestBody final Login login) {
		List<String> localUserNames = null;
		localUserNames = login.getUserNames();
		if (localUserNames != null && localUserNames.size() > 0) {
			for (String userName : localUserNames) {
				admin.deleteUser(userName);
			}
		}
		return "{\"deleted\":\"deleted\"}";

	}

	/**
	 * This is to add user.
	 * 
	 * @param login
	 *            login
	 * @return return
	 */
	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public String addUser(@RequestBody final LoginVTO login) {
			String response = admin.addUser(login.getRole().getRoleName(),login.getContact().getEmailId(), login.getFirstName(), login.getLastName(),
				login.getLevels().getCompetency().getCompetencyName(), login.getContact().getContactNo(),
				login.getLevels().getLevelName());
		return response;
	}

	/**
	 * This is list panel.
	 * 
	 * @return return
	 */
	@RequestMapping(value = "/listPanel",method = RequestMethod.GET,produces={"application/json"})
public @ResponseBody String getPanelList() {
		List<LoginVTO> list = admin.getList();
		String json = new Gson().toJson(list);
		return json;
			
	}
	/**
	 * This is list panel.
	 * 
	 * @return return
	 */
	@RequestMapping(value = "/listPanelComp",method = RequestMethod.POST,produces={"application/json"})
public @ResponseBody String getPanelList(@RequestBody CompetencyVTO cmp) {
		System.out.println(cmp.getCompetencyName());
	
		String compy = cmp.getCompetencyName();
		List<LoginVTO> list = admin.getListComp(compy);
		
			String json = new Gson().toJson(list);
			return json;	
		
		
		
			
	}
	/**
	 * This is list lead.
	 * 
	 * @return return
	 */
@RequestMapping(value = "/listLead",method = RequestMethod.GET,produces={"application/json"})
public @ResponseBody String getLeadList() {
	List<LoginVTO> list = admin.getListLead();
	String json = new Gson().toJson(list);
	return json;
		
}
/**
 * This is list coordinator.
 * 
 * @return return
 */
@RequestMapping(value = "/listCoordinator",method = RequestMethod.GET,produces={"application/json"})
public @ResponseBody String getCoordinatorList() {
	List<LoginVTO> list = admin.getListCoordinator();
	String json = new Gson().toJson(list);
	return json;
		
}
/**
 * This is list resource.
 * 
 * @return return
 */
@RequestMapping(value = "/listResource",method = RequestMethod.GET,produces={"application/json"})
public @ResponseBody String getReourceList() {
	List<LoginVTO> list = admin.getListResource();
	String json = new Gson().toJson(list);
	return json;
		
}

}
