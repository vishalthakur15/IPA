/**
 * 
 */
package com.epam.login.controllers;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.epam.login.models.Competency;
import com.epam.login.service.CompetencyService;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LevelsVTO;
import com.google.gson.Gson;

/**
 * @author vThakur
 *
 */
/**
 * class that represents a controller.
 * LoginController
 * @return this is a class
 */
@RestController
@RequestMapping("/competency")
public class CompetencyController  {
	private static final String STRING= null;
	/*
	 * This is a public class LoginController
	 */
	
    @Autowired
    private CompetencyService competencyService;

    /**
	 * getCompetency This is get competency.
	 * 
	 * @return save the value in object
	 */
    @RequestMapping(value = "/get", method = RequestMethod.GET,produces="application/json")
    public final String getCompetency() {
    	List<CompetencyVTO> list = competencyService.getCompetency();
    	String json = new Gson().toJson(list);
    	return json;
    }
    /**
     * @param competency competency
     * @return return
     */
    @RequestMapping(value = "/getLevel", method = RequestMethod.POST,produces="application/json")
    public final String getLevels(@RequestBody final CompetencyVTO competency) {
	
    	List<LevelsVTO> list = competencyService.getLevels(competency.getCompetencyName());
    	String json = new Gson().toJson(list);
 	
    	return json;
    }
     
    /*
     * This is to get list.
      */
   
    
	/**
	 * This is the post method.
	 * 
	 * @param competency
	 *            get competency.
	 * @return competencyService.insertCompetency
	 */
    @RequestMapping(value = "/insert", method = RequestMethod.POST,consumes="application/json")
    public final String insertCompetency(@RequestBody final Competency competency) {
    	return competencyService.insertCompetency(competency.getCompetencyName());	
    }
        
        /*
         * This is to insert competency
          */
        
   
        
        /**
    	 * This is the post method.
    	 * 
    	 * @param competency
    	 *            get competency.
    	 * @return competencyService.removeCompetency
    	 */
    @RequestMapping(value = "/remove", method = RequestMethod.POST,consumes="application/json")
    public @ResponseBody String removeCompetency(@RequestBody final Competency competency){
    	List<String> localCompetencyNames=null;
    	localCompetencyNames=competency.getCompetencyNames();
       	if (localCompetencyNames != null && localCompetencyNames.size() > 0) {
    		for (String competencyName : localCompetencyNames) {
    		competencyService.removeCompetency(competencyName);
    		}

    		}

    		return "[{\"deleted\":\"deleted\"}]";
    }
}
