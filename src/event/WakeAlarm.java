package event;

import java.util.GregorianCalendar;

public class WakeAlarm extends Event {
	
	public WakeAlarm(GregorianCalendar time) {
		super(time);
	}
	
	@Override
	public String Trigger() {
		return Event.WAKE_ALARM;
	}

}
