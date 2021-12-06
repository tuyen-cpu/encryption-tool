package GUI;

import java.text.ParseException;
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
				
						gui = new MainGUI();
						gui.createAndShowGUI();
					
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}
		

	}
}
