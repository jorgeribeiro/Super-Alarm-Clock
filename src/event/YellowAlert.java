package event;

import java.util.GregorianCalendar;

public class YellowAlert extends Event {
	
	public YellowAlert(GregorianCalendar time, boolean active) {
		super(time, active);
	}

	@Override
	public String Trigger() {
		return Event.YELLOW_ALERT;
	}

}
