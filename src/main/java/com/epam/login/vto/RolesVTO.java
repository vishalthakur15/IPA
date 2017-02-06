package com.epam.login.vto;

/**
 * Roles.
 * @author  vthakur
 */
public class RolesVTO {
	/*
	 * Class Roles
	 */

    /**
     * role_Id.This is a class.
     */
    private int roleId;
    /*
     * initialize roleId
     */

    /**
     * Roles.This is the default constructor.
     */
    public RolesVTO() {
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
    private String roleName;
    /*
     * Initialize roleName
     */

}
