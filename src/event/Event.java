package event;

import java.util.GregorianCalendar;

public abstract class Event {
	public static final String WAKE_ALARM   = " Time to wake up! ";
	public static final String SLEEP_ALARM  = " Time to go to bed! ";
	public static final String RED_ALERT    = " You must go to bed now! ";
	public static final String YELLOW_ALERT = " Get ready to go to bed! ";
	
	private GregorianCalendar time;
	
	public Event(GregorianCalendar time) {
		this.time = new GregorianCalendar();
		setTime(time);
	}
	
	public GregorianCalendar getTime() {
		return time;
	}

	public void setTime(GregorianCalendar time) {
		this.time = time;
	}

	public abstract String Trigger();
}
