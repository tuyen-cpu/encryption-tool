package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Base64;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.BevelBorder;

import java.awt.Color;
import java.awt.FlowLayout;

public class TabAsymmetric extends JPanel implements ActionListener {
	private String[] listAlgorithms = { "RSA" };
	private String[] listMode = { "ECB" };
	private String[] listPadding = { "OAEPWithSHA-1AndMGF1Padding",
			"OAEPWithSHA-256AndMGF1Padding", "PKCS1Padding" };

	private String[] listKeySize = { "1024", "2048", "3072", "4069" };
	private JPanel pnOption,pnOptionContainer, pnKey, pnEncypt, pnAlgorithms, pnKeySize, pnMode,
			pnPadding, pnOptionKey, pnContainerKey, pnFieldPublic,
			pnPrivateKey, pnFieldPrivate, pnFilePublic, pnFilePrivte,
			pnFileKey, pnFieldPublicContainer, pnSum, pnCenter, pnBtnPrivate,
			pnBtnPublic;
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblPadding,
			lblPrivatekey;
	private JComboBox choiceAlgorithms, choiceKeySize, choiceMode,
			choicePadding;
	private JButton btnCreateKey, btnImportPublicKey, btnImportPrivatekey,
			btnCopyPrivateKey, btnCopyPublicKey, btnSavePrivate, btnSavePublic;
	private JRadioButton rdString, rdFile;
	private JTextField txtPublicKey, txtPrivateKey;
	private Dimension dmTxtKey, dmBtnCopy, dmBtnCreate, dmChoose;
	private OptionEncryptUI optionEncryptUI;
	File publicFile, privateFile, fileSave;
	JFileChooser jFileChoose;
	OptionGeneralUI generalUI;
	private static Base64.Encoder encoder = Base64.getEncoder();
	private static Base64.Decoder decoder = Base64.getDecoder();

	public TabAsymmetric() {
		setLayout(new BorderLayout());
		createComponent();
		addComponent();
		addHandle();
	}

	public void createComponent() {
		dmTxtKey = new Dimension(510, 40);
		dmBtnCopy = new Dimension(60, 40);
		dmBtnCreate = new Dimension(120, 40);
		dmChoose = new Dimension(140, 40);
		btnSavePrivate = new JButton();
		btnSavePublic = new JButton();
		pnOptionContainer = new JPanel();
		pnOptionContainer.setBorder(new CompoundBorder(new BevelBorder(BevelBorder.LOWERED, null, null, new Color(255, 255, 255), new Color(255, 255, 255)), new EmptyBorder(10, 10, 10, 10)));
		btnSavePublic.setToolTipText("Save");
		btnSavePrivate.setToolTipText("Save");
		pnFieldPublicContainer = new JPanel(new BorderLayout());
		pnSum = new JPanel(new BorderLayout());
		pnSum.setBorder(new CompoundBorder(
				new BevelBorder(BevelBorder.LOWERED, null, null, new Color(200,
						200, 200), new Color(200, 200, 200)), new EmptyBorder(
						10, 10, 10, 10)));
		pnOption = new JPanel();
		pnAlgorithms = new JPanel();
		pnKeySize = new JPanel();
		pnMode = new JPanel();
		pnPadding = new JPanel();
		pnBtnPrivate = new JPanel(new BorderLayout());
		pnBtnPublic = new JPanel(new BorderLayout());
		pnKey = new JPanel(new BorderLayout());
		pnCenter = new JPanel(new BorderLayout());
		pnCenter.setBorder(new EmptyBorder(20, 20, 0, 20));
		pnEncypt = new JPanel();
		lblAlgorithms = new JLabel("Algorithms:");
		lblKeySize = new JLabel("Key size:");
		lblMode = new JLabel("Mode");
		lblPadding = new JLabel("Padding");
		choiceAlgorithms = new JComboBox(listAlgorithms);
		choiceKeySize = new JComboBox(listKeySize);
		choiceMode = new JComboBox(listMode);
		choicePadding = new JComboBox(listPadding);
		btnCreateKey = new JButton("Create key");
		pnOptionKey = new JPanel();
		pnContainerKey = new JPanel(new BorderLayout());
		rdString = new JRadioButton("Text key");
		rdFile = new JRadioButton("Import file key");
		rdString.setFocusPainted(false);
		rdFile.setFocusPainted(false);
		rdString.setSelected(true);
		ButtonGroup bg = new ButtonGroup();
		bg.add(rdString);
		bg.add(rdFile);
		txtPublicKey = new JTextField();
		txtPrivateKey = new JTextField();
		btnCopyPrivateKey = new JButton();
		btnCopyPublicKey = new JButton();
		btnCopyPrivateKey.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnCopyPublicKey.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/copy.png")));
		btnSavePublic.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/icon-save.png")));
		btnSavePrivate.setIcon(new ImageIcon(this.getClass().getResource(
				"/img/icon-save.png")));
		pnFieldPublic = new JPanel(new BorderLayout());
		pnFieldPrivate = new JPanel(new BorderLayout());
		pnFilePublic = new JPanel();
		pnFilePrivte = new JPanel();
		pnFileKey = new JPanel(new BorderLayout());
		btnImportPublicKey = new JButton("Choose public key");
		btnImportPrivatekey = new JButton("Choose private key");
		optionEncryptUI = new OptionEncryptUI();
		optionEncryptUI.setBorder(null);
		jFileChoose = new JFileChooser();
		jFileChoose.setCurrentDirectory(jFileChoose.getFileSystemView()
				.getParentDirectory(new File("C:\\")));
		pnPrivateKey = new JPanel(new BorderLayout());
		lblPrivatekey = new JLabel("Private key:", SwingConstants.CENTER);
	}

