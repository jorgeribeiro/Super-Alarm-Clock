package view;

import java.awt.*;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

public class GUI {
	private JFrame frame;
	private JPanel btnPanel, clockPanel;
	
	public GUI() {
		frameSetup();
	}
	
	public JFrame getFrame() {
		return frame;
	}
	
	public void addSetAlarmListener(ActionListener l) {
		((ButtonPanel) btnPanel).addSetAlarmListener(l);
	}
	
	public void addGoingToBedListener(ActionListener l) {
		((ButtonPanel) btnPanel).addGoingToBedListener(l);
	}
	
	public void addAwakeListener(ActionListener l) {
		((ButtonPanel) btnPanel).addAwakeListener(l);
	}
	
	public void addSleepStatusListener(ActionListener l) {
		((ButtonPanel) btnPanel).addSleepStatusListener(l);
	}
	
	public void addSetReportContactListener(ActionListener l) {
		((ButtonPanel) btnPanel).addSetReportContactListener(l);
	}
	
	public void updateClockPanel(GregorianCalendar time) {
		((ClockPanel) clockPanel).updateClock(time);
	}

	public void frameSetup() {
		frame = new JFrame("Super Alarm Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		
		btnPanel = new ButtonPanel();
		clockPanel = new ClockPanel();
		
		frame.add(btnPanel);
		frame.add(clockPanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class ButtonPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		private JButton btnSetAlarm;
		private JButton btnGoingToBed;
		private JButton btnAwake;
		private JButton btnSleepStatus;
		private JButton btnSetReportContact;
		
		public ButtonPanel() {
			setup();
		}

		private void setup() {
			btnSetAlarm = new JButton("Set alarm");
			btnGoingToBed = new JButton("Going to bed");
			btnAwake = new JButton("I am awake");
			btnSleepStatus = new JButton("Check sleep status");
			btnSetReportContact = new JButton("Set report contact");
			
			Dimension d = btnSleepStatus.getPreferredSize();
			btnSetAlarm.setPreferredSize(d);
			btnGoingToBed.setPreferredSize(d);
			btnAwake.setPreferredSize(d);
			btnSetReportContact.setPreferredSize(d);
			
			this.add(btnSetAlarm);
			this.add(btnGoingToBed);
			this.add(btnAwake);
			this.add(btnSleepStatus);
			this.add(btnSetReportContact);
			
			this.setLayout(new SpringLayout());
			SpringUtilities.makeCompactGrid(this, 
											5, 1, 
											6, 6, 
											6, 6);
		}
		
		public void addSetAlarmListener(ActionListener l) {
			btnSetAlarm.addActionListener(l);
		}
		
		public void addGoingToBedListener(ActionListener l) {
			btnGoingToBed.addActionListener(l);
		}
		
		public void addAwakeListener(ActionListener l) {
			btnAwake.addActionListener(l);
		}
		
		public void addSleepStatusListener(ActionListener l) {
			btnSleepStatus.addActionListener(l);
		}
		
		public void addSetReportContactListener(ActionListener l) {
			btnSetReportContact.addActionListener(l);
		}
	}

	class ClockPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		JTextField txtClock;
		Font font;
		
		public ClockPanel() {
			txtClock = new JTextField(7);
			font = new Font("SansSerif", Font.BOLD, 25);
			
			txtClock.setEditable(false);
			txtClock.setHorizontalAlignment(JTextField.CENTER);
			txtClock.setFont(font);
			this.add(txtClock);
		}
		
		public void updateClock(GregorianCalendar time) {
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			txtClock.setText(dateFormat.format(time.getTime()));	
		}
	}
	
}