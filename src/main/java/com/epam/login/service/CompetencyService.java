/**
 * 
 */
package com.epam.login.service;
/*
 * These are all imports used.
 */

import java.util.List;

import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LevelsVTO;

/**
 * @author vThakur
 *
 */

public interface CompetencyService {

	
	/**
	 * @param competencyName competencyName
	 * @return return
	 */
	public String insertCompetency(String competencyName);

	/**
	 * remove competency.
	 * @param competencyName competencyName  
	 * @return return
	 */
	public String removeCompetency(String competencyName);
	

	
	/**
	 * @return return return.
	 */
	public List<CompetencyVTO> getCompetency();
	
	/**
	 * @param cname cname
	 * @return return
	 */
	public List<LevelsVTO> getLevels(String cname);
	
}
