package event;

import java.util.GregorianCalendar;

import model.Clock;

public class EventFactory {
	public Event getEvent(int type, GregorianCalendar time) {
		if(type == Clock.WAKE_ALARM)
			return new WakeAlarm(time);
		else if(type == Clock.SLEEP_ALARM)
			return new SleepAlarm(time);
		else if(type == Clock.RED_ALERT)
			return new RedAlert(time);
		else if(type == Clock.YELLOW_ALERT)
			return new YellowAlert(time);
		return null;
	}
}
