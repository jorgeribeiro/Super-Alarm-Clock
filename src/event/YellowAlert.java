package event;

import java.util.GregorianCalendar;

public class YellowAlert extends Event {
	
	public YellowAlert(GregorianCalendar time) {
		super(time);
	}

	@Override
	public String Trigger() {
		return Event.YELLOW_ALERT;
	}

}
