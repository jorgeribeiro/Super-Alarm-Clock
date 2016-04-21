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
	private Controller ctrl;
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
					setupGoingToBedDialog();
				}
			});
			
			btnAwake.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setupAwakeDialog();
				}
			});
			
			btnSleepStatus.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setupSleepStatusDialog();
				}
			});
			
			btnSetReportContact.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent arg0) {
					setupSetReportContactDialog();
				}
			});
		}
		
		@SuppressWarnings({ "unchecked", "rawtypes" })
		private void setupSetAlarmDialog() {
			NewDialog dialog = new NewDialog(frame, "Set Alarm", Dialog.ModalityType.DOCUMENT_MODAL, ALARM_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			
			String[] times = {"am", "pm"};
			JTextField txtWakeUpH = new JTextField(2);
			JTextField txtWakeUpM = new JTextField(3);
			JTextField txtSleepH = new JTextField(2);
			JTextField txtSleepM = new JTextField(3);
			JComboBox comboWakeUp = new JComboBox(times);
			JComboBox comboSleep = new JComboBox(times);
			comboSleep.setSelectedIndex(1);
			JCheckBox cbWakeUp = new JCheckBox("ON/OFF");
			JCheckBox cbSleep = new JCheckBox("ON/OFF");
			
			// wake up options
			JPanel pnlWakeUp = new JPanel();
			pnlWakeUp.add(new JLabel("Wake up"));
			pnlWakeUp.add(txtWakeUpH);
			pnlWakeUp.add(new JLabel(":"));
			pnlWakeUp.add(txtWakeUpM);
			pnlWakeUp.add(comboWakeUp);
			pnlWakeUp.add(cbWakeUp);
			// sleep options
			JPanel pnlSleep = new JPanel();
			pnlSleep.add(new JLabel("      Sleep"));
			pnlSleep.add(txtSleepH);
			pnlSleep.add(new JLabel(":"));
			pnlSleep.add(txtSleepM);
			pnlSleep.add(comboSleep);
			pnlSleep.add(cbSleep);
			
			p.add(pnlWakeUp, BorderLayout.CENTER);
			p.add(pnlSleep, BorderLayout.SOUTH);
			Component[] components = p.getComponents();
			dialog.setupDialog(p, components);
		}
		
		private void setupGoingToBedDialog() {
			NewDialog dialog = new NewDialog(frame, "Going to bed", Dialog.ModalityType.DOCUMENT_MODAL, BED_DIALOG);
			JPanel p = new JPanel();
			
			p.setLayout(new GridBagLayout());
			p.add(new JLabel(" Data recorded! Have a good night. "));
			dialog.setupDialog(p, null);
		}
		
		private void setupAwakeDialog() {
			NewDialog dialog = new NewDialog(frame, "I am awake", Dialog.ModalityType.DOCUMENT_MODAL, AWAKE_DIALOG);
			JPanel p = new JPanel();
			
			p.setLayout(new GridBagLayout());
			p.add(new JLabel(" Data recorded! Have a good day. "));
			dialog.setupDialog(p, null);
		}
		
		private void setupSleepStatusDialog() {
			NewDialog dialog = new NewDialog(frame, "Sleep status", Dialog.ModalityType.DOCUMENT_MODAL, STATUS_DIALOG);
			JPanel p = new JPanel();
			
			p.setLayout(new GridBagLayout());
			JTextArea area = new JTextArea(10,20);
			JScrollPane sp = new JScrollPane(area); 
			
			p.add(sp);
			
			String out = "";
			
			out = ctrl.retrieveData("status.txt");
			if(out.equals("")){
				out+="No status available for now!";
			}
			
			area.setText(out);
			
			dialog.setupDialog(p,null);
		}
		
		private void setupSetReportContactDialog() {
			NewDialog dialog = new NewDialog(frame, "Set report contact", Dialog.ModalityType.DOCUMENT_MODAL, REPORT_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			
			//ArrayList JtextField
			ArrayList <JTextField> listText = new ArrayList<JTextField>(); 
			JTextField txtName = new JTextField(15);
			JTextField txtEmail = new JTextField(15);
			JTextField txtPhone = new JTextField(15);
			listText.add(txtName);
			listText.add(txtEmail);
			listText.add(txtPhone);
			
			// name options
			JPanel pnlName = new JPanel();
			pnlName.add(new JLabel("Name"));
			pnlName.add(txtName);
			// email options
			JPanel pnlEmail = new JPanel();
			pnlEmail.add(new JLabel("Email"));
			pnlEmail.add(txtEmail);
			// phone options
			JPanel pnlPhone = new JPanel();
			pnlPhone.add(new JLabel("Phone"));
			pnlPhone.add(txtPhone);
			
			p.add(pnlName, BorderLayout.NORTH);
			p.add(pnlEmail, BorderLayout.CENTER);
			p.add(pnlPhone, BorderLayout.SOUTH);
			dialog.setupDialog(p,listText);
		}
	}

	public void setupEventDialog(String info) {
		NewDialog dialog = new NewDialog(frame, "Alert", Dialog.ModalityType.DOCUMENT_MODAL, EVENT_DIALOG);
		JPanel p = new JPanel();
		
		p.setLayout(new GridBagLayout());
		p.add(new JLabel(info));
		dialog.setupDialog(p, null);
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
