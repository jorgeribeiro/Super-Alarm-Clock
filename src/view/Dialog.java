package view;

import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JDialog;

public abstract class Dialog extends JDialog {
	private static final long serialVersionUID = 1L;
	
	public JButton btnOK;
	
	public Dialog(Window owner, String title, Dialog.ModalityType modalityType) {
		super(owner, title, modalityType);
	}
	
	public void setupBtnOK() {
		btnOK.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				// if OK button is pressed close the dialog
				dispose();
			}
		});
	}
	
	public abstract void setupDialog();
}