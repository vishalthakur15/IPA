 /**
 * 
 */
package com.epam.login.service;
import java.util.ArrayList;
import java.util.Iterator;
/*
 * These are all imports used.
 */
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.epam.login.DAO.CompetencyDao;
import com.epam.login.models.Competency;
import com.epam.login.models.Levels;
import com.epam.login.vto.CompetencyVTO;
import com.epam.login.vto.LevelsVTO;
/**
 * @author vThakur
 *
 */
//This is service.
@Service
public class CompetencyServiceImpl implements CompetencyService {
	/**
     * competencyDAO.This is a class.
     */
	 @Autowired
	    private CompetencyDao competencydao;
	
	 /**
	     * insert.
	     * @param competencyName competencyName
	     * @return return
	   */
	 
	 /*
	  * This is to insert competency
	  * @see com.epam.login.service.CompetencyService#insertCompetency(java.lang.String)
	  */
	@Override
	public final String insertCompetency(final String competencyName) {
			 String msg=null;
			//string decleration
		 String cmp= competencydao.insertCompetency(competencyName);	
		//string decleration
		 if("passes".equalsIgnoreCase(cmp))
		 {
		     msg="{\"message\":\"passes\"}";
		 }
		 else {
		     msg="{\"message\":\"already exist\"}";
		 }
       return msg;
   	//return message
	}
	 /*
	  * This is to remove competency
	  * @see com.epam.login.service.CompetencyService#insertCompetency(java.lang.String)
	  */
	
	 /**
     * remove.
     * @param competencyName competencyName
   */
	
	
	@Override
	public String removeCompetency(final String competencyName) {
	
		return competencydao.removeCompetency(competencyName);
		//return competencydao.removeCompetency
	}
	 /*
	  * This is to get competency
	  * @see com.epam.login.service.CompetencyService#insertCompetency(java.lang.String)
	  */
	
	 /**
     * get.
     * @param competencyName competencyName
   */
	
	/* (getCompetency)
	 * @see com.epam.login.service.CompetencyService#getCompetency()
	 */
	@Override
	public final List<CompetencyVTO> getCompetency() {
		List<Competency> comp= competencydao.getCompetency();
		List<CompetencyVTO> cvto1 = new ArrayList<CompetencyVTO>();
		Iterator itr = comp.iterator();
		int i =0;
		while(itr.hasNext()){
			CompetencyVTO cvto = new CompetencyVTO();
			Competency compy = (Competency) itr.next();
			BeanUtils.copyProperties(compy,cvto);
		   cvto1.add(i++,cvto);
		   
		}
		return cvto1;
	}

	/* (getLevels)
	 * @see com.epam.login.service.CompetencyService#getLevels(java.lang.String)
	 */
	@Override
	public List<LevelsVTO> getLevels(final String cname) {
		// TODO Auto-generated method stub
		List<Levels> list=competencydao.getLevels(cname);
		List<LevelsVTO> cvto1 = new ArrayList<LevelsVTO>();
		Iterator itr = list.iterator();
		int i =0;
		while(itr.hasNext()){
			LevelsVTO cvto = new LevelsVTO();
			Levels compy = (Levels) itr.next();
			BeanUtils.copyProperties(compy,cvto);
		   cvto1.add(i++,cvto);
		   
		}
		return cvto1;
	}

}
