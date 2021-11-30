package GUI;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;

import javax.swing.JPanel;
import javax.swing.ImageIcon;

import algorithms.Symmetric;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;

public class MainGUI {
	private JPanel pnMain, pnSymmetric, pnBtnStart, pnAsymmetric, pnPBE,
			pnHash;
	OptionGeneralUI pnOption;
	OptionKeyUI pnKey;
	OptionEncryptUI pnEncrypt;
	private JTabbedPane tabbedPane;
	JButton btnStart;

	public MainGUI() {
		pnBtnStart = new JPanel();
		btnStart = new JButton("Start");
		btnStart.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			}
		});

		btnStart.setPreferredSize(new Dimension(150, 50));
		btnStart.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/start.png")));
		btnStart.setHorizontalAlignment(SwingConstants.LEFT);
		btnStart.setFont(new Font("Dialog", Font.PLAIN, 18));
		btnStart.setFocusPainted(false);
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 13));
		pnSymmetric = new JPanel();
		tabbedPane.addTab("Symmetric", null, pnSymmetric,
				"Symmetric encryption");

		pnAsymmetric = new JPanel();
		tabbedPane.addTab("Asymmetric", null, pnAsymmetric,
				"Asymmetric encryption");

		pnPBE = new JPanel();
		tabbedPane.addTab("PBE", null, pnPBE, null);

		pnHash = new JPanel();
		tabbedPane.addTab("Hash", null, pnHash, null);
		// Create pnOption
		pnOption = new OptionGeneralUI();
		pnSymmetric.add(pnOption);
		// Create pnKey
		pnKey = new OptionKeyUI();
		pnSymmetric.add(pnKey);

		// create pnEncrypt
		pnEncrypt = new OptionEncryptUI();
		pnSymmetric.add(pnEncrypt);
		pnBtnStart.add(btnStart);

	}

	private void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		pnMain = new JPanel();

		pnMain.setPreferredSize(new Dimension(750, 600));
		pnMain.setLayout(new BorderLayout());
		frame.getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.add(tabbedPane, BorderLayout.CENTER);
		pnMain.add(pnBtnStart, BorderLayout.SOUTH);
		// Display the window.
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);
		pnOption.getChoiceAlgorithms().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				pnOption.changeContentChoice();

			}
		});
		pnKey.getBtnCreateKey().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				String algorithm = pnOption.getChoiceAlgorithms()
						.getSelectedItem();
				String mode = pnOption.getChoiceMode().getSelectedItem();
				String padding = pnOption.getChoicePadding().getSelectedItem();
				int keysie = Integer.parseInt(pnOption.getChoiceKeySize()
						.getSelectedItem());
				Symmetric s;
				try {
					s = new Symmetric(algorithm, mode, padding, keysie);
					s.createKey();
					String key = s.getKeyWithString();
					pnKey.setTxtKey(key);
				} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		});
		btnStart.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				String input = pnEncrypt.getTxtPlain();
				if (input.equals("")) {
					JOptionPane.showMessageDialog(null, "Empty input", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
				String algorithm = pnOption.getChoiceAlgorithms()
						.getSelectedItem();
				String mode = pnOption.getChoiceMode().getSelectedItem();
				String padding = pnOption.getChoicePadding().getSelectedItem();
				String outText = "";
				int keysie = Integer.parseInt(pnOption.getChoiceKeySize()
						.getSelectedItem());
				String txtkey = pnKey.getTxtKey();
				try {
					Symmetric s = new Symmetric(algorithm, mode, padding,
							keysie);
					if (!txtkey.equals(""))
						try {
							s.setKey(txtkey);
						} catch (Exception e2) {
							JOptionPane.showMessageDialog(null, "Invalid key",
									"Error", JOptionPane.ERROR_MESSAGE);
							return;
						}
					if (s.getKey() == null) {
						JOptionPane.showMessageDialog(null, "Empty key",
								"Error", JOptionPane.ERROR_MESSAGE);
						return;
					}

					if (pnEncrypt.getPnSelectEnOrDe().getRdEncrypt()
							.isSelected()) {
							outText = s.encrypt(input);
					} else {
						outText = s.decrypt(input);
					}
					pnEncrypt.setTxtCipher(outText);
					;
				} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});

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
