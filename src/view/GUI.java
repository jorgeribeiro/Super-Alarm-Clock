package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import javax.swing.*;

import controller.Controller;
import model.Clock;

public class GUI {
	private JFrame frame;
	private JPanel btnPanel, clockPanel;
	private Controller ctrl; // get rid	
	private Clock clock;
	
	private static final int ALARM_DIALOG 	= 1;
	private static final int BED_DIALOG 	= 2;
	private static final int AWAKE_DIALOG 	= 3;
	private static final int STATUS_DIALOG 	= 4;
	private static final int REPORT_DIALOG 	= 5;
	private static final int EVENT_DIALOG   = 6;
	
	public GUI(Controller ctrl, Clock clock) {
		this.ctrl = ctrl;
		this.clock = clock;
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
	
	public void updateClockPanel(GregorianCalendar time) {
		((ClockPanel) clockPanel).updateClock(time);
	}
	
	public void setupEventDialog(String info) {
		NewDialog dialog = new NewDialog(frame, "Alert", Dialog.ModalityType.DOCUMENT_MODAL, EVENT_DIALOG);
		JPanel p = new JPanel();
		
		p.setLayout(new GridBagLayout());
		p.add(new JLabel(info));
		dialog.setupDialog(p, null);
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
	
	class NewDialog extends JDialog {
		private static final long serialVersionUID = 1L;
		
		Container container;
		JButton btnOk;
		int dialog;
		
		public NewDialog(Window owner, String title, Dialog.ModalityType modalityType,int dialogType) {
			super(owner, title, modalityType);
			dialog = dialogType;
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		public void setupDialog(JPanel panel, Object data) {
			container = this.getContentPane();
			btnOk = new JButton("OK");
			JPanel btn = new JPanel();
			btn.setLayout(new FlowLayout());
			btn.add(btnOk);
			container.add(panel, BorderLayout.CENTER);
			container.add(btn, BorderLayout.SOUTH);
			
			btnOk.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					if(dialog == ALARM_DIALOG) {
						Component[] components = (Component[]) data;
						JPanel pnlWakeUp = (JPanel) components[0];
						JTextField txtWakeUpH = (JTextField) pnlWakeUp.getComponent(1);
						JTextField txtWakeUpM = (JTextField) pnlWakeUp.getComponent(3);
						JComboBox comboWakeUp = (JComboBox) pnlWakeUp.getComponent(4);
						JCheckBox cbWakeUp = (JCheckBox) pnlWakeUp.getComponent(5);
						if(cbWakeUp.isSelected()) {
							GregorianCalendar time = new GregorianCalendar(1, 1, 1, 
									Integer.parseInt(txtWakeUpH.getText()), Integer.parseInt(txtWakeUpM.getText()));
							if(comboWakeUp.getSelectedIndex() == Calendar.AM)
								time.set(Calendar.AM_PM, Calendar.AM);
							else
								time.set(Calendar.AM_PM, Calendar.PM);
							clock.setAlarm(Clock.WAKE_ALARM, time);
						}
						
						JPanel pnlSleep = (JPanel) components[1];
						JTextField txtSleepH = (JTextField) pnlSleep.getComponent(1);
						JTextField txtSleepM = (JTextField) pnlSleep.getComponent(3);
						JComboBox comboSleep = (JComboBox) pnlSleep.getComponent(4);
						JCheckBox cbSleep = (JCheckBox) pnlSleep.getComponent(5);
						if(cbSleep.isSelected()) {
							GregorianCalendar time = new GregorianCalendar(1, 1, 1, 
									Integer.parseInt(txtSleepH.getText()), Integer.parseInt(txtSleepM.getText()));
							if(comboSleep.getSelectedIndex() == Calendar.AM)
								time.set(Calendar.AM_PM, Calendar.AM);
							else
								time.set(Calendar.AM_PM, Calendar.PM);
							clock.setAlarm(Clock.SLEEP_ALARM, time);
						}
						dispose();
					}
					if(dialog == BED_DIALOG) {
						dispose();
					}
					if(dialog == AWAKE_DIALOG) {
						dispose();
					}
					if(dialog == STATUS_DIALOG) {
						dispose();
					}
					if(dialog == REPORT_DIALOG){
						String info = "";
						ArrayList<JTextField> aux = (ArrayList<JTextField>) data;
						if( aux.get(0).getText().equals("")||aux.get(1).getText().equals("")||aux.get(2).getText().equals("")){
							JOptionPane.showMessageDialog(frame, "All fields are required","Error",JOptionPane.ERROR_MESSAGE);
						}
						else{
							info+="Name: " + aux.get(0).getText() + "\n";
							info+="Email: "+ aux.get(1).getText() + "\n";
							info+="Telephone: " + aux.get(2).getText() + "\n";
							ctrl.saveContactTxt(info);
							JOptionPane.showMessageDialog(frame, "Data recorded successfully","Success",JOptionPane.INFORMATION_MESSAGE);
							dispose();
						}
					}
					if(dialog==EVENT_DIALOG){
						dispose();
					}
					//dispose();
				}
			});
			
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
		
	}
	
}