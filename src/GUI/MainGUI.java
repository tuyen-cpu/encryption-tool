package GUI;

import javax.crypto.NoSuchPaddingException;
import javax.crypto.SecretKey;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.SwingWorker;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;

import javax.swing.JPanel;
import javax.swing.ImageIcon;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import algorithms.Asymmetric;
import algorithms.Gif;
import algorithms.Hash;
import algorithms.RSAFile;
import algorithms.Symmetric;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.InvalidKeySpecException;
import java.util.Base64;
import java.util.concurrent.TimeUnit;

import javax.swing.BoxLayout;

public class MainGUI {
	private JPanel pnMain, pnSymmetric, pnBtnStart, pnAsymmetric, pnCombine,
			pnHash;
	private OptionGeneralUI pnOption;
	private OptionKeyUI pnKey;
	private OptionEncryptUI pnEncrypt;
	private TabHash tabHash;
	private JTabbedPane tabbedPane;
	private JButton btnStart;
	private String algorithm, mode, padding;
	private int keysize;
	private Symmetric symmetric;
	private Asymmetric asymmetric;
	private RSAFile rsaFile;
	private String tabbedPaneCurrent;
	private String outText, textInput, inputFile, outputFile;
	private TabAsymmetric tabAsymmetric, tabCombine;
	private ImageIcon icon, iconLoading;
	static JFrame frame;
	private JDialog jLoading;
	
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
			rsaFile = new RSAFile("AES", 128, "CBC", "PKCS5Padding");
		} catch (NoSuchAlgorithmException | NoSuchPaddingException
				| IOException | NoSuchProviderException e) {
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
		tabbedPane.addTab("Combine", null, pnCombine, null);
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
		pnCombine.add(tabCombine, BorderLayout.CENTER);

	}

	public void createComponent() {
		tabbedPane = new JTabbedPane(JTabbedPane.TOP);
		pnBtnStart = new JPanel();
		btnStart = new JButton("Start");
		pnSymmetric = new JPanel();
		pnAsymmetric = new JPanel(new BorderLayout());
		iconLoading = icon = new ImageIcon(this.getClass().getResource(
				"/img/loading.gif"));
		pnCombine = new JPanel(new BorderLayout());
		pnHash = new JPanel(new BorderLayout());
		pnOption = new OptionGeneralUI();
		pnKey = new OptionKeyUI();
		pnEncrypt = new OptionEncryptUI();
		icon = new ImageIcon(this.getClass().getResource("/img/success.png"));
		tabAsymmetric = new TabAsymmetric();
		tabAsymmetric.getOptionEncryptUI().remove(
				tabAsymmetric.getOptionEncryptUI().getPnRadio());
		tabCombine = new TabAsymmetric();
		setListTabCombine();
		tabHash = new TabHash();
		pnMain = new JPanel();
		pnMain.setLayout(new BorderLayout());
		loading(frame);
	}

	public void setListTabCombine() {
		DefaultComboBoxModel model;

		tabCombine.setListAlgorithms(TabCombine.listAlgorithms);
		model = new DefaultComboBoxModel<String>(TabCombine.listAlgorithms);
		tabCombine.getChoiceAlgorithms().setModel(model);

		tabCombine.setListKeySize(TabCombine.listKeySize);
		model = new DefaultComboBoxModel<String>(TabCombine.listKeySize);
		tabCombine.getChoiceKeySize().setModel(model);

		tabCombine.setListMode(TabCombine.listMode);
		model = new DefaultComboBoxModel<String>(TabCombine.listMode);
		tabCombine.getChoiceMode().setModel(model);

		tabCombine.setListPadding(TabCombine.listPadding);
		model = new DefaultComboBoxModel<String>(TabCombine.listPadding);
		tabCombine.getChoicePadding().setModel(model);

		tabCombine.getChoiceAlgorithms().addItemListener(new ItemListener() {

			@Override
			public void itemStateChanged(ItemEvent arg0) {
				DefaultComboBoxModel<String> model;
				switch ((String) tabCombine.getChoiceAlgorithms()
						.getSelectedItem()) {

				case "AES":
					tabCombine.setListKeySize(TabCombine.listKeySizeAES);
					model = new DefaultComboBoxModel<String>(
							TabCombine.listKeySizeAES);
					tabCombine.getChoiceKeySize().setModel(model);
					System.out.println("AES");
					break;
				case "DES":
					tabCombine.setListKeySize(TabCombine.listKeySizeDES);
					model = new DefaultComboBoxModel<String>(
							TabCombine.listKeySizeDES);
					tabCombine.getChoiceKeySize().setModel(model);
					System.out.println("DES");
					break;
				case "DESede":
					tabCombine.setListKeySize(TabCombine.listKeySizeDESede);
					model = new DefaultComboBoxModel<String>(
							TabCombine.listKeySizeDESede);
					tabCombine.getChoiceKeySize().setModel(model);
					System.out.println("DESede");
					break;
				case "RC2":
					tabCombine.setListKeySize(TabCombine.listKeySizeRC2);
					model = new DefaultComboBoxModel<String>(
							TabCombine.listKeySizeRC2);
					tabCombine.getChoiceKeySize().setModel(model);
					System.out.println("DESede");
					break;
				case "Blowfish":
					tabCombine.setListKeySize(TabCombine.listKeySizeBlowfish);
					model = new DefaultComboBoxModel<String>(
							TabCombine.listKeySizeBlowfish);
					tabCombine.getChoiceKeySize().setModel(model);
					System.out.println("DESede");
					break;
				default:
					break;
				}

			}
		});

	}

	public void createAndShowGUI() {
		System.out.println("GUI");
		// Create and set up the window.
		frame = new JFrame("Main Frame");

		frame.setIconImage(Toolkit.getDefaultToolkit().getImage(
				getClass().getResource("/img/logo.png")));
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
				} catch (NoSuchAlgorithmException | NoSuchPaddingException | NoSuchProviderException e) {
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

					
					SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
						@Override
						protected String doInBackground()
								throws InterruptedException {
							try {
								asymmetric = new Asymmetric(algorithm, mode, padding,
										keysize);
								asymmetric.genkey();
								return "success";
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("loi ngay day");
								return "error";
							}
						}

						@Override
						protected void done() {
							jLoading.dispose();
						}
					};
					worker.execute(); // here the process thread initiates
					jLoading.setVisible(true);

					tabAsymmetric.getTxtPrivateKey().setText(
							asymmetric.getPrivateKeyWithString());
					tabAsymmetric.getTxtPublicKey().setText(
							asymmetric.getPublicKeyWithString());

				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		});
		// create key combine
		tabCombine.getBtnCreateKey().addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent arg0) {
				addControllOptionCombineTab();
				try {
					tabCombine.getTxtPrivateKey().setText("");
					tabCombine.getTxtPublicKey().setText("");	
					SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
						@Override
						protected String doInBackground()
								throws InterruptedException {
							try {
								rsaFile = new RSAFile(algorithm, keysize, mode, padding);
								rsaFile.doGenkey();
								return "success";
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("loi ngay day");
								return "error";
							}
						}

						@Override
						protected void done() {
							jLoading.dispose();
						}
					};
					worker.execute(); // here the process thread initiates
					jLoading.setVisible(true);
					
					tabCombine.getTxtPrivateKey().setText(
							rsaFile.getPrivateKeyWithString());
					tabCombine.getTxtPublicKey().setText(
							rsaFile.getPublicKeyWithString());

				} catch (Exception e) {
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
				case "Combine":
					System.out.println("Handle tab Combine");
					handleTabCombine();
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
			algorithm = (String) tabHash.getChoiceAlgorithms()
					.getSelectedItem();
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
			algorithm = (String) tabHash.getChoiceAlgorithms()
					.getSelectedItem();
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

	public void loading(JFrame frameParent) {
		jLoading = new JDialog(frame);
		JPanel p1 = new JPanel(new BorderLayout());
		p1.add(new JLabel(iconLoading), BorderLayout.CENTER);
		jLoading.setUndecorated(true);
		jLoading.getContentPane().add(p1);
		jLoading.pack();
		jLoading.setLocationRelativeTo(frame);
		jLoading.setDefaultCloseOperation(JDialog.DO_NOTHING_ON_CLOSE);
		jLoading.setModal(true);
	}

	public void checkEmptyFileInputAndOutput() {
		if (pnEncrypt.getFileInput() == null) {
			JOptionPane.showMessageDialog(null, "File input empty", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (pnEncrypt.getFileOutput() == null) {
			JOptionPane.showMessageDialog(null, "File output empty", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
	}
public void getDialogNotuSupportNopaddingWithFile(){
	if(padding.equalsIgnoreCase("NoPadding")){
		JOptionPane.showMessageDialog(null, "No support file encryption and decryption with Nopadding", "Error",
				JOptionPane.PLAIN_MESSAGE);
		return;
	}
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
			if (pnKey.getTxtKey().equals("")) {
				JOptionPane.showMessageDialog(null, "Empty key", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			System.out.println("Input Key");
			try {
				txtkey = symmetric.convertStringKeyToSecretKey(pnKey
						.getTxtKey());
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "Key valid", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
		} else {
			System.out.println("File Key");
			if (pnKey.getFileInputKey() == null) {
				JOptionPane.showMessageDialog(null, "File key empty", "Error",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
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
		keysize = Integer.parseInt((String) pnOption.getChoiceKeySize()
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
					if(padding.equalsIgnoreCase("NoPadding")){
						JOptionPane.showMessageDialog(null, "No support file encryption and decryption with Nopadding", "Error",
								JOptionPane.PLAIN_MESSAGE);
						return;
					}
					checkEmptyFileInputAndOutput();
					inputFile = pnEncrypt.getFileInput().getAbsolutePath();
					outputFile = pnEncrypt.getFileOutput().getAbsolutePath();
					System.out.println("Encrypt with file");
					SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
						@Override
						protected String doInBackground()
								throws InterruptedException {
							try {

								symmetric.encrypt(inputFile, outputFile);
								return "success";
							} catch (Exception e) {
								e.printStackTrace();
								System.out.println("loi ngay day");
								return "error";
							}
						}

						@Override
						protected void done() {
							jLoading.dispose();
						}
					};
					worker.execute(); // here the process thread initiates
					jLoading.setVisible(true);
					try {
						if (worker.get().equalsIgnoreCase("success")) {
							JOptionPane.showMessageDialog(null,
									"Successful encryption!", "Success",
									JOptionPane.INFORMATION_MESSAGE, icon);
						} else {
							JOptionPane.showMessageDialog(null, "Invalid key",
									"Error", JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}

				} else {
					System.out.println("Encrypt with string");
					outText = symmetric.encrypt(textInput);
				}
			} else {
				if (pnEncrypt.getRdFile().isSelected()) {
					if(padding.equalsIgnoreCase("NoPadding")){
						JOptionPane.showMessageDialog(null, "No support file encryption and decryption with Nopadding", "Error",
								JOptionPane.PLAIN_MESSAGE);
						return;
					}
					checkEmptyFileInputAndOutput();
					inputFile = pnEncrypt.getFileInput().getAbsolutePath();
					outputFile = pnEncrypt.getFileOutput().getAbsolutePath();
					try {
						symmetric.decrypt(inputFile, outputFile);
						JOptionPane.showMessageDialog(null,
								"Successful decryption!", "Success",
								JOptionPane.INFORMATION_MESSAGE, icon);
					} catch (Exception e) {
						e.printStackTrace();
						JOptionPane.showMessageDialog(null,
								"Decryption failed", "Error",
								JOptionPane.OK_OPTION);
					}
				} else {
					System.out.println("Decrypt with string");
					outText = symmetric.decrypt(textInput);
				}
			}
			pnEncrypt.setTxtCipher(outText);
			;
		} catch (NoSuchAlgorithmException | NoSuchPaddingException e1) {
			e1.printStackTrace();
		} catch (Exception e1) {
			e1.printStackTrace();
		}
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
					System.out.println(tabAsymmetric.getPublicFile()
							.getAbsolutePath());
					publickey = asymmetric.readPublicKey(tabAsymmetric
							.getPublicFile().getAbsolutePath());

				} catch (InvalidKeySpecException | NoSuchAlgorithmException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			} else {
				try {
					privatekey = asymmetric.readPrivateKey(tabAsymmetric
							.getPrivateFile().getAbsolutePath());
					System.out.println(tabAsymmetric.getPrivateFile()
							.getAbsolutePath());

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
				outText = asymmetric.encrypt(textInput);
				System.out.println("Encrypt with string");
			} else {
				System.out.println("Vao de set private");
				asymmetric.setPrivateKey(privatekey);
				System.out.println("Set private key");
				outText = asymmetric.decrypt(textInput);
				System.out.println("Decrypt with string");
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

	public void handleTabCombine() {
		textInput = tabCombine.getOptionEncryptUI().getTxtPlain().getText();
		if (textInput.equals("")
				&& tabCombine.getOptionEncryptUI().getRdField().isSelected()) {
			JOptionPane.showMessageDialog(null, "Empty text input", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (tabCombine.getRdString().isSelected()
				&& tabCombine.getOptionEncryptUI().getPnSelectEnOrDe()
						.getRdDecrypt().isSelected()
				&& tabCombine.getTxtPrivateKey().getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Empty private key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		if (tabCombine.getRdString().isSelected()
				&& tabCombine.getOptionEncryptUI().getPnSelectEnOrDe()
						.getRdEncrypt().isSelected()
				&& tabCombine.getTxtPublicKey().getText().equalsIgnoreCase("")) {
			JOptionPane.showMessageDialog(null, "Empty public key", "Error",
					JOptionPane.ERROR_MESSAGE);
			return;
		}
		PrivateKey privatekey = null;
		PublicKey publickey = null;
		if (tabCombine.getRdString().isSelected()) {
			System.out.println("Input Key");
			if (tabCombine.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				privatekey = rsaFile.convertStringKeyToPrivateKey(tabCombine
						.getTxtPrivateKey().getText());
				System.out.println("add private key for decrypt");
			} else {
				publickey = rsaFile.convertStringKeyToPublicKey(tabCombine
						.getTxtPublicKey().getText());

				System.out.println("add public key for decrypt");
			}
		} else {
			System.out.println("File Key");
			if (tabCombine.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				try {
					System.out.println(tabCombine.getPublicFile()
							.getAbsolutePath());

					privatekey = rsaFile.readPrivateKey(tabCombine
							.getPrivateFile().getAbsolutePath());
				} catch (InvalidKeySpecException | NoSuchAlgorithmException
						| IOException e) {
					e.printStackTrace();
				}
			} else {
				try {
					publickey = rsaFile.readPublicKey(tabCombine
							.getPublicFile().getAbsolutePath());
					System.out.println(tabCombine.getPrivateFile()
							.getAbsolutePath());

				} catch (InvalidKeySpecException | NoSuchAlgorithmException
						| IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}

		}

		if (algorithm == null)
			addControllOptionCombineTab();
		outText = "";

		try {
			rsaFile = new RSAFile(algorithm, keysize, mode, padding);
			if (tabCombine.getOptionEncryptUI().getPnSelectEnOrDe()
					.getRdEncrypt().isSelected()) {
				System.out.println("Vao ma hoa de set private");
				rsaFile.setPrivateKey(privatekey);
				System.out.println("Set private key");
				if (tabCombine.getOptionEncryptUI().getRdField().isSelected()) {
					System.out.println("Enter encryt with string");

				} else {
					inputFile = tabCombine.getOptionEncryptUI().getFileInput()
							.getAbsolutePath();
					outputFile = tabCombine.getOptionEncryptUI()
							.getFileOutput().getAbsolutePath();
					System.out.println("Combine encryt with file");
					System.out.println(rsaFile.getPrivateKeyWithString());
					SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
						@Override
						protected String doInBackground()
								throws InterruptedException {
							try {
								rsaFile.doEncryptRSAWithAES(inputFile, outputFile);
								return "success";
							} catch (Exception e) {
								e.printStackTrace();
								return "error";
							}
						}

						@Override
						protected void done() {
							jLoading.dispose();
						}
					};
					worker.execute(); // here the process thread initiates
					jLoading.setVisible(true);
					try {
						if (worker.get().equalsIgnoreCase("success")) {
							JOptionPane.showMessageDialog(null,
									"Successful encryption!", "Success",
									JOptionPane.INFORMATION_MESSAGE, icon);
						} else {
							JOptionPane.showMessageDialog(null, "Invalid key",
									"Error", JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			} else {
				System.out.println("Vao giai ma de set public");
				rsaFile.setPublicKey(publickey);
				System.out.println("Set public key");
				if (tabCombine.getOptionEncryptUI().getRdField().isSelected()) {
					System.out.println("Enter decryt with string");

				} else {
					inputFile = tabCombine.getOptionEncryptUI().getFileInput()
							.getAbsolutePath();
					outputFile = tabCombine.getOptionEncryptUI()
							.getFileOutput().getAbsolutePath();

					System.out.println("Enter decryt with file");
					
					SwingWorker<String, Void> worker = new SwingWorker<String, Void>() {
						@Override
						protected String doInBackground()
								throws InterruptedException {
							try {
								rsaFile.doDeCryptRSAWithAES(inputFile, outputFile);
								return "success";
							} catch (Exception e) {
								e.printStackTrace();
								return "error";
							}
						}

						@Override
						protected void done() {
							jLoading.dispose();
						}
					};
					worker.execute(); // here the process thread initiates
					jLoading.setVisible(true);
					try {
						if (worker.get().equalsIgnoreCase("success")) {
							JOptionPane.showMessageDialog(null,
									"Successful decryption!", "Success",
									JOptionPane.INFORMATION_MESSAGE, icon);
						} else {
							JOptionPane.showMessageDialog(null, "Invalid key",
									"Error", JOptionPane.ERROR_MESSAGE);

						}
					} catch (Exception e1) {
						e1.printStackTrace();
					}
				}
			}

			tabCombine.getOptionEncryptUI().setTxtCipher(outText);
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
		algorithm = (String) pnOption.getChoiceAlgorithms().getSelectedItem();
		mode = (String) pnOption.getChoiceMode().getSelectedItem();
		padding = (String) pnOption.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt((String) pnOption.getChoiceKeySize()
				.getSelectedItem());
	}

	public void addControllOptionAsymmetricTab() {
		algorithm = (String) tabAsymmetric.getChoiceAlgorithms()
				.getSelectedItem();
		mode = (String) tabAsymmetric.getChoiceMode().getSelectedItem();
		padding = (String) tabAsymmetric.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt((String) tabAsymmetric.getChoiceKeySize()
				.getSelectedItem());
	}

	public void addControllOptionCombineTab() {
		algorithm = (String) tabCombine.getChoiceAlgorithms().getSelectedItem();
		mode = (String) tabCombine.getChoiceMode().getSelectedItem();
		padding = (String) tabCombine.getChoicePadding().getSelectedItem();
		keysize = Integer.parseInt((String) tabCombine.getChoiceKeySize()
				.getSelectedItem());
	}
}
