/*
 * package com.epam.login.vto
 */
package com.epam.login.vto;
import java.util.List;

/**
 * PanelAvailabilityVTO.
 * @author Vishal_Thakur
 */
public class PanelAvailabilityVTO {


    private int availabilityId;
	
    private List<Integer> availabilityIds;
    
    public List<Integer> getAvailabilityIds() {
		return availabilityIds;
	}

	public void setAvailabilityIds(List<Integer> availabilityIds) {
		this.availabilityIds = availabilityIds;
	}

	private String availability_date;
	
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

	/**
	 * List.
	 */
	private List<PanelAvailabilityVTO> eventNames;
    /**
     * return.
     * @return return
     */
    public List<PanelAvailabilityVTO> getEventNames() {
		return eventNames;
	}

	/**
	 * eventNames.
	 * @param eventNames eventNames
	 */
	public void setEventNames(List<PanelAvailabilityVTO> eventNames) {
		this.eventNames = eventNames;
	}

	/**
	 * reventNames.
	 */
	private List<String> reventNames;
	/**
	 * return.
	 * @return return
	 */
	public List<String> getReventNames() {
		return reventNames;
	}

	/**
	 * reventNames.
	 * @param reventNames reventNames 
	 */
	public void setReventNames(List<String> reventNames) {
		this.reventNames = reventNames;
	}

	private boolean allDay;
	
    private int duration;
	
    private boolean status;

	private LoginVTO login;

	/**
	 * return.
	 * @return return
	 */
	public int getAvailabilityId() {
		return availabilityId;
	}

	/**
	 * availabiltyId.
	 * @param availabilityId availabilityId
	 */
	public void setAvailabilityId(int availabilityId) {
		this.availabilityId = availabilityId;
	}

	/**
	 * Return.
	 * @return return.
	 */
	public boolean getAllDay() {
		return allDay;
	}

	/**
	 * allDay.
	 * @param allDay allDay
	 */
	public void setAllDay(boolean allDay) {
		this.allDay = allDay;
	}
	/**
	 * PanelAvailabilty.
	 */
	public PanelAvailabilityVTO()
	{
		super();
	}
	/**
	 * PanelAvailabilityVTO.
	 * @param panAv panav.
	 */
	public PanelAvailabilityVTO(PanelAvailabilityVTO panAv) {
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
	 * @return return.
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
	 * Return.
	 * @return return 
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Status.
	 * @param status status.
	 */
	public void setStatus(boolean status) {
		this.status = status;
	}

	/**
	 * Return.
	 * @return return
	 */
	public LoginVTO getLogin() {
		return login;
	}

	/**
	 * login.
	 * @param login login
	 */
	public void setLogin(LoginVTO login) {
		this.login = login;
	}
	
}
