package com.epam.login.vto;

/**
 * Levels.
 * @author  vthakur
 */
public class LevelsVTO {
    /**
     * levelId.This is a class.
     */
    private int levelId;
    /**
     * levelName.This is a class.
     */
    private String levelName;
    /**
     * competencyId.This is a class.
     */
    private CompetencyVTO competency;
     
    /**
	 * @return the competency
	 */
	public CompetencyVTO getCompetency() {
		return competency;
	}

	/**
	 * @param competency the competency to set
	 */
	public void setCompetency(final CompetencyVTO competency) {
		this.competency = competency;
	}

	/**
     * Levels.This is the default constructor.
     */
    public LevelsVTO() {
        super();
    }

    /**
     * getLevelId.
     * @return save the value in object
     */
    public final int getLevelId() {
        return levelId;
    }

    /**
     * setLevelId.
     * @param levelId
     * save the value in object
     */
    public final void setLevelId(final int levelId) {
        this.levelId = levelId;
    }

    /**
     * getLevelName.
     * @return save the value in object
     */
    public final String getLevelName() {
        return levelName;
    }

    /**
     * setLevelName.
     * @param levelName
     * save the value in object
     */
    public final void setLevelName(final String levelName) {
        this.levelName = levelName;
    }

 
}
