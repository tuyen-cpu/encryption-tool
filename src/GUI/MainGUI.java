package GUI;

import javax.crypto.NoSuchPaddingException;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Symmetric;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.security.NoSuchAlgorithmException;

public class MainGUI {

	public static String TAB_SYMMETRIC = "Symmetric";
	public static String TAB_ASYMMETRIC = "Asymmetric";
	public static String TAB_PBE = "PBE";
	public static String TAB_HASH = "HASH";
	private JPanel pnMain, pnSymmetric, pnBtnStart, pnAsymmetric, pnPBE,
			pnHash;
	OptionGeneralUI pnOption;
	OptionKeyUI pnKey;
	OptionEncryptUI pnEncrypt;
	TabHash tabHash;
	private JTabbedPane tabbedPane;
	JButton btnStart;
	String algorithm, mode, padding;
	int keysize;
	Symmetric symmetric;
	String tabbedPaneCurrent;
	String outText, textInput, inputFile, outputFile;

	public MainGUI() throws NoSuchAlgorithmException, NoSuchPaddingException {
		pnBtnStart = new JPanel();
		btnStart = new JButton("Start");
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
		pnHash = new JPanel(new BorderLayout());
		tabbedPane.addTab("Hash", null, pnHash, null);
		tabHash = new TabHash();
		pnHash.add(tabHash, BorderLayout.CENTER);
		pnOption = new OptionGeneralUI();
		pnSymmetric.add(pnOption);
		pnKey = new OptionKeyUI();
		pnSymmetric.add(pnKey);
		pnEncrypt = new OptionEncryptUI();
		pnSymmetric.add(pnEncrypt);
		pnBtnStart.add(btnStart);
		tabbedPaneCurrent = tabbedPane
				.getTitleAt(tabbedPane.getSelectedIndex());
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tabbedPaneCurrent = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				System.out.println("Current tab: " + tabbedPaneCurrent);
			}
		});
		symmetric = new Symmetric("AES", "CBC", "NoPadding", 128);
		symmetric.createKey();
		addHandle();
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main Frame");
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pnBtnStart.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnMain = new JPanel();
		pnMain.setPreferredSize(new Dimension(750, 550));
		pnMain.setLayout(new BorderLayout());
		frame.getContentPane().add(pnMain, BorderLayout.CENTER);
		pnMain.add(tabbedPane, BorderLayout.CENTER);
		pnMain.add(pnBtnStart, BorderLayout.SOUTH);
		// Display the window.
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	public void addHandle() {
		pnOption.getChoiceAlgorithms().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				pnOption.changeContentChoice();

			}
		});
		pnKey.getBtnCreateKey().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addControllOptionSymmetricTab();
				try {
					symmetric = new Symmetric(algorithm, mode, padding, keysize);
					symmetric.createKey();
					String key = symmetric.getKeyWithString();
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
				switch (tabbedPaneCurrent) {
				case "Symmetric":
					System.out.println("Handle tab Symmetric");
					handleTabSymmetric();
					break;
				case "Asymmetric":
					System.out.println("Handle tab Asymmetric");
					handleTabAsymmetric();
					break;
				case "PBE":
					System.out.println("Handle tab PBE");
					handleTabPBE();
					break;
				case "Hash":
					System.out.println("Handle tab Hash");
					handleTabHash();
					break;
				default:
					System.out.println("Handle tab Symmetric");
					handleTabSymmetric();
					break;
				}
			}
		});
	}

	public void handleTabPBE() {

	}

	public void handleTabHash() {

	}

	public void handleTabAsymmetric() {

	}

	public void handleTabSymmetric() {
		textInput = pnEncrypt.getTxtPlain();
		if (textInput.equals("") && pnEncrypt.getRdField().isSelected()) {
			JOptionPane.showMessageDialog(null, "Empty text input", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		String txtkey = pnKey.getTxtKey();
		if (txtkey.equalsIgnoreCase("") || txtkey == null) {
			JOptionPane.showMessageDialog(null, "Empty key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (algorithm == null)
			addControllOptionSymmetricTab();
		outText = "";
		keysize = Integer.parseInt(pnOption.getChoiceKeySize()
				.getSelectedItem());
		try {
			symmetric = new Symmetric(algorithm, mode, padding, keysize);
			if (!txtkey.equals(""))
				try {
					symmetric.setKey(txtkey);
				} catch (Exception e2) {
					JOptionPane.showMessageDialog(null, "Invalid key", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}
			if (symmetric.getKey() == null) {
				JOptionPane.showMessageDialog(null, "Empty key", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}

			if (pnEncrypt.getPnSelectEnOrDe().getRdEncrypt().isSelected()) {
				if (pnEncrypt.getRdFile().isSelected()) {
					System.out.println("Encrypt with string");
					inputFile = pnEncrypt.getLblFileInput().getText();
					outputFile = pnEncrypt.getLblFileOutput().getText();
					symmetric.encrypt(inputFile, outputFile);
					JOptionPane.showMessageDialog(null, "Mã hóa thành công",
							"Success", JOptionPane.OK_OPTION);
				} else {
					System.out.println("Encrypt with file");
					outText = symmetric.encrypt(textInput);
				}
			} else {
				if (pnEncrypt.getRdFile().isSelected()) {
					inputFile = pnEncrypt.getLblFileInput().getText();
					outputFile = pnEncrypt.getLblFileOutput().getText();
					symmetric.decrypt(inputFile, outputFile);
					System.out.println("Decrypt with string");
					JOptionPane.showMessageDialog(null, "Giải mã thành công",
							"Success", JOptionPane.OK_OPTION);
				} else {
					System.out.println("Decrypt with string");
					outText = symmetric.decrypt(textInput);
				}
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

	/* add option control for handle */
	public void addControllOptionSymmetricTab() {
		algorithm = pnOption.getChoiceAlgorithms().getSelectedItem();
		mode = pnOption.getChoiceMode().getSelectedItem();
		padding = pnOption.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt(pnOption.getChoiceKeySize()
				.getSelectedItem());
	}

}
