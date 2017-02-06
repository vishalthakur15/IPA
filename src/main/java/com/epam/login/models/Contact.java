package com.epam.login.models;
/**
 * package includes class Contact .
 */
/*
 * These are all imports used.
 */
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;

/**
 * Contact.
 * @author vthakur
 */
@Entity
@Table(name = "contact_info")
public class Contact {
    /**
     * This is a class for Contact id.
     * contactId.
     */
    @Id
    @GeneratedValue
    @Column(name = "contact_id")
    private int contactId;
   

    /**
     * This is a class for Email.
     * email.
     */
    @Column(name = "email")
    private String emailId;
    /**
     * This is a class for contact.
     * contact.
     */
    @Column(name = "contact")
    private String contactNo;
    /**
     * This is a class for Login.
     * Login.This is a class
     */
    @OneToOne(mappedBy = "contact", cascade = CascadeType.ALL)
    private Login login;

    /**
     * This is a class.
     * getLogin.
     * @return save the value in object
     */
    public final Login getLogin() {
        return login;
    }

    /**
     * setLogin.
     * @param login9
     * save the value in object
     */
    public final void setLogin(final Login login9) {
        this.login = login9;
    }

   
    /**
     * Contact.
     */
    public Contact() {
        super();
    }
    
    
    /**
     * Email Id Contact No.
     * @param emailId9 emailId
     * @param contactNo9 contactNo
     */
    public Contact(final String emailId9,final String contactNo9)
    {
    	this.emailId = emailId9;
    	this.contactNo = contactNo9;
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
     * @param contactId9
     * save the value in object
     */
    public final void setContactId(final int contactId9) {
        this.contactId = contactId9;
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
     * @param emailId9
     * save the value in object
     */
    public final void setEmailId(final String emailId9) {
        this.emailId = emailId9;
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
     * @param contact9
     * save the value in object
     */
    public final void setContactNo(final String contact9) {
        this.contactNo = contact9;
    }
}

/*
 * This is Contact Model.
 */
