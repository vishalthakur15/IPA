/**
 * 
 */
package com.epam.login.DAO;
/*
 * These are all imports used.
 */
import java.util.List;

import com.epam.login.models.Competency;
import com.epam.login.models.Levels;

/**
 * @author vThakur
 *
 */

/*
 * CompetencyDAO interface
 */

public interface CompetencyDao {
	String COMPETENCYID= null;
	/**
	 * @return return.
	 */
	public  List<Competency> getCompetency();
	 /* This is a List method
	  */
	
	/**
	 * GetByCompetencyName DAO.
	 * @param competencyName competencyName
	 * @return return
	 */
	public  String insertCompetency(String competencyName);
	 /* This is a insert method
	  */
	
	/**
	 * GetByCompetencyName DAO.
	 * @param competencyName remove
	 * @return return
	 */
	public String removeCompetency(String competencyName);
	
	 /* This is a remove method
	  */
	 
	/**
	 * @param cname cname
	 * @return return
	 */
	public List<Levels> getLevels(String cname);
	
	
	
}
