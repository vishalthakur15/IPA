package com.epam.login.models;
/**
 * package includes class Login .

 */
/*
 * These are all imports used.
 */
//import login models
import java.sql.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
/*Login*/
/**
 * Login.
 * @author  vthakur
 */
@Entity
@Table(name = "users1")
public class Login {

	/**
	 * user_Id. this is key.
	 */
	@Id
	@Column(name = "user_id")
	private int userId;

	/**
	 * userName. this is key.
	 */
	@Column(name = "user_name")
	private String userName;

	/**
	 * getUserName.
	 * @return save the value in object
	 */
	public final String getUserName() {
		return userName;
		//returns userName
	}

	/**
	 * setUserName.
	 * @param userName the userName
	 *            save the value in object
	 */
	public final void setUserName(final String userName) {
		this.userName = userName;
	}

	/**
	 * firstName.This is key.
	 */
	 
	@Column(name = "first_name")
	private String firstName;
	/**
	 * middleName.This is key.
	 */
	 
	@Column(name = "middle_name")
	private String middleName;
	/*
	 * initialize encryptedKey
	 */
	/**
	 * encryptedKey.This is key.
	 */
	 
	@Column(name = "encrypted_key")
	private String encryptedKey;
	/*
	 * initialize encryptedKey
	 */
	/**
	 * lastName.This is key.
	 */
	 
	@Column(name = "last_name")
	private String lastName;
	/*
	 * initialize lastName
	 */

	/**
	 * active.This is key.
	 */
	 
	@Column(name = "active")
	private Boolean active;
	/*
	 * initialize active
	 */

	@Transient
	private List<String> userNames;
	
	/**
	 * getFirstTime.
	 * @return save the value in object.
	 */
	public final Boolean getFirstTime() {
		/*
		 * etFirstTimemethod
		 */
		return firstTime;
	}

	/**
	 * @return the userNames
	 */
	public final List<String> getUserNames() {
		return userNames;
	}

	/**
	 * @param userNames7 the userNames to set
	 */
	public final void setUserNames(final List<String> userNames7) {
		this.userNames = userNames7;
	}

	/**
	 * setFirstTime.
	 * @param firstTime6 first time
	 * save the value in object.
	 */
	public final void setFirstTime(final Boolean firstTime6) {
		/*
		 *  setFirstTimemethod
		 */
		this.firstTime = firstTime6;
	}

	/**
	 * firstTime.This is key.
	 */
	 
	@Column(name = "first_time")
	private Boolean firstTime;
	/*
	 * initialize firstTime
	 */
	/**
	 * contactId.This is key.
	 */

	/*
	 * initialize contactId
	 */

	/**
	 * getuserId.
	 * @return save the value in object.
	 */
	public final int getUserId() {
		/*
		 * getUserIdmethod
		 */
		return userId;
		//returns userID
	}

	/**
	 * setUserId.
	 * @param userId5 userId
	 *            save the value in object.
	 */
	public final void setUserId(final int userId5) {
		/*
		 *setUserId method 
		 */
		this.userId = userId5;
	}

	/**
	 * getFirstName.
	 * @return save the value in object.
	 */
	public final String getFirstName() {
		/*
		 * getFirstNamemethod
		 */
		return firstName;
		//returns firstName 
	}

	/**
	 * setFirstName.
	 * @param firstName5 firstName
	 *            save the value in object.
	 */
	public final void setFirstName(final String firstName5) {
		this.firstName = firstName5;
	}

	/**
	 * getMiddleName.
	 * @return save the value in object.
	 */
	public final String getMiddleName() {
		/*
		 * getMiddleName method
		 */
		return middleName;
		//returns middleName
	}

	/**
	 * setMiddleName.
	 * @param middleName5 middleName
	 *            save the value in object.
	 */
	public final void setMiddleName(final String middleName5) {
		/*
		 * setMiddleNamemethod
		 */
		this.middleName = middleName5;
	}

	/**
	 * getLastName.
	 * @return save the value in object.
	 */
	public final String getLastName() {
		/*
		 * getLastNamemethod
		 */
		return lastName;
		//returns lastName
	}

	/**
	 * setLasteName.
	 * @param lastName5 lastName
	 *            save the value in object.
	 */
	public final void setLastName(final String lastName5) {
		/*
		 * setLastName method
		 */
		this.lastName = lastName5;
	}

