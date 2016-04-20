package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class GUI {
	private JFrame frame;
	
	public GUI() {}
	
	public void frameSetup() {
		frame = new JFrame("Super Alarm Clock");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.setLayout(new GridLayout());
		
		JPanel btnPanel = new ButtonPanel();
		JPanel clockPanel = new ClockPanel();
		
		frame.add(btnPanel);
		frame.add(clockPanel);
		
		frame.pack();
		frame.setLocationRelativeTo(null);
		frame.setVisible(true);
	}
	
	class ButtonPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		JButton btnSetAlarm;
		JButton btnGoingToBed;
		JButton btnAwake;
		JButton btnSleepStatus;
		JButton btnSetReportContact;
		JDialog dlgSetAlarm;
		
		public ButtonPanel() {
			setup();
		}
		
		private void setup() {
			btnSetAlarm = new JButton("Set Alarm");
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
			
			btnSetAlarm.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setupSetAlarmDialog();
				}
			});
			
			btnGoingToBed.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			btnAwake.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			btnSleepStatus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
			
			btnSetReportContact.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					
				}
			});
		}
		
		private void setupSetAlarmDialog() {
			NewDialog dialog = new NewDialog();
			JPanel p = new JPanel();
			
			
			
			dialog.setupDialog(p);
		}
	}

	class ClockPanel extends JPanel {
		private static final long serialVersionUID = 1L;
		
		JTextField txtClock;
		Font font;
		
		public ClockPanel() {
			setup();
		}
		
		private void setup() {
			txtClock = new JTextField(7);
			font = new Font("SansSerif", Font.BOLD, 25);
			
			txtClock.setText("9:39am");
			txtClock.setEditable(false);
			txtClock.setHorizontalAlignment(JTextField.CENTER);
			txtClock.setFont(font);
			
			this.add(txtClock);
		}
	}
	
	class NewDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		
		Container container;
		JButton btnOk;
		
		public void setupDialog(JPanel panel) {
			container = this.getContentPane();
			container.setLayout(new BorderLayout());
			container.add(panel, BorderLayout.CENTER);
			container.add(btnOk, BorderLayout.SOUTH);
			
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					dispose();
				}
			});
			
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}
	
}
