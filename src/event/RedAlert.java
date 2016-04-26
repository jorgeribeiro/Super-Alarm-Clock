package event;

import java.util.GregorianCalendar;

public class RedAlert extends Event {
	
	public RedAlert(GregorianCalendar time) {
		super(time);
	}

	@Override
	public String Trigger() {
		return Event.RED_ALERT;
	}

}
