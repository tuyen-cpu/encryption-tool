package GUI;

import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.MouseInfo;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JLabel;
import javax.swing.Popup;
import javax.swing.PopupFactory;
import javax.swing.Timer;
import javax.swing.border.EmptyBorder;

public class DialogCustom {
	static Timer t;
	static Popup p;

	public static void showShortDialog(Component parent, String text) {
		JLabel lb = new JLabel(text);
		lb.setFont(new Font("Dialog", Font.PLAIN, 12));
		lb.setOpaque(true);
		lb.setBorder(new EmptyBorder(3, 3, 3, 3));
		lb.setForeground(Color.BLACK);
		lb.setBackground(Color.WHITE);
		p = PopupFactory.getSharedInstance().getPopup(parent, lb,
				(int) MouseInfo.getPointerInfo().getLocation().getX(),
				(int) MouseInfo.getPointerInfo().getLocation().getY() - 50);

		p.show();
		t = new Timer(600, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.hide();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	public static void showDescription(Component parent, String text) {
		JLabel lb = new JLabel(text);
		lb.setFont(new Font("Dialog", Font.PLAIN, 12));
		lb.setOpaque(true);
		lb.setBorder(new EmptyBorder(5, 5, 5, 5));
		lb.setForeground(Color.BLACK);
		lb.setBackground(Color.WHITE);
		Point point = parent.getLocationOnScreen();
		p = PopupFactory.getSharedInstance().getPopup(parent, lb,
				(int) point.x, (int) point.y - 32);

		p.show();
		t = new Timer(2000, new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				p.hide();
			}
		});
		t.setRepeats(false);
		t.start();
	}

	public static void stopDialog() {
		p.hide();
		t.stop();
	}
}
