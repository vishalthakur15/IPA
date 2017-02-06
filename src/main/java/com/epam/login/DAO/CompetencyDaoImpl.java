	/**
 * 
 */
package com.epam.login.DAO;
/*
 * These are all imports used.
 */
import java.io.Serializable;
import java.util.Collections;
import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.epam.login.models.Competency;
import com.epam.login.models.Levels;

/**
 *  CompetencyDAOImpl.
 * @author vThakur
 */
@Repository
@Transactional
public class CompetencyDaoImpl implements CompetencyDao,Serializable{

	/**
	 * Serial Id.
	 */
	private static final long serialVersionUID = 1L;
	/*
	 * CompetencyDAOImpl implements CompetencyDAO
	 */
	
	/**
     * sessionFactory.This is a class.
     */
    @Autowired
    private SessionFactory sessionFactory;
  //sessionFactory
    /**
     * This is LoginDaoImpl.
     * This is a default Constructor
     * @return 
     */
    public CompetencyDaoImpl() {
        super();
        //calls main class
    }
    /**
     * This is to getSession.
     * @return save the value in object
     */
    private   Session getSession() {
        return sessionFactory.getCurrentSession();
        //return sessionFactory
    }
    /**
     * setSession. 
     * @param sessionFactory Session Factory
     * save the value in object
     */
    public final void setSession(final SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }
    
    /**
     * This is to getByCompetencyName.
     * @param  CompetencyName
     * save the value in object
     */

  /*
   *This is to insert Competency
   * @see com.epam.login.DAO.CompetencyDao#insertCompetency(java.lang.String)
   */
    @Override
	public final String insertCompetency(final String competencyName) {
    	String message="already Exist";
    	String hql=("from Competency competency where competency.competencyName='"+competencyName+"'");
        List list = getSession().createQuery(hql).list();
    	if(list==null || list.isEmpty())
    	{
    	  Session session = getSession();
    	  boolean active = false;
    	  Competency competency = new Competency(competencyName,active);
    	  session.save(competency);
    		String hql1=(" update Competency competency set competency.active=1 where competency.competencyName='"+competencyName+"'");
    		int passes = getSession().createQuery(hql1).executeUpdate();
    	  return message="passes";
    	}
    	else
    	{
    		  Competency comp = (Competency) list.get(0);
    	      boolean active = comp.getActive();
    	      if(active){
    	    	  message="already exist";
    	      }
    	      else
    	      {
    	    	  String hql3=(" update Competency competency set competency.active=1 where competency.competencyName='"+competencyName+"'");
    	    		int passes = getSession().createQuery(hql3).executeUpdate();
    	    	  return message="passes";
    	      }
    	}
    	return message;
	}

    /*
     *This is to remove Competency.
     * @see com.epam.login.DAO.CompetencyDao#insertCompetency(java.lang.String)
     */
	
	@Override
	public final String removeCompetency(final String competencyName) {
		String hql=(" update Competency competency set competency.active=0 where competency.competencyName='"+competencyName+"'");
		int result = getSession().createQuery(hql).executeUpdate();
		return "result";
	}
	
	 /*
	   *This is to get Competency.
	   * @see com.epam.login.DAO.CompetencyDao#insertCompetency(java.lang.String)
	   */
	@SuppressWarnings("unchecked")
	@Override
	public List<Competency> getCompetency() {
		 String hql = ("from Competency competency where competency.active=1");
		 List<Competency> list = getSession().createQuery(hql).list();
		 if(list != null && !list.isEmpty())
	        {
	        	return list;       
	        }
	        return null;
	}
	/* (getLevels)
	 * @see com.epam.login.DAO.CompetencyDao#getLevels(java.lang.String)
	 */
	@Override
	public List<Levels> getLevels(final String cname) {
		String hql = ("from Levels where competency.competencyName='"+cname+"'");
		List<Levels> list = getSession().createQuery(hql).list();
		 if(list != null && !list.isEmpty())
	        {
	        	return list;       
	        }
	        return null;
	}

}
