package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

@SuppressWarnings({ "unchecked", "rawtypes" })
public class SetAlarmDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	private String[] times = {"am", "pm"};
	private JTextField txtWakeUpHour = new JTextField(2);
	private JTextField txtWakeUpMin = new JTextField(3);
	private JTextField txtSleepHour = new JTextField(2);
	private JTextField txtSleepMin = new JTextField(3);
	private JComboBox comboWakeUp = new JComboBox(times);
	private JComboBox comboSleep = new JComboBox(times);
	private JCheckBox cbWakeUp = new JCheckBox("ON/OFF");
	private JCheckBox cbSleep = new JCheckBox("ON/OFF");

	public SetAlarmDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		setupDialog();
	}
	
	public String getTxtWakeUpHour() {
		return txtWakeUpHour.getText();
	}

	public void setTxtWakeUpHour(String txtWakeUpHour) {
		this.txtWakeUpHour.setText(txtWakeUpHour);
	}

	public String getTxtWakeUpMin() {
		return txtWakeUpMin.getText();
	}

	public void setTxtWakeUpMin(String txtWakeUpMin) {
		this.txtWakeUpMin.setText(txtWakeUpMin);
	}

	public String getTxtSleepHour() {
		return txtSleepHour.getText();
	}

	public void setTxtSleepHour(String txtSleepHour) {
		this.txtSleepHour.setText(txtSleepHour);
	}

	public String getTxtSleepMin() {
		return txtSleepMin.getText();
	}

	public void setTxtSleepMin(String txtSleepMin) {
		this.txtSleepMin.setText(txtSleepMin);;
	}

	public int getComboWakeUp() {
		return comboWakeUp.getSelectedIndex();
	}

	public int getComboSleep() {
		return comboSleep.getSelectedIndex();
	}

	public boolean getCbWakeUp() {
		return cbWakeUp.isSelected();
	}

	public boolean getCbSleep() {
		return cbSleep.isSelected();
	}

	@Override
	public void setupDialog() {
		JPanel contentPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		btnPanel.setLayout(new FlowLayout());
		this.btnOK = new JButton("OK");
		
		// wake up options
		JPanel pnlWakeUp = new JPanel();
		pnlWakeUp.add(new JLabel("Wake up"));
		pnlWakeUp.add(txtWakeUpHour);
		pnlWakeUp.add(new JLabel(":"));
		pnlWakeUp.add(txtWakeUpMin);
		pnlWakeUp.add(comboWakeUp);
		pnlWakeUp.add(cbWakeUp);
		// sleep options
		JPanel pnlSleep = new JPanel();
		pnlSleep.add(new JLabel("      Sleep"));
		pnlSleep.add(txtSleepHour);
		pnlSleep.add(new JLabel(":"));
		pnlSleep.add(txtSleepMin);
		pnlSleep.add(comboSleep);
		pnlSleep.add(cbSleep);
		comboSleep.setSelectedIndex(1);
		setupBtnOK();
		
		contentPanel.add(pnlWakeUp, BorderLayout.CENTER);
		contentPanel.add(pnlSleep, BorderLayout.SOUTH);
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
