package model;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.GregorianCalendar;
import event.*;

public class Clock {
	private GregorianCalendar time;
	private ArrayList<Event> events;
	
	public static final int WAKE_ALARM   = 0;
	public static final int SLEEP_ALARM  = 1;
	public static final int RED_ALERT    = 2;
	public static final int YELLOW_ALERT = 3;
	
	public Clock() {
		time = new GregorianCalendar();
		events = new ArrayList<Event>();
		// save indexes for each event
		events.add(Clock.WAKE_ALARM, null);
		events.add(Clock.SLEEP_ALARM, null);
		events.add(Clock.RED_ALERT, null);
		events.add(Clock.YELLOW_ALERT, null);
	}

	public GregorianCalendar getTime() {
		return time;
	}

	public void setTime(GregorianCalendar time) {
		this.time = time;
	}
	
	public void addTime(int field, int amount) {
		time.add(field, amount);
	}
	
	public void addTime(int index, int field, int amount) {
		events.get(index).getTime().add(field, amount);
	}

	public Event getEvent(int index) {
		return events.get(index);
	}
	
	public void setEvent(int index, Event event) {
		events.set(index, event);
	}
	
	public String checkEvents() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String systemClock = dateFormat.format(time.getTime());
		for(Event e : events) {
			if(e != null && e.isActive()) {
				String eventClock = dateFormat.format(e.getTime().getTime());
				if(eventClock.equals(systemClock)) {
					return e.Trigger();
				}
			}
		}
		return "";
	}
	
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		return dateFormat.format(time.getTime());
	}
	
	public String getTimeStamp(){
		SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy hh:mm a");
		return dateFormat.format(time.getTime()) + "\n";
	}
}