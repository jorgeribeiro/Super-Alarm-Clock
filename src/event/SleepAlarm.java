package event;

import java.util.GregorianCalendar;

public class SleepAlarm extends Event {
	
	public SleepAlarm(GregorianCalendar time) {
		super(time);
	}

	@Override
	public String Trigger() {
		return Event.SLEEP_ALARM;		
	}

}
