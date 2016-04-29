package event;

import java.util.GregorianCalendar;

public class WakeAlarm extends Event {
	
	public WakeAlarm(GregorianCalendar time, boolean active) {
		super(time, active);
	}
	
	@Override
	public String Trigger() {
		return Event.WAKE_ALARM;
	}

}
