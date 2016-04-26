package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Window;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class EventDialog extends Dialog {
	private static final long serialVersionUID = 1L;
	
	String info;
	Color color;
	
	public EventDialog(Window owner, String title, ActionListener l, String info, Color color) {
		super(owner, title, l);
		this.info = info;
		this.color = color;
		setupDialog();
	}

	@Override
	public void setupDialog() {
		JPanel contentPanel = new JPanel();
		JPanel btnPanel = new JPanel();
		contentPanel.setLayout(new GridBagLayout());
		btnPanel.setLayout(new FlowLayout());
		
		JLabel infoLabel = new JLabel(info);
		infoLabel.setForeground(color);
		contentPanel.add(infoLabel);
		btnPanel.add(btnOK);
		
		this.add(contentPanel, BorderLayout.CENTER);
		this.add(btnPanel, BorderLayout.SOUTH);
		this.pack();
		this.setLocationRelativeTo(null);
		this.setVisible(true);

	}
	
	
}
