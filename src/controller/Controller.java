package controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.GregorianCalendar;

import javax.swing.Timer;

import model.*;
import persistency.FileHandler;
import view.GUI;

public class Controller {
	private static GUI gui;
	private static Clock clock;
	private static Student user;
	private static Contact contact;
	private static FileHandler fileHandler;
	
	public Controller() {
		gui = new GUI();
		clock = new Clock();
		user = new Student();
		contact = new Contact();
		fileHandler = new FileHandler();
		
		gui.frameSetup();
		setupClock();
	}
	
	private void setupClock() {
		gui.updateClockPanel(clock.getTime()); // show machine time
		ActionListener updateClockAction = new ActionListener() {
			  public void actionPerformed(ActionEvent e) {
				  clock.addTime(GregorianCalendar.MINUTE, 1);
				  gui.updateClockPanel(clock.getTime());
				  // test alarms every update
				  String info = clock.testEvents();
				  if(info.length() > 0) {
					  gui.setupEventDialog(info);
				  }
			  }
		};
		Timer t = new Timer(1000, updateClockAction); // keep counting
		t.start();
	}
	
	public static void main(String[] args) {
		new Controller();
	}
}
