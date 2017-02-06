package com.epam.login.vto;
/**
 * package includes class Contact .
 */

/**
 * Contact.
 * @author vthakur
 */
public class ContactVTO {
    /**
     * This is a class for Contact id.
     * contactId.
     */
    private int contactId;
   

    /**
     * This is a class for Email.
     * email.
     */
    private String emailId;
    /**
     * This is a class for contact.
     * contact.
     */
    private String contactNo;
   
    /**
     * ContactVTO.
     * ContactVTO to call main class
     */
    public ContactVTO() {
        super();
    }
    
    
    /**
     * @param emailId1 emailId1
     * @param contactNo1 contactNo1
     */
    public ContactVTO(final String emailId1,final String contactNo1)
    {
    	this.emailId = emailId1;
    	this.contactNo = contactNo1;
    }
    
    /**
     * getContactId.
     * @return save the value in object
     */
    public final int getContactId() {
        return contactId;
    }
    /**
     * setContactId.
     * @param contactId1
     * save the value in object
     */
    public final void setContactId(final int contactId1) {
        this.contactId = contactId1;
    }
    /**
     * getEmailId.
     * @return save the value in object
     */
    public final String getEmailId() {
        return emailId;
    }
    /**
     * setEmailId.
     * @param emailId1  emailId1
     * save the value in object
     */
    public final void setEmailId(final String emailId1) {
        this.emailId = emailId1;
    }
    /**
     * getContact.
     * @return save the value in object
     */
    public final String getContactNo() {
        return contactNo;
    }

    /**
     * setContact.
     * @param contact1 contact1
     * save the value in object
     */
    public final void setContactNo(final String contact1) {
        this.contactNo = contact1;
    }
}
