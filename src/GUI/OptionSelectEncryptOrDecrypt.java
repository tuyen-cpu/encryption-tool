package GUI;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ButtonGroup;
import javax.swing.JPanel;
import javax.swing.JRadioButton;

public class OptionSelectEncryptOrDecrypt extends JPanel {
	private JRadioButton rdEncrypt, rdDecrypt;

	public OptionSelectEncryptOrDecrypt() {
		rdEncrypt = new JRadioButton("Encrypt");
		rdDecrypt = new JRadioButton("Decrypt");
		rdEncrypt.setSelected(true);
		rdEncrypt.setFocusPainted(false);
		rdDecrypt.setFocusPainted(false);
		rdEncrypt.setActionCommand("rdEncrypt");
		rdDecrypt.setActionCommand("rdDecrypt");
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdEncrypt);
		bg.add(rdDecrypt);
		
		rdEncrypt.setPreferredSize(new Dimension(65,20));
		rdDecrypt.setPreferredSize(new Dimension(68,20));
		rdEncrypt.setFont(new Font("Dialog", Font.PLAIN, 12));
		rdDecrypt.setFont(new Font("Dialog", Font.PLAIN, 12));
		add(rdEncrypt);
		add(rdDecrypt);
		
	}

	public JRadioButton getRdEncrypt() {
		return rdEncrypt;
	}

	public void setRdEncrypt(JRadioButton rdEncrypt) {
		this.rdEncrypt = rdEncrypt;
	}

	public JRadioButton getRdDecrypt() {
		return rdDecrypt;
	}

	public void setRdDecrypt(JRadioButton rdDecrypt) {
		this.rdDecrypt = rdDecrypt;
	}


	
}