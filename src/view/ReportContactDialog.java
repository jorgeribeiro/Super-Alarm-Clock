package view;

import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.Window;

import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class ReportContactDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	private JTextField txtName = new JTextField(15);
	private JTextField txtEmail = new JTextField(15);
	private JTextField txtPhone = new JTextField(15);
	
	public ReportContactDialog(Window owner, String title, ModalityType modalityType) {
		super(owner, title, modalityType);
		setupDialog();
	}
	
	public String getTxtName() {
		return txtName.getText();
	}

	public void setTxtName(String txtName) {
		this.txtName.setText(txtName);
	}

	public String getTxtEmail() {
		return txtEmail.getText();
	}

	public void setTxtEmail(String txtEmail) {
		this.txtEmail.setText(txtEmail);
	}

	public String getTxtPhone() {
		return txtPhone.getText();
	}

	public void setTxtPhone(String txtPhone) {
		this.txtPhone.setText(txtPhone);
	}

	@Override
	public void setupDialog() {
		JPanel contentPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		contentPanel.setLayout(new BorderLayout());
		btnPanel.setLayout(new FlowLayout());
		this.btnOK = new JButton("OK");
		
		JPanel pnlName = new JPanel();
		pnlName.add(new JLabel("Name"));
		pnlName.add(txtName);
		
		JPanel pnlEmail = new JPanel();
		pnlEmail.add(new JLabel("Email"));
		pnlEmail.add(txtEmail);
		
		JPanel pnlPhone = new JPanel();
		pnlPhone.add(new JLabel("Phone"));
		pnlPhone.add(txtPhone);
		
		setupBtnOK();
		
		contentPanel.add(pnlName, BorderLayout.NORTH);
		contentPanel.add(pnlEmail, BorderLayout.CENTER);
		contentPanel.add(pnlPhone, BorderLayout.SOUTH);
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);
	}
}
