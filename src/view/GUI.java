package view;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.GregorianCalendar;

import javax.swing.*;

public class GUI {
	private JFrame frame;
	private static final int ALARM_DIALOG 	= 1;
	private static final int BED_DIALOG 	= 2;
	private static final int AWAKE_DIALOG 	= 3;
	private static final int STATUS_DIALOG 	= 4;
	private static final int REPORT_DIALOG 	= 5;
	
	
	
	
	
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
			NewDialog dialog = new NewDialog(frame, "Set Alarm", Dialog.ModalityType.DOCUMENT_MODAL,ALARM_DIALOG);
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
			dialog.setupDialog(p);
		}
		
		private void setupGoingToBedDialog() {
			NewDialog dialog = new NewDialog(frame, "Going to bed", Dialog.ModalityType.DOCUMENT_MODAL,BED_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			
			p.add(new JLabel("Data recorded! Have a good night."));
			
			dialog.setupDialog(p);
		}
		
		private void setupAwakeDialog() {
			NewDialog dialog = new NewDialog(frame, "I am awake", Dialog.ModalityType.DOCUMENT_MODAL,AWAKE_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			
			p.add(new JLabel("Data recorded! Have a good day."));
			
			dialog.setupDialog(p);
		}
		
		private void setupSleepStatusDialog() {
			NewDialog dialog = new NewDialog(frame, "Sleep status", Dialog.ModalityType.DOCUMENT_MODAL,STATUS_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new GridBagLayout());
			
			p.add(new JTextField(15));
			
			dialog.setupDialog(p);
		}
		
		private void setupSetReportContactDialog() {
			NewDialog dialog = new NewDialog(frame, "Set report contact", Dialog.ModalityType.DOCUMENT_MODAL,REPORT_DIALOG);
			JPanel p = new JPanel();
			p.setLayout(new BorderLayout());
			
			JTextField txtName = new JTextField(15);
			JTextField txtEmail = new JTextField(15);
			JTextField txtPhone = new JTextField(15);
			
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
			SimpleDateFormat dateFormat = new SimpleDateFormat("hh:mm a");
			GregorianCalendar now = new GregorianCalendar();
			txtClock.setText(dateFormat.format(now.getTime()));
			GregorianCalendar oneDayLater = new GregorianCalendar();
			oneDayLater.add(GregorianCalendar.DATE, 1);
			
			ActionListener updateClockAction = new ActionListener() {
				  public void actionPerformed(ActionEvent e) {
					  now.add(GregorianCalendar.MINUTE, 1);
					  txtClock.setText(dateFormat.format(now.getTime()));
				    }
			};
			Timer t = new Timer(1000, updateClockAction);
			t.start();
			
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
		int dialog;
		
		public NewDialog(Window owner, String title, Dialog.ModalityType modalityType,int dialogType) {
			super(owner, title, modalityType);
			dialog = dialogType;
		}
		
		public void setupDialog(JPanel panel) {
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
					if(dialog==ALARM_DIALOG){
						
					}
					if(dialog==BED_DIALOG){
						
					}
					if(dialog==AWAKE_DIALOG){
						
					}
					if(dialog==STATUS_DIALOG){
						
					}
					if(dialog==REPORT_DIALOG){
						System.out.println("Xau");
					}
					dispose();
				}
			});
			
			this.pack();
			this.setLocationRelativeTo(null);
			this.setVisible(true);
		}
	}
	
}