	/**
	 * getActive.
	 * @return save the value in object.
	 */
	public final Boolean getActive() {
		/*
		 * getActive method
		 */
		return active;
		//returns active
	}

	/**
	 * setActive.
	 * @param active5 active
	 *            save the value in object.
	 */
	public final void setActive(final Boolean active5) {
		/*
		 * setActive method
		 */
		this.active = active5;
	}

	/**
	 * Login.This is the default constructor.
	 */
	public Login() {
		/*
		 * Login method
		 */
		super();
		//calls main class
	}


	/**
	 * Login.
	 * @param login login
	 */

	/**
	 * Contact.This is class.
	 */
	@OneToOne
	@JoinColumn(name="contact_id")
	private Contact contact;
	/*
	 * initialize contact
	 */

	/**
	 * getContactId.
	 * @return save the value in object.
	 */


	/**
	 * setContactId.
	 * @param contactId contactId 
	 *            save the value in object.
	 */

	/**
	 * getEncryptedKey.
	 * @return save the value in object.
	 */
	public final String getEncryptedKey() {
		return encryptedKey;
		//return encryptedKey
	}

	/**
	 * setEncryptedKey.
	 * @param encryptedKey3 encryptedKey
	 *            save the value in object
	 */
	public final void setEncryptedKey(final String encryptedKey3) {
		/*
		 * initialize setEncryptedKey
		 */
		this.encryptedKey = encryptedKey3;
	}

	/**
	 * Roles.This is the class.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "role_id", referencedColumnName = "role_id")
	private Roles role;
	/*
	 * initialize role
	 */

	/**
	 * @return the role
	 */
	public final Roles getRole() {
		return role;
	}

	/**
	 * @param role2 the role to set
	 */
	public final void setRole(final Roles role2) {
		this.role = role2;
	}

	/**
	 * getToken.
	 * @return save the value in object.
	 */
	public final String getToken() {
		/*
		 * initialize  getToken
		 */
		return token;
		//returns token
	}

	/**
	 * setToken.
	 * @param token1 token
	 *            save the value in object.
	 */
	public final void setToken(final String token1) {
		/*
		 * initialze setToken
		 */
		this.token = token1;
	}

	/**
	 * token.This is the key.
	 */
	@Column(name = "token")
	private String token;
	/*
	 * initialize token
	 */

	/**
	 * expiry_date.This is the key.
	 */
	@Column(name = "expiry_date")
	private Date expiryDate;
	/*
	 * initialize  expiryDate
	 */

	/**
	 * getExpiryDate.
	 * @return save the value in object.
	 */
	public final Date getExpiryDate() {
		/*
		 * getExpiryDate method 
		 */
		return expiryDate;
		//returns expiryDate
	}

	/**
	 * setExpiryDate.
	 * @param expiryDate1 expiryDate
	 *            save the value in object.
	 */
	public final void setExpiryDate(final Date expiryDate1) {
		/*
		 * initialize  setExpiryDate
		 */
		this.expiryDate = expiryDate1;
	}

	/**
	 * Levels.This is the class.
	 */
	@ManyToOne(cascade = CascadeType.ALL)
	@JoinColumn(name = "level_id", referencedColumnName = "level_id")
	private Levels levels;
	//initialize levels

	/**
	 * @return the levels
	 */
	public final Levels getLevels() {
		return levels;
	}

	/**
	 * @param levels1 the levels to set
	 */
	public final void setLevels(final Levels levels1) {
		this.levels = levels1;
	}

	/**
	 * getContact.
	 * @return save the value in object.
	 */
	public final Contact getContact() {
		/*
		 *  getContact token
		 */
		return contact;
		//returns contact
	}

	/**
	 * setContact. 
	 * @param contact1 contact
	 *            save the value in object.
	 */
	public final void setContact(final Contact contact1) {
		this.contact = contact1;
		/*
		 * final method setContact
		 */
	} //end of loop

	/**
	 * setLogin.
	 * @param login1 login
	 */
	public final void setLogin(final Login login1) {
		// TODO Auto-generated method stub
		
	}

	/**
	 * Roles.
	 * @param roles1 roles
	 */
	public final void setRoles(final Roles roles1) {
		// TODO Auto-generated method stub
		
	}

} //end of loop

/*
 * This is Login Model Class.
 */
