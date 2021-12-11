package algorithms;

import java.awt.Color;
import java.util.concurrent.TimeUnit;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;



public class Test {
	public static void main(String[] args) {
		Gif.getIconLoading();
		try {
			TimeUnit.SECONDS.sleep(4);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Gif.closeLoading();
	}
}
