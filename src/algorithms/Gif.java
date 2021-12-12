package algorithms;

import java.awt.Color;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.WindowConstants;

import com.sun.java.swing.plaf.windows.resources.windows;

public class Gif extends Thread{
	public static ImageIcon icon;
	public static JDialog dialog;
	public static  JFrame frame;

	public static void closeLoading() {
		System.out.println("done...");
		dialog.dispose();
		return;
	}

	public static void getIconLoading(JFrame framePrent) {
		System.out.println("load...");
		icon = new ImageIcon(Gif.class.getResource("/img/loading.gif"));
		dialog = new JDialog(framePrent);
		dialog.getContentPane().setBackground(new Color(0, 0, 0, 0));
		dialog.setUndecorated(true);
		JLabel label = new JLabel(icon);
		label.setBackground(new Color(0, 0, 0, 0));
		dialog.add(label);
		dialog.pack();
		dialog.setLocationRelativeTo(null);
		dialog.setVisible(true);
//		 frame = new JFrame();
//		icon = new ImageIcon(Gif.class.getResource("/img/loading.gif"));
//		JLabel label = new JLabel(icon);
//		frame.setUndecorated(true);
//		frame.add(label);
//		frame.pack();
//		frame.setLocationRelativeTo(null);
//		frame.setVisible(true);
//		frame.setDefaultCloseOperation(WindowConstants.DISPOSE_ON_CLOSE);
	}
	
}
