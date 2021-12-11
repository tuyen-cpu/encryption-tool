package GUI;

import java.util.concurrent.TimeUnit;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import com.formdev.flatlaf.FlatIntelliJLaf;
import com.formdev.flatlaf.json.ParseException;

public class Main {
	public static void main(String[] args) throws ParseException {
		try {
			UIManager.setLookAndFeel(new FlatIntelliJLaf());
//			StartGUI start = new StartGUI();
//			MainGUI gui = new MainGUI();
//			start.dispose();
//			gui.createAndShowGUI();

			

			SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					StartGUI start = new StartGUI();
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
