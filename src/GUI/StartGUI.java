package GUI;

import javax.swing.JFrame;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.GridBagLayout;
import java.awt.Toolkit;

import javax.swing.JLabel;

import java.awt.GridBagConstraints;
import java.awt.Font;
import java.awt.BorderLayout;

import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class StartGUI extends JFrame{
	public StartGUI() {
		setSize(400,400);
		getContentPane().setBackground(Color.DARK_GRAY);
		getContentPane().setLayout(new BorderLayout(0, 0));
		
		JLabel lblNewLabel = new JLabel("ENCYPT TOOL",SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("UTM Avo", Font.PLAIN, 30));
		lblNewLabel.setForeground(Color.WHITE);
		getContentPane().add(lblNewLabel, BorderLayout.CENTER);
		
		JLabel lblNewLabel_1 = new JLabel("by Tran Quang Tuyen",SwingConstants.RIGHT);
		lblNewLabel_1.setBorder(new EmptyBorder(10,10,10,10));
		lblNewLabel_1.setForeground(Color.WHITE);
		lblNewLabel_1.setFont(new Font("UTM Avo", Font.PLAIN, 15));
		getContentPane().add(lblNewLabel_1, BorderLayout.SOUTH);
		setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/img/logo.png")));
		setUndecorated(true);
		setResizable(false);
		setVisible(true);
		setLocationRelativeTo(null);
	}

}
