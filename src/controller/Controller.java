package controller;
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
		run();
	}
}
