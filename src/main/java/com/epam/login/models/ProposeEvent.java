package com.epam.login.models;
//import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;


/**
 * ProposeEvent.
 * @author anjali.garg
 */
@Entity
@Table(name="propose_event")
public class ProposeEvent {
	/**
	 * proposeId. This is primary key
	 */
	@NotNull
	@Id
	@GeneratedValue
	@Column(name="propose_id")
	private int proposeId;
	/**
	 * proposeDateTime. This is Date
	 */
	@NotNull
	@Column(name="propose_date_time")
	private Timestamp proposeDateTime;
	/*
	 * duration
	 */
	/**
	 * duration. This is Time
	 */
	@NotNull
	@Column(name="duration")
	private int duration;
	/*
	 * description
	 */
	/**
	 * description. This is String
	 */
	@NotNull
	@Column(name="description")
	private String description;
	/*
	 * proposed_date
	 */
	/**
	 * proposedDate. This is Date
	 */
	@NotNull
	@Column(name="proposed_date_time")
	private Timestamp proposedDateTime;
	/*
	 * 
	 */
	/**
	 * user_id.
	 */
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id",referencedColumnName="user_id")
	private Login login;
	/*
	 * 
	 */
	/**
	 * competency_id.
	 */
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="competency_id1",referencedColumnName="competency_id")
	private Competency competency;
	
	/**
	 * Propose event.
	 */
	public ProposeEvent() {
		super();
	}
	/**
	 * Get proposeid.
	 * @return return
	 */
	public int getProposeID() {
		return proposeId;
	}
	/**
	 * getProposeId.
	 * @return return
	 */
	public int getProposeId() {
		return proposeId;
	}
	/**
	 * propose id.
	 * @param proposeId propose id.
	 */
	public void setProposeId(final int proposeId) {
		this.proposeId = proposeId;
	}
	/**
	 * propose id.
	 * @param proposeID propose id.
	 */
	public void setProposeID(final int proposeID) {
		this.proposeId = proposeID;
	}
	/**
	 * propose.
	 * @param propose propose
	 */
	public ProposeEvent(final ProposeEvent propose) {
		super();

		String timeFormat = "yyyy-MM-dd HH:mm:ss";
		DateFormat dateFormat = new SimpleDateFormat(timeFormat);
	
		String currentTime = dateFormat.format(propose.getProposeDateTime());
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
	 *  return.
	 * @return return
	 */
	public Timestamp getProposeDateTime() {
		return proposeDateTime;
	}
	/**
	 * proposeDateTime.
	 * @param proposeDateTime proposeDateTime
	 */
	public void setProposeDateTime(final Timestamp proposeDateTime) {
		this.proposeDateTime = proposeDateTime;
	}
	/**
	 * return.
	 * @return return
	 */
	public Login getLogin() {
		return login;
	}
	/**
	 * login.
	 * @param login login
	 */
	public void setLogin(final Login login) {
		this.login = login;
	}
	/**
	 * return.
	 * @return return
	 */
	public Competency getCompetency() {
		return competency;
	}
	/**
	 * competency.
	 * @param competency competency
	 */
	public void setCompetency(final Competency competency) {
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
	public void setDuration(final int duration) {
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
	public void setDescription(final String description) {
		this.description = description;
	}
	/**
	 *  return.
	 * @return return
	 */
	public Timestamp getProposedDateTime() {
		return proposedDateTime;
	}

	/**
	 *  proposedDateTime.
	 * @param proposedDateTime  proposedDateTime
	 */
	public void setProposedDateTime(final Timestamp proposedDateTime) {
		this.proposedDateTime = proposedDateTime;
	}
}
/*
 * This is ProposeEvent Model Class.
 */
