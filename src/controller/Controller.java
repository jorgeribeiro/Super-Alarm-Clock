package controller;

import java.text.DateFormat;
import java.util.GregorianCalendar;

import model.*;
import persistency.FileHandler;
import view.GUI;

public class Controller {
	private static GUI gui;
	private static Clock clock;
	private static Student user;
	private static Contact contact;
	private static FileHandler fileHandler;
	
	public static void run() {
		gui = new GUI();
		gui.frameSetup();
	}
	
	public static void main(String[] args) {
		//run();
		DateFormat df = DateFormat.getDateInstance(DateFormat.SHORT);
		GregorianCalendar now = new GregorianCalendar();
		System.out.println(df.format(now.getTime()));
		GregorianCalendar oneDayLater = new GregorianCalendar();
		oneDayLater.add(GregorianCalendar.DATE, 1);
		System.out.println(df.format(oneDayLater.getTime()));
		while(now.before(oneDayLater)) {
			now.add(GregorianCalendar.MINUTE, 60);
			System.out.println(now.getTime());
		}
		
	}
}
