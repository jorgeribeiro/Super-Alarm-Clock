package model;

import java.util.ArrayList;
import java.util.Date;
import event.*;

public class Clock {
	private Date time;
	private ArrayList<Event> events;
	
	public Clock(Date time, ArrayList<Event> events) {
		super();
		this.time = time;
		this.events = events;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
}