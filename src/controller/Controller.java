package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import event.EventFactory;
import model.*;
import persistency.FileHandler;
import view.*;

public class Controller {
	private static GUI gui;
	private static Clock clock;
	//private static Student user; 	   // not used yet
	//private static Contact contact;  // not used yet
	private static FileHandler fileHandler;
	private static EventFactory eventFactory;
	
	public Controller() {
		clock = new Clock();
		eventFactory = new EventFactory();
		//user = new Student();
		//contact = new Contact();
		fileHandler = new FileHandler();
		gui = new GUI(this);
    	setupClock();
    	
    	gui.addSetAlarmListener(new SetAlarmListener());
    	gui.addGoingToBedListener(new GoingToBedListener());
    	gui.addAwakeListener(new AwakeListener());
    	gui.addSleepStatusListener(new SleepStatusListener());
    	gui.addSetReportContactListener(new SetReportContactListener());
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
									gui.setupEventDialog(info); // check alarms every update
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
	
	public void saveContactTxt(String info){
		fileHandler.writeFile("dataTxt/contact.txt",info);
	}
	
	public String retrieveData(String fileName){
		return fileHandler.readFile(fileName);
	}
	
	public static void main(String[] args) {
		new Controller();
	}
	
	/**
	 *  refactoring the MVC - Jorge
	 */
	class SetAlarmListener implements ActionListener {
		SetAlarmDialog dialogSetAlarm;
		
		@Override
		public void actionPerformed(ActionEvent arg0) {
			dialogSetAlarm = new SetAlarmDialog(gui.getFrame(), "Set alarm", new ButtonOKListener());
		}
		
		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				setAlarm();
				dialogSetAlarm.dispose();
			}
			
			private void setAlarm() {
				GregorianCalendar timeWakeUp;
				GregorianCalendar timeSleep;
				// set wake up alarm
				if(dialogSetAlarm.isCbWakeUpSelected()) {
					timeWakeUp = new GregorianCalendar(1, 1, 1,
							Integer.parseInt(dialogSetAlarm.getTxtWakeUpHour()), 
							Integer.parseInt(dialogSetAlarm.getTxtWakeUpMin()));
					if(dialogSetAlarm.getComboWakeUp() == Calendar.AM)
						timeWakeUp.set(Calendar.AM_PM, Calendar.AM);
					else
						timeWakeUp.set(Calendar.AM_PM, Calendar.PM);
					clock.setEvent(Clock.WAKE_ALARM, eventFactory.getEvent(Clock.WAKE_ALARM, timeWakeUp));
				}
				
				// set sleep alarm
				if(dialogSetAlarm.isCbSleepSelected()) {
					timeSleep = new GregorianCalendar(1, 1, 1,
							Integer.parseInt(dialogSetAlarm.getTxtSleepHour()), 
							Integer.parseInt(dialogSetAlarm.getTxtSleepMin()));
					if(dialogSetAlarm.getComboSleep() == Calendar.AM)
						timeSleep.set(Calendar.AM_PM, Calendar.AM);
					else
						timeSleep.set(Calendar.AM_PM, Calendar.PM);
					clock.setEvent(Clock.SLEEP_ALARM, eventFactory.getEvent(Clock.SLEEP_ALARM, timeSleep));
				}
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
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK Going to bed Pressed");
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
				System.out.println("OK I am awake Pressed");
			}
		}
	}
	
	class SleepStatusListener implements ActionListener {
		SleepStatusDialog dialogSleepStatus;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogSleepStatus = new SleepStatusDialog(gui.getFrame(), "Check sleep status", new ButtonOKListener());
		}

		class ButtonOKListener implements ActionListener {
			@Override
			public void actionPerformed(ActionEvent e) {
				System.out.println("OK Sleep status Pressed");
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
				System.out.println("OK Report contact Pressed");
			}
		}
	}
}
