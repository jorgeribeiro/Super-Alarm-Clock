package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

public class SleepStatusDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	private JTextArea txtReport = new JTextArea(20, 40);
	private JScrollPane sp = new JScrollPane(txtReport);

	public SleepStatusDialog(Window owner, String title, ActionListener l) {
		super(owner, title, l);
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
		
		contentPanel.add(sp);
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
