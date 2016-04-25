package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GoingToBedDialog extends Dialog {
	private static final long serialVersionUID = 1L;

	public GoingToBedDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		setupDialog();
	}

	@Override
	public void setupDialog() {
		JPanel contentPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		btnPanel.setLayout(new FlowLayout());
		this.btnOK = new JButton("OK");
		
		setupBtnOK();
		
		contentPanel.add(new JLabel(" Data recorded! Have a good night! "));
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}

}
