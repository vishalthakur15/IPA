package com.epam.login.models;
/**
 * package includes class Competency .
 */

import java.util.List;
import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;
/**
 * Competency.
 * @author vthakur
 */
@Entity
@Table(name = "competency")
public class Competency {
    /**
     * competency_Id.This is a class.
     */
    @Id
    @GeneratedValue
    @Column(name = "competency_id")
    private int competencyId;
    /**
     * competencyName.This is a class.
     */
    @Column(name = "competency_name")
    private  String competencyName;
    
    /**
     * level.This is a class.
     */
    @OneToMany(mappedBy = "competency")
    private Set<Levels> level;

    /**
     * getLevel.
     * @return save the value in object.
     */
/**
* active.This is key.
*/
@NotNull
@Column(name = "active")
private Boolean active;
/*
* initialize active
*/

    /**
     * @return return
     */
    public final Set<Levels> getLevel() {
        return level;
    }
    /**
* getActive.
* @return save the value in object.
*/
    @Transient
    @JsonProperty("competencyNames")
private List<String> competencyNames;
    
/**
 * @return return
 */
public List<String> getCompetencyNames() {
return competencyNames;
}
/**
 * @param competencyNames competencyNames.
 */
public void setCompetencyNames(List<String> competencyNames) {
this.competencyNames = competencyNames;
}
/**
 * @return return.
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

public void setActive(Boolean active) {
/*
* setActive method
*/
this.active = active;
}

/**
     * setLevel.
     * @param level
     * save the value in object
     */
    public final void setLevel(final Set<Levels> level) {
        this.level = level;
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
     * @param competencyId
     * save the value in object
     */
    public final void setCompetencyId(final int competencyId) {
        this.competencyId = competencyId;
    }

    /**
     * Competency.This is the default constructor.
     */
    public Competency() {
        super();
    }
    

    /**
     * @param competencyName competencyName 
     * @param active active
     */
    public Competency(final String competencyName,final boolean active) {
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
     * @param competencyName
     * save the value in object
     */
    public final void setCompetencyName(final String competencyName) {
        this.competencyName = competencyName;
    }
    
}
/*
 * This is Competency Model.
 */
