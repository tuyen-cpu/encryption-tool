package GUI;

import java.security.NoSuchAlgorithmException;
import java.text.ParseException;

import javax.crypto.NoSuchPaddingException;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;

public class Main {
	public static void main(String[] args) throws ParseException {

		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					MainGUI gui;
					try {
						gui = new MainGUI();
						gui.createAndShowGUI();
					} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
