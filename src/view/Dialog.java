package view;

import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public abstract class Dialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public JButton btnOK;
	
	public Dialog(Window owner, String title, ActionListener l) {
		super(owner, title);
		btnOK = new JButton("OK");
		addBtnOKListener(l);
	}
	
	private void addBtnOKListener(ActionListener l) {
		btnOK.addActionListener(l);
	}
	
	public abstract void setupDialog();
}