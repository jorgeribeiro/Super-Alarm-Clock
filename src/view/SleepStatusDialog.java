package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SleepStatusDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtReport = new JTextArea(10, 20);
	private JScrollPane sp = new JScrollPane(txtReport);

	public SleepStatusDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		setupDialog();
	}
	
	public void setTxtReport(String info) {
		txtReport.setText(info);
	}

	@Override
	public void setupDialog() {
		JPanel contentPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		btnPanel.setLayout(new FlowLayout());
		this.btnOK = new JButton("OK");
		
		setupBtnOK();
		
		contentPanel.add(sp);
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