	public void addComponent() {

		pnAlgorithms.add(lblAlgorithms);
		pnAlgorithms.add(choiceAlgorithms);
		pnKeySize.add(lblKeySize);
		pnKeySize.add(choiceKeySize);
		pnMode.add(lblMode);
		pnMode.add(choiceMode);
		pnPadding.add(lblPadding);
		pnPadding.add(choicePadding);
		pnOption.setLayout(new BoxLayout(pnOption, BoxLayout.X_AXIS));
		pnOption.add(pnAlgorithms);
		pnOption.add(pnAlgorithms);
		pnOption.add(pnKeySize);
		pnOption.add(pnMode);
		pnOption.add(pnPadding);
		pnKey.add(pnOptionKey, BorderLayout.CENTER);
		pnKey.add(pnSum, BorderLayout.SOUTH);
		pnOptionKey.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		pnOptionKey.add(rdString);
		pnOptionKey.add(rdFile);
		pnSum.add(pnContainerKey);
		// pnSum.add(pnFileKey);
		pnFieldPublic.add(btnCreateKey, BorderLayout.WEST);
		pnFieldPublic.add(txtPublicKey);
		pnBtnPublic.add(btnSavePublic, BorderLayout.WEST);
		pnBtnPublic.add(btnCopyPublicKey);
		pnFieldPublicContainer.add(pnFieldPublic);
		pnFieldPublicContainer.add(pnBtnPublic, BorderLayout.EAST);
		pnBtnPrivate.add(btnSavePrivate, BorderLayout.WEST);
		pnBtnPrivate.add(btnCopyPrivateKey);
		pnFieldPrivate.add(pnBtnPrivate, BorderLayout.EAST);
		pnFieldPrivate.add(pnPrivateKey);
		pnPrivateKey.add(lblPrivatekey, BorderLayout.WEST);
		pnPrivateKey.add(txtPrivateKey);
		pnContainerKey.add(pnFieldPublicContainer, BorderLayout.NORTH);
		pnContainerKey.add(pnFieldPrivate, BorderLayout.SOUTH);
		pnFilePrivte.setLayout(new BorderLayout(0, 0));
		pnFilePrivte.add(btnImportPrivatekey);
		pnFilePublic.setLayout(new BorderLayout(0, 0));

		pnFilePublic.add(btnImportPublicKey);
		pnFileKey.add(pnFilePublic, BorderLayout.NORTH);
		pnFileKey.add(pnFilePrivte, BorderLayout.SOUTH);
		pnOptionContainer.setLayout(new BorderLayout(0, 0));
		pnOptionContainer.add(pnOption);
		add(pnOptionContainer, BorderLayout.NORTH);
		pnCenter.add(pnKey, BorderLayout.NORTH);
		pnCenter.add(optionEncryptUI);
		add(pnCenter);

		optionEncryptUI.getTxtPlain().setSize(7, 50);
		setBorder(new EmptyBorder(0, 0, 10, 0));
		lblAlgorithms.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblKeySize.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblMode.setFont(new Font("Dialog", Font.PLAIN, 13));
		lblPadding.setFont(new Font("Dialog", Font.PLAIN, 13));
		btnCopyPrivateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnCopyPublicKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnImportPrivatekey.setFont(new Font("Dialog", Font.PLAIN, 12));
		btnImportPublicKey.setFont(new Font("Dialog", Font.PLAIN, 12));

		btnCreateKey.setFont(new Font("Dialog", Font.PLAIN, 12));
		lblPrivatekey.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPrivateKey.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPublicKey.setFont(new Font("Dialog", Font.PLAIN, 14));
		txtPrivateKey.setToolTipText("Private key");
		txtPublicKey.setToolTipText("Public key");
		btnSavePrivate.setToolTipText("Save");
		btnSavePublic.setToolTipText("Save");
		btnCreateKey.setToolTipText("Press to create key");
		pnAlgorithms.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnKeySize.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnMode.setBorder(new EmptyBorder(0, 0, 0, 0));
		pnPadding.setBorder(new EmptyBorder(0, 0, 0, 0));
		choiceAlgorithms.setFocusable(false);
		choiceKeySize.setFocusable(false);
		choiceMode.setFocusable(false);
		choicePadding.setFocusable(false);
		btnSavePrivate.setPreferredSize(dmBtnCopy);
		btnSavePublic.setPreferredSize(dmBtnCopy);
		pnFieldPrivate.setBorder(new EmptyBorder(-5, 45, 0, 0));
		lblPrivatekey.setPreferredSize(new Dimension(120, 40));
		txtPublicKey.setPreferredSize(dmTxtKey);
		txtPrivateKey.setPreferredSize(dmTxtKey);
		btnCreateKey.setPreferredSize(dmBtnCreate);
		btnCopyPublicKey.setPreferredSize(dmBtnCopy);
		btnCopyPrivateKey.setPreferredSize(dmBtnCopy);
		btnImportPublicKey.setPreferredSize(dmChoose);
		btnImportPrivatekey.setPreferredSize(dmChoose);
		choicePadding.setPreferredSize(new Dimension(190, 26));
		optionEncryptUI.getBtnChooseInput().setPreferredSize(dmChoose);
		optionEncryptUI.getBtnChooseOutput().setPreferredSize(dmChoose);
		pnFieldPrivate.setBorder(new EmptyBorder(0, 0, 0, 0));
		optionEncryptUI.setPreferredSize(new Dimension(740, 280));
		generalUI = new OptionGeneralUI();
		generalUI.setBorder(null);
		pnOptionContainer.add(generalUI, BorderLayout.SOUTH);
	}

