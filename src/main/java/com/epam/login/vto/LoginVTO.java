package com.epam.login.vto;
/**
 * package includes class Login .
 */
//import login models
import java.sql.Date;
import java.util.List;
/*Login*/
/**
 * Login.
 * @author  vthakur
 */
public class LoginVTO {

	/**
	 * user_Id. this is key.
	 */
	private int userId;

	/**
	 * userName. this is key.
	 */
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
	 * @param userName4 the userName
	 *            save the value in object
	 */
	public final void setUserName(final String userName4) {
		this.userName = userName4;
	}

	/**
	 * firstName.This is key.
	 */
	 
	private String firstName;
	/**
	 * middleName.This is key.
	 */
	 
	private String middleName;
	/*
	 * initialize encryptedKey
	 */
	/**
	 * encryptedKey.This is key.
	 */
	 
	private String encryptedKey;
	/*
	 * initialize encryptedKey
	 */
	/**
	 * lastName.This is key.
	 */
	 
	private String lastName;
	/*
	 * initialize lastName
	 */

	/**
	 * active.This is key.
	 */
	 
	private Boolean active;
	/*
	 * initialize active
	 */

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
	public List<String> getUserNames() {
		return userNames;
	}

	/**
	 * @param userNames4 the userNames to set
	 */
	public void setUserNames(final List<String> userNames4) {
		this.userNames = userNames4;
	}

	/**
	 * setFirstTime.
	 * @param firstTime3 first time
	 * save the value in object.
	 */
	public final void setFirstTime(final Boolean firstTime3) {
		/*
		 *  setFirstTimemethod
		 */
		this.firstTime = firstTime3;
	}

	/**
	 * firstTime.This is key.
	 */
	 
	private Boolean firstTime;
	/*
	 * initialize firstTime
	 */
	/**
	 * contactId.This is key.
	 */
	/*@NotNull
	@Column(name = "contact_id", unique = true, nullable = false)
	@GeneratedValue(generator = "gen")
	@GenericGenerator(name = "gen", strategy = "foreign", parameters = @Parameter(name = "property", value = "contact"))
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
	 * @param userId3 userId
	 *            save the value in object.
	 */
	public final void setUserId(final int userId3) {
		/*
		 *setUserId method 
		 */
		this.userId = userId3;
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
	 * @param firstName3 firstName
	 *            save the value in object.
	 */
	public final void setFirstName(final String firstName3) {
		this.firstName = firstName3;
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
	 * @param middleName3 middleName
	 *            save the value in object.
	 */
	public final void setMiddleName(final String middleName3) {
		/*
		 * setMiddleNamemethod
		 */
		this.middleName = middleName3;
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
	 * @param lastName3 lastName
	 *            save the value in object.
	 */
	public final void setLastName(final String lastName3) {
		/*
		 * setLastName method
		 */
		this.lastName = lastName3;
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
	 * @param active3 active
	 *            save the value in object.
	 */
	public final void setActive(final Boolean active3) {
		/*
		 * setActive method
		 */
		this.active = active3;
	}

	/**
	 * Login.This is the default constructor.
	 */
	public LoginVTO() {
		/*
		 * Login method
		 */
		super();
		//calls main class
	}


	/**
	 * LoginVTO login.
	 * @param login3 login.
	 */
	public LoginVTO(LoginVTO login3) {
		
	}

	/**
	 * Contact.This is class.
	 */
	private ContactVTO contact;
	/*
	 * initialize contact
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
	 * @param encryptedKey2 encryptedKey
	 *            save the value in object
	 */
	public final void setEncryptedKey(final String encryptedKey2) {
		/*
		 * initialize setEncryptedKey
		 */
		this.encryptedKey = encryptedKey2;
	}

	/**
	 * Roles.This is the class.
	 */
	private RolesVTO role;
	/*
	 * initialize role
	 */

	/**
	 * @return the role
	 */
	public RolesVTO getRole() {
		return role;
	}

	/**
	 * @param role2 the role to set
	 */
	public void setRole(final RolesVTO role2) {
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
	private String token;
	/*
	 * initialize token
	 */

	/**
	 * expiry_date.This is the key.
	 */
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
	private LevelsVTO levels;
	//initialize levels

	/**
	 * @return the levels
	 */
	public LevelsVTO getLevels() {
		return levels;
	}

	/**
	 * @param levels1 the levels to set
	 */
	public void setLevels(final LevelsVTO levels1) {
		this.levels = levels1;
	}

	/**
	 * getContact.
	 * @return save the value in object.
	 */
	public final ContactVTO getContact() {
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
	public final void setContact(final ContactVTO contact1) {
		this.contact = contact1;
		/*
		 * final method setContact
		 */
	} //end of loop

	/**
	 * LoginVTO login.
	 * @param login1 login
	 */
	public void setLogin(final LoginVTO login1) {
	
	}

	

	/**
	 * RolesVTO roles1.
	 * @param roles1 roles
	 */
	public void setRoles(final RolesVTO roles1) {

		
	}

} //end of loop