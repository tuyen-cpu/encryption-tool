package GUI;

import java.awt.Color;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.json.ParseException;

public class Main {

	public static void main(String[] args) throws ParseException {
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
//			UIManager.setLookAndFeel("com.formdev.flatlaf.intellijthemes.materialthemeuilite.FlatDraculaIJTheme");
			
			StartGUI  start= new StartGUI();
			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					
					MainGUI gui = new MainGUI();
					start.dispose();
					gui.createAndShowGUI();
				}
			});
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