	public void addHandle() {
		rdFile.addActionListener(this);
		rdString.addActionListener(this);
		rdString.setActionCommand("rdString");
		rdFile.setActionCommand("rdFile");
		btnImportPrivatekey.addActionListener(new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isStop = false;
					do {
						privateFile = openFile();
						if (privateFile.exists()) {
							btnImportPrivatekey.setText(privateFile
									.getAbsolutePath());
							isStop = true;
						} else {
							JOptionPane.showMessageDialog(MainGUI.frame,
									"File " + privateFile.getName()
											+ " doesn't exist!", "Error",
									JOptionPane.ERROR_MESSAGE);
							System.out.println("File does not exist!");
							btnImportPrivatekey.setText("Choose private key");
							privateFile = null;
							isStop = false;

						}
					} while (!isStop);
				} catch (Exception ex) {
					privateFile = null;
					System.out.println("Cancel choose file");
				}

			}
		});
		btnImportPublicKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					boolean isStop = false;
					do {
						publicFile = openFile();
						if (publicFile.exists()) {
							btnImportPublicKey.setText(publicFile
									.getAbsolutePath());
							isStop = true;
						} else {
							JOptionPane.showMessageDialog(MainGUI.frame,
									"File " + publicFile.getName()
											+ " doesn't exist!", "Error",
									JOptionPane.ERROR_MESSAGE);
							System.out.println("File does not exist!");
							btnImportPublicKey.setText("Choose public key");
							publicFile = null;
							isStop = false;

						}
					} while (!isStop);
				} catch (Exception ex) {
					publicFile = null;
					System.out.println("Cancel choose file");
				}

			}
		});
		btnCopyPrivateKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionEncryptUI.copyIntoClipBoard(txtPrivateKey.getText());

			}
		});
		btnCopyPublicKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				optionEncryptUI.copyIntoClipBoard(txtPublicKey.getText());

			}
		});
		txtPrivateKey.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				DialogCustom.stopDialog();
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txtPrivateKey.getText().trim().equals("")) {
					DialogCustom.showDescription(txtPrivateKey,
							"Enter private key");
				}

			}
		});
		txtPublicKey.addFocusListener(new FocusListener() {

			@Override
			public void focusLost(FocusEvent e) {
				DialogCustom.stopDialog();
			}

			@Override
			public void focusGained(FocusEvent e) {
				if (txtPublicKey.getText().trim().equals("")) {
					DialogCustom.showDescription(txtPublicKey,
							"Enter public key");
				}

			}
		});
		btnSavePrivate.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtPrivateKey.getText().equalsIgnoreCase("")) {
					saveFile(txtPrivateKey.getText());
				} else {
					JOptionPane.showMessageDialog(MainGUI.frame,
							"Empty private key!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
		btnSavePublic.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				if (!txtPublicKey.getText().equalsIgnoreCase("")) {
					saveFile(txtPublicKey.getText());
				} else {
					JOptionPane.showMessageDialog(MainGUI.frame,
							"Empty public key!", "Error",
							JOptionPane.ERROR_MESSAGE);
				}

			}
		});
	}

	public File openFile() {
		int select = jFileChoose.showOpenDialog(null);
		if (select == JFileChooser.APPROVE_OPTION) {
			System.out.println(jFileChoose.getSelectedFile().getAbsolutePath());
			return jFileChoose.getSelectedFile();
		} else {
			System.out.println("Cancel");
			return null;
		}
	}

	public void setChoice(String[] listValue, Choice choice) {
		choice.removeAll();
		for (String algo : listValue) {
			choice.add(algo);
		}
	}

	public void saveFile(String txt) {
		int select = jFileChoose.showSaveDialog(this);
		if (select == JFileChooser.APPROVE_OPTION) {
			System.out.println("Save into: "
					+ jFileChoose.getSelectedFile().getName());
			fileSave = jFileChoose.getSelectedFile();
			if (fileSave.exists()) {

				int dialogResult = JOptionPane.showConfirmDialog(this, "File "
						+ fileSave.getName()
						+ " already exists, do you want to replace this file?",
						"Warning", JOptionPane.YES_NO_OPTION,
						JOptionPane.QUESTION_MESSAGE);
				if (dialogResult == JOptionPane.YES_OPTION) {
					writeFile(txt, fileSave);
					System.out.println("replace!");
				} else {
					System.out.println("no replace!");
				}
			} else {
				writeFile(txt, fileSave);
				System.out.println("Saved!");
			}

		} else {
			System.out.println("Cancel");
		}

	}
	public void writeFile(String txt, File file) {
		BufferedWriter writer;
		try {
			writer = new BufferedWriter(new FileWriter(file));
			writer.write(txt);
			writer.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getActionCommand().equals("rdString")) {
			System.out.println("rdString");
			pnSum.remove(pnFileKey);
			pnSum.add(pnContainerKey);
			pnSum.revalidate();
			pnSum.repaint();
		} else {
			System.out.println("rdFile");
			pnSum.remove(pnContainerKey);
			pnSum.add(pnFileKey);
			pnSum.revalidate();
			pnSum.repaint();

		}

	}

	public OptionEncryptUI getOptionEncryptUI() {
		return optionEncryptUI;
	}

	public String[] getListAlgorithms() {
		return listAlgorithms;
	}

	public void setListAlgorithms(String[] listAlgorithms) {
		this.listAlgorithms = listAlgorithms;
	}

	public String[] getListMode() {
		return listMode;
	}

	public void setListMode(String[] listMode) {
		this.listMode = listMode;
	}

	public String[] getListPadding() {
		return listPadding;
	}

	public void setListPadding(String[] listPadding) {
		this.listPadding = listPadding;
	}

	public String[] getListKeySize() {
		return listKeySize;
	}

	public void setListKeySize(String[] listKeySize) {
		this.listKeySize = listKeySize;
	}

	public JPanel getPnOption() {
		return pnOption;
	}

	public void setPnOption(JPanel pnOption) {
		this.pnOption = pnOption;
	}

	public JPanel getPnKey() {
		return pnKey;
	}

	public void setPnKey(JPanel pnKey) {
		this.pnKey = pnKey;
	}

	public JPanel getPnEncypt() {
		return pnEncypt;
	}

	public void setPnEncypt(JPanel pnEncypt) {
		this.pnEncypt = pnEncypt;
	}

	public JPanel getPnAlgorithms() {
		return pnAlgorithms;
	}

	public void setPnAlgorithms(JPanel pnAlgorithms) {
		this.pnAlgorithms = pnAlgorithms;
	}

	public JPanel getPnKeySize() {
		return pnKeySize;
	}

	public void setPnKeySize(JPanel pnKeySize) {
		this.pnKeySize = pnKeySize;
	}

	public JPanel getPnMode() {
		return pnMode;
	}

	public void setPnMode(JPanel pnMode) {
		this.pnMode = pnMode;
	}

	public JPanel getPnPadding() {
		return pnPadding;
	}

	public void setPnPadding(JPanel pnPadding) {
		this.pnPadding = pnPadding;
	}

	public JPanel getPnOptionKey() {
		return pnOptionKey;
	}

	public void setPnOptionKey(JPanel pnOptionKey) {
		this.pnOptionKey = pnOptionKey;
	}

	public JPanel getPnContainerKey() {
		return pnContainerKey;
	}

	public void setPnContainerKey(JPanel pnContainerKey) {
		this.pnContainerKey = pnContainerKey;
	}

	public JPanel getPnFieldPublic() {
		return pnFieldPublic;
	}

	public void setPnFieldPublic(JPanel pnFieldPublic) {
		this.pnFieldPublic = pnFieldPublic;
	}

	public JPanel getPnFieldPrivate() {
		return pnFieldPrivate;
	}

	public void setPnFieldPrivate(JPanel pnFieldPrivate) {
		this.pnFieldPrivate = pnFieldPrivate;
	}

	public JPanel getPnFilePublic() {
		return pnFilePublic;
	}

	public void setPnFilePublic(JPanel pnFilePublic) {
		this.pnFilePublic = pnFilePublic;
	}

	public JPanel getPnFilePrivte() {
		return pnFilePrivte;
	}

	public void setPnFilePrivte(JPanel pnFilePrivte) {
		this.pnFilePrivte = pnFilePrivte;
	}

	public JPanel getPnFileKey() {
		return pnFileKey;
	}

	public void setPnFileKey(JPanel pnFileKey) {
		this.pnFileKey = pnFileKey;
	}

	public JLabel getLblAlgorithms() {
		return lblAlgorithms;
	}

	public void setLblAlgorithms(JLabel lblAlgorithms) {
		this.lblAlgorithms = lblAlgorithms;
	}

	public JLabel getLblKeySize() {
		return lblKeySize;
	}

	public void setLblKeySize(JLabel lblKeySize) {
		this.lblKeySize = lblKeySize;
	}

	public JLabel getLblMode() {
		return lblMode;
	}

	public void setLblMode(JLabel lblMode) {
		this.lblMode = lblMode;
	}

	public JLabel getLblPadding() {
		return lblPadding;
	}

	public void setLblPadding(JLabel lblPadding) {
		this.lblPadding = lblPadding;
	}

	public JPanel getPnPrivateKey() {
		return pnPrivateKey;
	}

	public void setPnPrivateKey(JPanel pnPrivateKey) {
		this.pnPrivateKey = pnPrivateKey;
	}

	public JLabel getLblPrivatekey() {
		return lblPrivatekey;
	}

	public void setLblPrivatekey(JLabel lblPrivatekey) {
		this.lblPrivatekey = lblPrivatekey;
	}

	public JComboBox getChoiceAlgorithms() {
		return choiceAlgorithms;
	}

	public void setChoiceAlgorithms(JComboBox choiceAlgorithms) {
		this.choiceAlgorithms = choiceAlgorithms;
	}

	public JComboBox getChoiceKeySize() {
		return choiceKeySize;
	}

	public void setChoiceKeySize(JComboBox choiceKeySize) {
		this.choiceKeySize = choiceKeySize;
	}

	public JComboBox getChoiceMode() {
		return choiceMode;
	}

	public void setChoiceMode(JComboBox choiceMode) {
		this.choiceMode = choiceMode;
	}

	public JComboBox getChoicePadding() {
		return choicePadding;
	}

	public void setChoicePadding(JComboBox choicePadding) {
		this.choicePadding = choicePadding;
	}

	public JButton getBtnCreateKey() {
		return btnCreateKey;
	}

	public void setBtnCreateKey(JButton btnCreateKey) {
		this.btnCreateKey = btnCreateKey;
	}

	public JButton getBtnImportPublicKey() {
		return btnImportPublicKey;
	}

	public void setBtnImportPublicKey(JButton btnImportPublicKey) {
		this.btnImportPublicKey = btnImportPublicKey;
	}

	public JButton getBtnImportPrivatekey() {
		return btnImportPrivatekey;
	}

	public void setBtnImportPrivatekey(JButton btnImportPrivatekey) {
		this.btnImportPrivatekey = btnImportPrivatekey;
	}

	public JButton getBtnCopyPrivateKey() {
		return btnCopyPrivateKey;
	}

	public void setBtnCopyPrivateKey(JButton btnCopyPrivateKey) {
		this.btnCopyPrivateKey = btnCopyPrivateKey;
	}

	public JButton getBtnCopyPublicKey() {
		return btnCopyPublicKey;
	}

	public void setBtnCopyPublicKey(JButton btnCopyPublicKey) {
		this.btnCopyPublicKey = btnCopyPublicKey;
	}

	public JRadioButton getRdString() {
		return rdString;
	}

	public void setRdString(JRadioButton rdString) {
		this.rdString = rdString;
	}

	public JRadioButton getRdFile() {
		return rdFile;
	}

	public void setRdFile(JRadioButton rdFile) {
		this.rdFile = rdFile;
	}

	public JTextField getTxtPublicKey() {
		return txtPublicKey;
	}

	public void setTxtPublicKey(JTextField txtPublicKey) {
		this.txtPublicKey = txtPublicKey;
	}

	public JTextField getTxtPrivateKey() {
		return txtPrivateKey;
	}

	public void setTxtPrivateKey(JTextField txtPrivateKey) {
		this.txtPrivateKey = txtPrivateKey;
	}

	public Dimension getDmTxtKey() {
		return dmTxtKey;
	}

	public void setDmTxtKey(Dimension dmTxtKey) {
		this.dmTxtKey = dmTxtKey;
	}

	public Dimension getDmBtnCopy() {
		return dmBtnCopy;
	}

	public void setDmBtnCopy(Dimension dmBtnCopy) {
		this.dmBtnCopy = dmBtnCopy;
	}

	public Dimension getDmBtnCreate() {
		return dmBtnCreate;
	}

	public void setDmBtnCreate(Dimension dmBtnCreate) {
		this.dmBtnCreate = dmBtnCreate;
	}

	public Dimension getDmChoose() {
		return dmChoose;
	}

	public void setDmChoose(Dimension dmChoose) {
		this.dmChoose = dmChoose;
	}

	public JFileChooser getjFileChoose() {
		return jFileChoose;
	}

	public void setjFileChoose(JFileChooser jFileChoose) {
		this.jFileChoose = jFileChoose;
	}

	public void setOptionEncryptUI(OptionEncryptUI optionEncryptUI) {
		this.optionEncryptUI = optionEncryptUI;
	}

	public JPanel getPnFieldPublicContainer() {
		return pnFieldPublicContainer;
	}

	public void setPnFieldPublicContainer(JPanel pnFieldPublicContainer) {
		this.pnFieldPublicContainer = pnFieldPublicContainer;
	}

	public JPanel getPnSum() {
		return pnSum;
	}

	public void setPnSum(JPanel pnSum) {
		this.pnSum = pnSum;
	}

	public File getPublicFile() {
		return publicFile;
	}

	public void setPublicFile(File publicFile) {
		this.publicFile = publicFile;
	}

	public File getPrivateFile() {
		return privateFile;
	}

	public void setPrivateFile(File privateFile) {
		this.privateFile = privateFile;
	}

	public OptionGeneralUI getGeneralUI() {
		return generalUI;
	}

	public void setGeneralUI(OptionGeneralUI generalUI) {
		this.generalUI = generalUI;
	}

	public JPanel getPnOptionContainer() {
		return pnOptionContainer;
	}

	public void setPnOptionContainer(JPanel pnOptionContainer) {
		this.pnOptionContainer = pnOptionContainer;
	}

}
