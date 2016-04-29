package event;

import java.util.GregorianCalendar;

public class RedAlert extends Event {
	
	public RedAlert(GregorianCalendar time, boolean active) {
		super(time, active);
	}

	@Override
	public String Trigger() {
		return Event.RED_ALERT;
	}

}
