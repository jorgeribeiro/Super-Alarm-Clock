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
		setupEvents(); // use factory instead
	}
	
	private void setupEvents() {
		Event wakeAlarm = new WakeAlarm();
		Event sleepAlarm = new SleepAlarm();
		Event redAlert = new RedAlert();
		Event yellowAlert = new YellowAlert();

		events.add(wakeAlarm);
		events.add(sleepAlarm);
		events.add(redAlert);
		events.add(yellowAlert);
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

	public ArrayList<Event> getEvents() {
		return events;
	}

	public void setEvents(ArrayList<Event> events) {
		this.events = events;
	}
	
	public void setAlarm(int index, GregorianCalendar time) {
		events.get(index).setTime(time);
	}
	
	public String testEvents() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		String systemClock = dateFormat.format(time.getTime());
		for(Event e : events) {
			String eventClock = dateFormat.format(e.getTime().getTime());
			if(eventClock.equals(systemClock)) {
				return e.Trigger();
			}
		}
		return "";
	}
	
	public String toString() {
		SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
		return dateFormat.format(time.getTime());
	}
	
}