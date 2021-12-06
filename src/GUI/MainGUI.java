package GUI;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Asymmetric;
import algorithms.Hash;
import algorithms.Symmetric;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;

import javax.swing.BoxLayout;

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
	Asymmetric asymmetric;
	String tabbedPaneCurrent;
	String outText, textInput, inputFile, outputFile;
	TabAsymmetric tabAsymmetric;

	public MainGUI() {
		createComponent();
		setFontComponent();
		btnStart.setFocusPainted(false);
		addComponent();
		tabbedPaneCurrent = tabbedPane
				.getTitleAt(tabbedPane.getSelectedIndex());

		addAlgorithmsAllTab();

		addHandle();
	}

	public void addAlgorithmsAllTab() {
		try {
			symmetric = new Symmetric("AES", "CBC", "NoPadding", 128);
			symmetric.createKey();
			asymmetric = new Asymmetric("RSA", "ECB", "PKCS1Padding", 515);
			asymmetric.genkey();
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	public void setFontComponent() {
		btnStart.setFont(new Font("Dialog", Font.PLAIN, 18));
		tabbedPane.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnStart.setPreferredSize(new Dimension(150, 50));
		btnStart.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/start.png")));
		btnStart.setHorizontalAlignment(SwingConstants.LEFT);
		pnBtnStart.setBorder(new EmptyBorder(0, 0, 10, 0));
		pnMain.setPreferredSize(new Dimension(750, 600));
	}

	public void addComponent() {
		tabbedPane.addTab("Symmetric", null, pnSymmetric,
				"Symmetric encryption");
		tabbedPane.addTab("Asymmetric", null, pnAsymmetric,
				"Asymmetric encryption");
		tabbedPane.addTab("PBE", null, pnPBE, null);
		tabbedPane.addTab("Hash", null, pnHash, null);
		pnHash.add(tabHash, BorderLayout.CENTER);
		pnAsymmetric.add(tabAsymmetric, BorderLayout.CENTER);
		pnSymmetric.setLayout(new BoxLayout(pnSymmetric, BoxLayout.Y_AXIS));
		pnSymmetric.add(pnOption);
		pnSymmetric.add(pnKey);
		pnSymmetric.add(pnEncrypt);
		pnBtnStart.add(btnStart);
		pnMain.add(tabbedPane, BorderLayout.CENTER);
		pnMain.add(pnBtnStart, BorderLayout.SOUTH);

	}

	public void createComponent() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);

		pnBtnStart = new JPanel();
		btnStart = new JButton("Start");
		pnSymmetric = new JPanel();
		pnAsymmetric = new JPanel(new BorderLayout());
		pnPBE = new JPanel();
		pnHash = new JPanel(new BorderLayout());
		pnOption = new OptionGeneralUI();
		pnKey = new OptionKeyUI();
		pnEncrypt = new OptionEncryptUI();

		tabAsymmetric = new TabAsymmetric();
		tabHash = new TabHash();
		pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
	}

	public void createAndShowGUI() {
		// Create and set up the window.
		JFrame frame = new JFrame("Main Frame");
		
       
		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(getClass().getResource("/img/logo.png")));
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().add(pnMain, BorderLayout.CENTER);
		// Display the window.
		frame.pack();
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setLocationRelativeTo(null);

	}

	public void addHandle() {
		tabbedPane.addChangeListener(new ChangeListener() {
			public void stateChanged(ChangeEvent e) {
				tabbedPaneCurrent = tabbedPane.getTitleAt(tabbedPane
						.getSelectedIndex());
				System.out.println("Current tab: " + tabbedPaneCurrent);

			}
		});
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
		tabAsymmetric.getBtnCreateKey().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addControllOptionAsymmetricTab();
				try {
					tabAsymmetric.getTxtPrivateKey().setText("");
					tabAsymmetric.getTxtPublicKey().setText("");

					asymmetric = new Asymmetric(algorithm, mode, padding,
							keysize);
					asymmetric.genkey();
					tabAsymmetric.getTxtPrivateKey().setText(
							asymmetric.getPrivateKeyWithString());
					tabAsymmetric.getTxtPublicKey().setText(
							asymmetric.getPublicKeyWithString());

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

	public void handleTabHash() {
		if (tabHash.getRdString().isSelected()) {
			System.out.println("Hash with string");
			textInput = tabHash.getTxtString().getText();
			if (textInput.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Empty text input",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			algorithm = (String)tabHash.getChoiceAlgorithms().getSelectedItem();
			try {
				Hash hash = new Hash(algorithm);
				outText = hash.hash(textInput);
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		} else {
			System.out.println("Hash with file");
			textInput = tabHash.getLblFileInput().getText();
			if (textInput.equalsIgnoreCase("")) {
				JOptionPane.showMessageDialog(null, "Empty file input",
						"Error", JOptionPane.ERROR_MESSAGE);
				return;
			}
			algorithm = (String)tabHash.getChoiceAlgorithms().getSelectedItem();
			try {
				Hash hash = new Hash(algorithm);
				outText = hash.hash(textInput);
				System.out.println("Hash success!");
			} catch (NoSuchAlgorithmException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		tabHash.getTxtResult().setText(outText);
	}

	public void handleTabSymmetric() {
		textInput = pnEncrypt.getTxtPlain().getText();
		if (textInput.equals("") && pnEncrypt.getRdField().isSelected()) {
			JOptionPane.showMessageDialog(null, "Empty text input", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		Key txtkey = null;
		if (pnKey.getRdField().isSelected()) {
			System.out.println("Input Key");
			txtkey = symmetric.convertStringKeyToSecretKey(pnKey.getTxtKey());
		} else {
			System.out.println("File Key");
			try {
				txtkey = symmetric.readKey(pnKey.getFileInputKey()
						.getAbsolutePath());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "File key valid", "Error",
						JOptionPane.ERROR_MESSAGE);
				e.printStackTrace();
				return;
			}
		}
		if (txtkey == null) {
			JOptionPane.showMessageDialog(null, "Empty key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (algorithm == null)
			addControllOptionSymmetricTab();
		outText = "";
		keysize = Integer.parseInt((String)pnOption.getChoiceKeySize()
				.getSelectedItem());
		try {
			symmetric = new Symmetric(algorithm, mode, padding, keysize);
			if (txtkey != null)
				try {
					symmetric.setKey((SecretKey) txtkey);
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
			// if radio is encrypt then into if, else is decrypt
			if (pnEncrypt.getPnSelectEnOrDe().getRdEncrypt().isSelected()) {
				if (pnEncrypt.getRdFile().isSelected()) {
					System.out.println("Encrypt with file");
					inputFile = pnEncrypt.getLblFileInput().getText();
					outputFile = pnEncrypt.getLblFileOutput().getText();
					symmetric.encrypt(inputFile, outputFile);
					JOptionPane.showMessageDialog(null, "Mã hóa thành công",
							"Success", JOptionPane.OK_OPTION);
				} else {
					System.out.println("Encrypt with string");
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

	public void handleTabPBE() {

	}

	public void handleTabAsymmetric() {
		textInput = tabAsymmetric.getOptionEncryptUI().getTxtPlain().getText();
		if (textInput.equals("")
				&& tabAsymmetric.getOptionEncryptUI().getRdField().isSelected()) {
			JOptionPane.showMessageDialog(null, "Empty text input", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (tabAsymmetric.getRdString().isSelected()
				&& tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
						.getRdDecrypt().isSelected()
				&& tabAsymmetric.getTxtPrivateKey().getText()
						.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Empty private key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (tabAsymmetric.getRdString().isSelected()
				&& tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
						.getRdEncrypt().isSelected()
				&& tabAsymmetric.getTxtPublicKey().getText()
						.equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Empty public key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		PrivateKey privatekey = null;
		PublicKey publickey = null;
		if (tabAsymmetric.getRdString().isSelected()) {
			System.out.println("Input Key");
			if (tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				publickey = asymmetric
						.convertStringKeyToPublicKey(tabAsymmetric
								.getTxtPublicKey().getText());
				System.out.println("add public key for decrypt");
			} else {
				privatekey = asymmetric
						.convertStringKeyToPrivateKey(tabAsymmetric
								.getTxtPrivateKey().getText());
				System.out.println("add private key for decrypt");
			}
		} else {
			System.out.println("File Key");
			if (tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				try {
					publickey = asymmetric.readPublicKey(tabAsymmetric
							.getLblInputKeyPublic().getText());

				} catch (InvalidKeySpecException | NoSuchAlgorithmException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					privatekey = asymmetric.readPrivateKey(tabAsymmetric
							.getLblInputKeyPrivate().getText());
					System.out.println(tabAsymmetric.getLblInputKeyPrivate()
							.getText());

				} catch (InvalidKeySpecException | NoSuchAlgorithmException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		if (algorithm == null)
			addControllOptionAsymmetricTab();
		outText = "";

		try {
			asymmetric = new Asymmetric(algorithm, mode, padding, keysize);
			if (tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				System.out.println("Vao de set public");
				asymmetric.setPublicKey(publickey);
				System.out.println("Set public key");
			} else {
				System.out.println("Vao de set private");
				asymmetric.setPrivateKey(privatekey);
				System.out.println("Set private key");
			}
			// if radio is encrypt then into if, else is decrypt
			if (tabAsymmetric.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {

				System.out.println("Encrypt with string");
				outText = asymmetric.encrypt(textInput);

			} else {

				System.out.println("Decrypt with string");
				outText = asymmetric.decrypt(textInput);

			}
			tabAsymmetric.getOptionEncryptUI().setTxtCipher(outText);
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
		algorithm =(String) pnOption.getChoiceAlgorithms().getSelectedItem();
		mode =(String)  pnOption.getChoiceMode().getSelectedItem();
		padding = (String) pnOption.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt((String) pnOption.getChoiceKeySize()
				.getSelectedItem());
	}

	public void addControllOptionAsymmetricTab() {
		algorithm = (String)tabAsymmetric.getChoiceAlgorithms().getSelectedItem();
		mode = (String)tabAsymmetric.getChoiceMode().getSelectedItem();
		padding = (String)tabAsymmetric.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt((String)tabAsymmetric.getChoiceKeySize()
				.getSelectedItem());
	}
}
