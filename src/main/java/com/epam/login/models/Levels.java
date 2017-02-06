package com.epam.login.models;
/**
 * package includes class Levels .
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * Levels.
 * @author  vthakur
 */
@Entity
@Table(name = "levels")
public class Levels {
    /**
     * levelId.This is a class.
     */
    @Id
    @GeneratedValue
    @Column(name = "level_id")
    private int levelId;
    /**
     * levelName.This is a class.
     */
    @Column(name = "level_name")
    private String levelName;
    /**
     * competencyId.This is a class.
     */
    @ManyToOne(cascade=CascadeType.ALL)
    @JoinColumn(name="competency_id",referencedColumnName="competency_id")
    private Competency competency;
     
    /**
* @return the competency
*/
public Competency getCompetency() {
return competency;
}

/**
* @param competency the competency to set
*/
public void setCompetency(Competency competency) {
this.competency = competency;
}

/**
     * Levels.This is the default constructor.
     */
    public Levels() {
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
/*
 * This is Levels Model Class.
 */
