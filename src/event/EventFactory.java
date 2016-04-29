package event;

import java.util.GregorianCalendar;

import model.Clock;

public class EventFactory {
	public Event getEvent(int type, GregorianCalendar time, boolean active) {
		if(type == Clock.WAKE_ALARM)
			return new WakeAlarm(time, active);
		else if(type == Clock.SLEEP_ALARM)
			return new SleepAlarm(time, active);
		else if(type == Clock.RED_ALERT)
			return new RedAlert(time, active);
		else if(type == Clock.YELLOW_ALERT)
			return new YellowAlert(time, active);
		return null;
	}
}
