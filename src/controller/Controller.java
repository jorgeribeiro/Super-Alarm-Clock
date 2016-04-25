package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.SwingUtilities;
import javax.swing.Timer;

import model.*;
import persistency.FileHandler;
import view.*;

public class Controller {
	private static GUI gui;
	private static Clock clock;
	//private static Student user; 	   // not used yet
	//private static Contact contact;  // not used yet
	private static FileHandler fileHandler;
	
	public Controller() {
		clock = new Clock();
		//user = new Student();
		//contact = new Contact();
		fileHandler = new FileHandler();
		gui = new GUI(this, clock);
    	setupClock();
    	
    	gui.addSetAlarmListener(new SetAlarmListener());
    	gui.addGoingToBedListener(new GoingToBedListener());
    	gui.addAwakeListener(new AwakeListener());
    	gui.addSleepStatusListener(new SleepStatusListener());
    	gui.addSetReportContactListener(new SetReportContactListener());
	}
	
	/**
	 * TODO
	 * improve thread (too big)
	 */
	private void setupClock() {
		gui.updateClockPanel(clock.getTime()); // show machine time
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
			dialogSetAlarm = new SetAlarmDialog(gui.getFrame(), "Set alarm", Dialog.ModalityType.DOCUMENT_MODAL);
			// if button ok is pressed do the work
			// why isActive() is working for this functionality? I have no idea
			if(dialogSetAlarm.isActive()) { System.out.println("Set alarm Closed");	}
		}
	}
	
	class GoingToBedListener implements ActionListener {
		GoingToBedDialog dialogGoingToBed;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogGoingToBed = new GoingToBedDialog(gui.getFrame(), "Going to bed", Dialog.ModalityType.DOCUMENT_MODAL);
			if(dialogGoingToBed.isActive()) { System.out.println("Going to bed Closed"); }
		}
	}
	
	class AwakeListener implements ActionListener {
		AwakeDialog dialogAwake;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogAwake = new AwakeDialog(gui.getFrame(), "I am awake", Dialog.ModalityType.DOCUMENT_MODAL);
			if(dialogAwake.isActive()) { System.out.println("Awake Closed"); }
		}
	}
	
	class SleepStatusListener implements ActionListener {
		SleepStatusDialog dialogSleepStatus;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogSleepStatus = new SleepStatusDialog(gui.getFrame(), "Check sleep status", Dialog.ModalityType.DOCUMENT_MODAL);
			if(dialogSleepStatus.isActive()) { System.out.println("Sleep status Closed"); }
		}
	}
	
	class SetReportContactListener implements ActionListener {
		ReportContactDialog dialogReportContact;
		@Override
		public void actionPerformed(ActionEvent e) {
			dialogReportContact = new ReportContactDialog(gui.getFrame(), "Set report contact", Dialog.ModalityType.DOCUMENT_MODAL);
			if(dialogReportContact.isActive()) { System.out.println("Set report contact Closed"); }
		}
	}
}
