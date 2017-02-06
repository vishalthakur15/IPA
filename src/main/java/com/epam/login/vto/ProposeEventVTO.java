package com.epam.login.vto;

import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import com.epam.login.models.Competency;
import com.epam.login.models.Login;
import com.epam.login.models.ProposeEvent;

public class ProposeEventVTO {
	/**
	 * proposeId. This is primary key.
	 */
	private int proposeId;
	/**
	 * proposeDateTime. This is Date.
	 */
	private Timestamp proposeDateTime;
	/*
	 * duration
	 */
	/**
	 * duration. This is Time.
	 */
	private int duration;
	/*
	 * description
	 */
	/**
	 * description. This is String.
	 */
	private String description;
	/*
	 * proposed_date
	 */
	/**
	 * proposedDate. This is Date.
	 */
	private Timestamp proposedDateTime;
	/*
	 * LoginVTO .
	 */
	/**
	 * LoginVTO .
	 */
	private LoginVTO login;
	
	/**
	 * CompetencyVTO competency.
	 */
	private CompetencyVTO competency;
	
	/**
	 * ProposeEventVTO.
	 */
	public ProposeEventVTO() {
		super();
	}
	/**
	 * getProposeID.
	 * @return return
	 */
	public int getProposeID() {
		return proposeId;
	}
	/**
	 * return.
	 * @return return
	 */
	public int getProposeId() {
		return proposeId;
	}
	/**
	 * proposeID.
	 * @param proposeId proposeID
	 */
	public void setProposeId(int proposeId) {
		this.proposeId = proposeId;
	}
	/**
	 * proposeID.
	 * @param proposeID proposeID
	 */
	public void setProposeID(int proposeID) {
		this.proposeId = proposeID;
	}
	/**
	 * ProposeEventVTO.
	 * @param propose propose
	 */
	public ProposeEventVTO(ProposeEvent propose) {
		super();
		
		String timeFormat = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);
		
		String currentTime = dateFormat.format(propose.getProposeDateTime());
		System.out.println("In constructor PRopose Time"+currentTime); 
		this.proposeDateTime = Timestamp.valueOf(currentTime);
		
		this.duration =        propose.getDuration();
		this.description =     propose.getDescription();
		Date today = new Date();
		
		   SimpleDateFormat sdf = 
				     new SimpleDateFormat("yyyy-MM-dd HH:mm:ss",Locale.ENGLISH);
		   String expiry = sdf.format(today);
		   this.proposedDateTime = Timestamp.valueOf(expiry);
		
		  
	}
	/**
	 * GetProposeDateTime.
	 * @return return
	 */
	public Timestamp getProposeDateTime() {
		return proposeDateTime;
	}
	/**
	 * proposeDateTime.
	 * @param proposeDateTime proposeDateTime
	 */
	public void setProposeDateTime(Timestamp proposeDateTime) {
		this.proposeDateTime = proposeDateTime;
	}
	/**
	 * return.
	 * @return return
	 */
	public LoginVTO getLogin() {
		return login;
	}
	/**
	 * Login.
	 * @param login login
	 */
	public void setLogin(LoginVTO login) {
		this.login = login;
	}
	/**
	 * Return.
	 * @return return
	 */
	public CompetencyVTO getCompetency() {
		return competency;
	}
	/**
	 * competency.
	 * @param competency competency.
	 */
	public void setCompetency(CompetencyVTO competency) {
		this.competency = competency;
	}
	/**
	 * return.
	 * @return return
	 */
	public int getDuration() {
		return duration;
	}
	/**
	 * duration.
	 * @param duration duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}
	/**
	 * return.
	 * @return return
	 */
	public String getDescription() {
		return description;
	}
	/**
	 * description.
	 * @param description description
	 */
	public void setDescription(String description) {
		this.description = description;
	}
	/**
	 * Return.
	 * @return return
	 */
	public Timestamp getProposedDateTime() {
		return proposedDateTime;
	}

	/**
	 * proposedDateTime.
	 * @param proposedDateTime proposedDateTime
	 */
	public void setProposedDateTime(Timestamp proposedDateTime) {
		this.proposedDateTime = proposedDateTime;
	}
}
