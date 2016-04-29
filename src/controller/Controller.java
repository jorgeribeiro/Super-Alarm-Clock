package controller;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.Timer;

import event.Event;
import event.EventFactory;
import model.*;
import persistency.FileHandler;
import view.*;

public class Controller {
	private static GUI gui;
	private static Clock clock;
	private static FileHandler fileHandler;
	private static EventFactory eventFactory;
	//private static Student user; 	   // not used yet
	//private static Contact contact;  // not used yet
	
	public Controller() {
		gui = new GUI();
		clock = new Clock();
		fileHandler = new FileHandler();
		eventFactory = new EventFactory();
		//user = new Student();
		//contact = new Contact();
		
    	setupClock();
    	setupListeners(); 	
	}
	
	/**
	 * TODO
	 * refactor thread (too big)
	 */
	private void setupClock() {
		gui.updateClockPanel(clock.getTime()); // show first machine time
		ActionListener updateClockAction = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  new Thread(new Runnable() {
					@Override
					public void run() {
						clock.addTime(GregorianCalendar.MINUTE, 1);
						SwingUtilities.invokeLater(new Runnable() {
							@Override
							public void run() {
								gui.updateClockPanel(clock.getTime());
								String info = clock.checkEvents();
								if(info.length() > 0) {
									new EventListener(info, setFontColor(info)).actionPerformed(e);
								}
							}
						});		
					}
				  }).start();
			  }
		};
		Timer t = new Timer(1000, updateClockAction); // keep counting
		t.start();
	}
	
	private void setupListeners() {
		gui.addSetAlarmListener(new SetAlarmListener());
    	gui.addGoingToBedListener(new GoingToBedListener());
    	gui.addAwakeListener(new AwakeListener());
    	gui.addSleepStatusListener(new SleepStatusListener());
    	gui.addSetReportContactListener(new SetReportContactListener());
	}
	
	private Color setFontColor(String info) {
		if(info.equals(Event.RED_ALERT)) return new Color(255, 0, 0);
		else if(info.equals(Event.YELLOW_ALERT)) return new Color(204, 204, 0);
		else return new Color(0, 0, 0);
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	class SetAlarmListener implements ActionListener {
		SetAlarmDialog dialogSetAlarm;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dialogSetAlarm = new SetAlarmDialog(gui.getFrame(), "Set alarm", new ButtonOKListener());
		}
		
		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				setAlarms();
			}
			
			private void setAlarms() {
				GregorianCalendar timeWakeUp;
				GregorianCalendar timeSleep;
				
				// set wake up alarm
				if(!dialogSetAlarm.getTxtWakeUpHour().equals("") && !dialogSetAlarm.getTxtWakeUpMin().equals("")) {
					timeWakeUp = new GregorianCalendar(1, 1, 1,
							Integer.parseInt(dialogSetAlarm.getTxtWakeUpHour()), 
							Integer.parseInt(dialogSetAlarm.getTxtWakeUpMin()));
					if(dialogSetAlarm.getComboWakeUp() == Calendar.AM)
						timeWakeUp.set(Calendar.AM_PM, Calendar.AM);
					else
						timeWakeUp.set(Calendar.AM_PM, Calendar.PM);
					clock.setEvent(Clock.WAKE_ALARM, eventFactory.getEvent(Clock.WAKE_ALARM, timeWakeUp, true));
				}
				
				// set sleep alarm
				if(!dialogSetAlarm.getTxtSleepHour().equals("") && !dialogSetAlarm.getTxtSleepMin().equals("")) {
					timeSleep = new GregorianCalendar(1, 1, 1,
							Integer.parseInt(dialogSetAlarm.getTxtSleepHour()), 
							Integer.parseInt(dialogSetAlarm.getTxtSleepMin()));
					if(dialogSetAlarm.getComboSleep() == Calendar.AM)
						timeSleep.set(Calendar.AM_PM, Calendar.AM);
					else
						timeSleep.set(Calendar.AM_PM, Calendar.PM);
					clock.setEvent(Clock.SLEEP_ALARM, eventFactory.getEvent(Clock.SLEEP_ALARM, timeSleep, true));
					setAlerts(timeSleep);
				}
				dialogSetAlarm.dispose();
			}
			
			private void setAlerts(GregorianCalendar timeSleep) {
				GregorianCalendar redAlert = new GregorianCalendar(1, 1, 1, 
						timeSleep.get(Calendar.HOUR), timeSleep.get(Calendar.MINUTE) + 10);
				redAlert.set(Calendar.AM_PM, timeSleep.get(Calendar.AM_PM));
				GregorianCalendar yellowAlert = new GregorianCalendar(1, 1, 1, 
						timeSleep.get(Calendar.HOUR), timeSleep.get(Calendar.MINUTE) - 10);
				
				redAlert.set(Calendar.AM_PM, timeSleep.get(Calendar.AM_PM));
				yellowAlert.set(Calendar.AM_PM, timeSleep.get(Calendar.AM_PM));
				clock.setEvent(Clock.RED_ALERT, eventFactory.getEvent(Clock.RED_ALERT, redAlert, true));
				clock.setEvent(Clock.YELLOW_ALERT, eventFactory.getEvent(Clock.YELLOW_ALERT, yellowAlert, true));
			}
		}
	}
	
	class GoingToBedListener implements ActionListener {
		GoingToBedDialog dialogGoingToBed;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogGoingToBed = new GoingToBedDialog(gui.getFrame(), "Going to bed", new ButtonOKListener());
		}
		
		class ButtonOKListener implements ActionListener {
			Event event;
			
			@Override
			public void actionPerformed(ActionEvent e) {
				fileHandler.writeFile("bedTime.txt", clock.getTimeStamp(), true);
				fileHandler.writeFile("bedTime.txt", clock.getTimeStamp(), true);
				if((event = clock.getEvent(Clock.WAKE_ALARM)) != null)
					event.setActive(true);
				if((event = clock.getEvent(Clock.SLEEP_ALARM)) != null)
					event.setActive(false);
				if((event = clock.getEvent(Clock.RED_ALERT)) != null)
					event.setActive(false);
				if((event = clock.getEvent(Clock.YELLOW_ALERT)) != null)
					event.setActive(false);
				dialogGoingToBed.dispose();
			}
		}
	}
	
	class AwakeListener implements ActionListener {
		AwakeDialog dialogAwake;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogAwake = new AwakeDialog(gui.getFrame(), "I am awake", new ButtonOKListener());
		}

		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				fileHandler.writeFile("awakeTime.txt", clock.getTimeStamp(), true);
				fileHandler.writeFile("awakeTime.txt", clock.getTimeStamp(), true);
				dialogAwake.dispose();
			}
		}
	}
	
	class SleepStatusListener implements ActionListener {
		SleepStatusDialog dialogSleepStatus;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogSleepStatus = new SleepStatusDialog(gui.getFrame(), "Check sleep status", new ButtonOKListener());
			String sleepStatus = fileHandler.readFile("status.txt");
			dialogSleepStatus.setTxtReport(sleepStatus);
		}

		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				dialogSleepStatus.dispose();
			}
		}
	}
	
	class SetReportContactListener implements ActionListener {
		ReportContactDialog dialogReportContact;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogReportContact = new ReportContactDialog(gui.getFrame(), "Set report contact", new ButtonOKListener());
		}

		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				String infoContact = "";
				if(dialogReportContact.getTxtName().equals("") || dialogReportContact.getTxtEmail().equals("") || 
						dialogReportContact.getTxtPhone().equals("")) {
					JOptionPane.showMessageDialog(dialogReportContact, "All fields are required", "Error", JOptionPane.ERROR_MESSAGE);
				}
				else {
					infoContact += "Name: "      + dialogReportContact.getTxtName()  + "\n";
					infoContact += "Email: "     + dialogReportContact.getTxtEmail() + "\n";
					infoContact += "Telephone: " + dialogReportContact.getTxtPhone() + "\n";
					fileHandler.writeFile("contactInfo.txt", infoContact, false);
					JOptionPane.showMessageDialog(dialogReportContact, "Data recorded successfully", "Success", JOptionPane.INFORMATION_MESSAGE);
					dialogReportContact.dispose();
				}
				dialogReportContact.dispose();
			}
		}
	}
	
	class EventListener implements ActionListener {
		EventDialog dialogEvent;
		String info;
		Color fontColor;
		
		public EventListener(String info, Color fontColor) {
			super();
			this.info = info;
			this.fontColor = fontColor;
		}
		
		@Override
		public void actionPerformed(ActionEvent e) {
			 dialogEvent = new EventDialog(gui.getFrame(), "Alert", new ButtonOKListener(), info, fontColor);
		}
		
		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				if(dialogEvent.getInfo().equals(Clock.RED_ALERT)) {
					// save information...
				} else if(dialogEvent.getInfo().equals(Clock.YELLOW_ALERT)) {
					// save information...
				}
				dialogEvent.dispose();
			}
		}
	}
}
