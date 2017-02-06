/*
 * com.epam.login.models
 */
package com.epam.login.models;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.validation.constraints.NotNull;

import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@Table(name = "panel_availability")
public class PanelAvailability {

	
	@Id
    @GeneratedValue
    @Column(name = "availability_id")
    private int availabilityId;
	
	@Column(name = "availability_date_time")
    private String availability_date;
	
	  @Transient
	    @JsonProperty("eventNames")
	private List<PanelAvailability> eventNames;
	  
	  @Transient
	    @JsonProperty("reventNames")
	private List<String> reventNames;
	
	  
	/**
	 * Return.
	 * @return return
	 */
	public List<String> getReventNames() {
		return reventNames;
	}

	/**
	 * Return.
	 * setReventNames.
	 * @param reventNames reventNames
	 */
	public void setReventNames(List<String> reventNames) {
		this.reventNames = reventNames;
	}

	/**
	 * Return.
	 * @return return
	 */
	public List<PanelAvailability> getEventNames() {
		return eventNames;
	}

	/**
	 *  eventNames.
	 * @param eventNames  eventNames
	 */
	public void setEventNames(List<PanelAvailability> eventNames) {
		this.eventNames = eventNames;
	}

	/**
	 * Return.
	 * @return return
	 */
	public String getAvailability_date() {
		return availability_date;
	}

	/**
	 * availability_date.
	 * @param availability_date availability_date
	 */
	public void setAvailability_date(String availability_date) {
		this.availability_date = availability_date;
	}
	

	@Column(name = "all_day")
    private boolean allDay;
	
	@Column(name = "duration")
    private int duration;
	
	@Column(name = "status")
    private boolean status;
	
	@NotNull
	@ManyToOne(cascade=CascadeType.ALL)
	@JoinColumn(name="user_id2",referencedColumnName="user_id")
	private Login login;

	/**
	 * Return.
	 * @return return
	 */
	public int getAvailabilityId() {
		return availabilityId;
	}

	/**
	 * availabilityId.
	 * @param availabilityId availabilityId
	 */
	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}

	/**
	 * Return.
	 * @return return
	 */
	public boolean getAllDay() {
		return allDay;
	}

	/**
	 * AllDay.
	 * @param allDay allDay.
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	/**
	 * PanelAvailability.
	 */
	public PanelAvailability()
	{
		super();
	}
	
	/**
	 * panAv.
	 * @param panAv panAv
	 */
	public PanelAvailability(PanelAvailability panAv) {
		super();
	
		String timeFormat = "yyyy-MM-dd hh:mm:ss";
		
		String currentTime = panAv.getAvailability_date();
		System.out.println("In constructor PRopose Time"+currentTime); 
		this.availability_date = currentTime;
		this.duration =        panAv.getDuration();
		this.allDay =     panAv.getAllDay();
		this.status = panAv.getStatus();
		  
	}
	/**
	 * return.
	 * @return return
	 */
	public int getDuration() {
		return duration;
	}

	/**
	 * Duration.
	 * @param duration duration
	 */
	public void setDuration(int duration) {
		this.duration = duration;
	}

	/**
	 * Return.
	 * @return return
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * SetStatus.
	 * @param status status
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * GetLogin.
	 * @return return
	 */
	public Login getLogin() {
		return login;
	}

	/**
	 * SetLogin.
	 * @param login login
	 */
	public void setLogin(Login login) {
		this.login = login;
	}
	
	@Transient
	private List<Integer> availabilityIds;


	public List<Integer> getAvailabilityIds() {
		return availabilityIds;
	}

	public void setAvailabilityIds(List<Integer> availabilityIds) {
		this.availabilityIds = availabilityIds;
	}
	

}
/*
 * This is PanelAvailabilty Model Class.
 */
