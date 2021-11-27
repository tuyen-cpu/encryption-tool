package GUI;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JTabbedPane;

import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Dimension;

import java.awt.Font;

import javax.swing.JPanel;

public class MainGUI {
	private JPanel pnSymmetric, pnAsymmetric, pnPBE, pnHash;
	private JTabbedPane tabbedPane;
	private OptionPanel pnOption;
	private OptionKey pnKey;

	public MainGUI() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN,13));
		pnSymmetric = new JPanel();
		tabbedPane.addTab("Symmetric", null, pnSymmetric, "Symmetric encryption");

		pnAsymmetric = new JPanel();
		tabbedPane.addTab("Asymmetric", null, pnAsymmetric, "Asymmetric encryption");

		pnPBE = new JPanel();
		tabbedPane.addTab("PBE", null, pnPBE, null);

		pnHash = new JPanel();
		tabbedPane.addTab("Hash", null, pnHash, null);
		//Create pnOption
		pnOption = new OptionPanel();
		pnSymmetric.add(pnOption);
		//Create pnKey
		pnKey = new OptionKey();
		pnSymmetric.add(pnKey);
		
		
		

	}

	private void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		JLabel pnMain = new JLabel();

		pnMain.setPreferredSize(new Dimension(750, 600));
		pnMain.setLayout(new BorderLayout());
		frame.getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.add(tabbedPane, BorderLayout.CENTER);
		// Display the window.
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	public static void main(String[] args) {

		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {
			e.printStackTrace();
		}
		// try{
		// for(LookAndFeelInfo info:UIManager.getInstalledLookAndFeels()){
		// /* - Nimbus
		// * - Metal
		// * - Window
		// * - CDE/Motif
		// * */
		//
		// if("Windows".equals(info.getName())){
		// UIManager.setLookAndFeel(info.getClassName());
		// break;
		// }
		// }
		// }
		// catch(Exception e){
		// e.printStackTrace();
		// }
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				MainGUI gui = new MainGUI();
				gui.createAndShowGUI();
			}
		});

	}

}
