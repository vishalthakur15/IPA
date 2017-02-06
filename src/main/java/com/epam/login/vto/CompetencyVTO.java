package com.epam.login.vto;
/**
 * package includes class Competency .
 */

import java.util.List;
/**
 * Competency.
 * @author vthakur
 */
public class CompetencyVTO {
    /**
     * competency_Id.This is a class.
     */
    private int competencyId;
    /**
     * competencyName.This is a class.
     */
    private  String competencyName;
    
	/**
	 * active.This is key.
	 */
	private Boolean active;

	/**
	 * competencyNames.
	 */
	private List<String> competencyNames;
    
	/**
	 * @return return
	 */
	public List<String> getCompetencyNames() {
		return competencyNames;
	}
	/**
	 * @param competencyNames competencyNames
	 */
	public void setCompetencyNames(final List<String> competencyNames) {
		this.competencyNames = competencyNames;
	}
	/**
	 * @return return
	 */
	public Boolean getActive() {
    	/*
		 * getActive method
		 */
		return active;
		//returns active
	}
	/**
	 * setActive.
	 * @param active active
	 *            save the value in object.
	 */

	public void setActive(final Boolean active) {
		/*
		 * setActive method
		 */
		this.active = active;
	}


    /**
     * getCompetencyId.
     * @return save the value in object
     */
    public final int getCompetencyId() {
        return competencyId;
    }

    /**
     * setCompetencyIdl.
     * @param competencyId competencyId
     * save the value in object
     */
    public final void setCompetencyId(final int competencyId) {
        this.competencyId = competencyId;
    }

    /**
     * Competency.This is the default constructor.
     */
    public CompetencyVTO() {
        super();
    }
    

    /**
     * @param competencyName competencyName
     * @param active active
     */
    public CompetencyVTO(final String competencyName,final boolean active) {
    	this.competencyName = competencyName;
    	this.active = active;
	}
	/**
     * getCompetencyName.
     * @return save the value in object
     */
    public final String getCompetencyName() {
        return competencyName;
    }

    /**
     * setCompetencyName.
     * @param competencyName competencyName
     * save the value in object
     */
    public final void setCompetencyName(final String competencyName) {
        this.competencyName = competencyName;
    }
    
}
