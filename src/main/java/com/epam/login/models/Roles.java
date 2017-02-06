package com.epam.login.models;
/**
 * package includes class Roles .
 */
///imports login models
/*
 * These are all imports used.
 */
/*
 * These are all imports used.
 */
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Roles.
 * @author  vthakur
 */
@Entity
@Table(name = "roles")
public class Roles {
	/*
	 * Class Roles
	 */

    /**
     * role_Id.This is a class.
     */
    @Id
    @GeneratedValue
    @Column(name = "role_id")
    private int roleId;
    /*
     * initialize roleId
     */

    /**
     * Roles.This is the default constructor.
     */
    public Roles() {
        super();
        //calls main class 
    }

    /**
     * getRoleId.
     * @return save the value in object
     */
    public final int getRoleId() {
        return roleId;
        //returns roleId
    }

    /**
     * setRoleId. 
     * @param roleId
     * save the value in object
     */
    public final void setRoleId(final int roleId) {
        this.roleId = roleId;
    }

    /**
     * getRoleName.
     * @return save the value in object
     */
    public final String getRoleName() {
        return roleName;
        //returns roleName
    }
    
    /**
     * setRoleName.
     * @param roleName
     * save the value in object
     */
    public final void setRoleName(final String roleName) {
        this.roleName = roleName;
    }

    /**
     * roleName.This is a class.
     */
    @Column(name = "role_name")
    private String roleName;
    /*
     * Initialize roleName
     */

}
/*
 * This is Roles Model Class.
 */
