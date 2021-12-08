package GUI;

import java.awt.BorderLayout;
import java.awt.Choice;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;

import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import javax.swing.BoxLayout;

public class TabAsymmetric extends JPanel implements ActionListener {
	private String[] listAlgorithms = { "RSA" };
	private String[] listMode = { "ECB" };
	private String[] listPadding = {"OAEPWithSHA-1AndMGF1Padding","OAEPWithSHA-256AndMGF1Padding","PKCS1Padding"
			 };

			
	private String[] listKeySize = { "1024", "2048", "3072", "4069" };
	private JPanel pnOption, pnKey, pnEncypt, pnAlgorithms, pnKeySize, pnMode,
			pnPadding, pnOptionKey, pnContainerKey, pnFieldPublic,pnPrivateKey,
			pnFieldPrivate, pnFilePublic, pnFilePrivte, pnFileKey,pnFieldPublicContainer,pnSum;
	private JLabel lblAlgorithms, lblKeySize, lblMode, lblPadding,
			lblPrivatekey;
	private JComboBox choiceAlgorithms, choiceKeySize, choiceMode, choicePadding;
	private JButton btnCreateKey, btnImportPublicKey, btnImportPrivatekey,
			btnCopyPrivateKey, btnCopyPublicKey;
	private JRadioButton rdString, rdFile;
	private JTextField txtPublicKey, txtPrivateKey;
	private Dimension dmTxtKey, dmBtnCopy, dmBtnCreate, dmChoose;
	private OptionEncryptUI optionEncryptUI;
	File publicFile,privateFile;
	JFileChooser jFileChoose;

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
		pnFieldPublicContainer = new JPanel(new BorderLayout());
		pnSum = new JPanel(new BorderLayout());
		pnOption = new JPanel();
		pnAlgorithms = new JPanel();
		pnKeySize = new JPanel();
		pnMode = new JPanel();
		pnPadding = new JPanel();
		pnKey = new JPanel(new BorderLayout());
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
		pnOptionKey = new JPanel(new GridBagLayout());
		pnContainerKey = new JPanel(new BorderLayout());
		rdString = new JRadioButton("String");
		rdFile = new JRadioButton("File");
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
		pnFieldPublic = new JPanel(new BorderLayout());
		pnFieldPrivate = new JPanel(new BorderLayout());
		pnFilePublic = new JPanel();
		pnFilePrivte = new JPanel();
		pnFileKey = new JPanel(new BorderLayout());
		
		btnImportPublicKey = new JButton("Choose key public");
		btnImportPrivatekey = new JButton("Choose key private");
		optionEncryptUI = new OptionEncryptUI();
		optionEncryptUI.remove(optionEncryptUI.pnRadio);
		jFileChoose = new JFileChooser();
		pnPrivateKey = new JPanel(new BorderLayout());
		lblPrivatekey = new JLabel("Private key:",SwingConstants.CENTER);
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
		pnOptionKey.add(rdString);
		pnOptionKey.add(rdFile);
		pnSum.add(pnContainerKey);
//		pnSum.add(pnFileKey);
		pnFieldPublic.add(btnCreateKey,BorderLayout.WEST);
		pnFieldPublic.add(txtPublicKey);
		pnFieldPublicContainer.add(pnFieldPublic);
		pnFieldPublicContainer.add(btnCopyPublicKey,BorderLayout.EAST);
		pnFieldPrivate.add(btnCopyPrivateKey,BorderLayout.EAST);
		pnFieldPrivate.add(pnPrivateKey);
		pnPrivateKey.add(lblPrivatekey,BorderLayout.WEST);
		pnPrivateKey.add(txtPrivateKey);

		pnContainerKey.add(pnFieldPublicContainer, BorderLayout.NORTH);
		pnContainerKey.add(pnFieldPrivate, BorderLayout.SOUTH);
		pnFilePrivte.setLayout(new BorderLayout(0, 0));

	
		pnFilePrivte.add(btnImportPrivatekey);
		pnFilePublic.setLayout(new BorderLayout(0, 0));

	
		pnFilePublic.add(btnImportPublicKey);

		pnFileKey.add(pnFilePublic, BorderLayout.NORTH);
		pnFileKey.add(pnFilePrivte, BorderLayout.SOUTH);
		add(pnOption, BorderLayout.NORTH);
		add(pnKey, BorderLayout.CENTER);
		add(optionEncryptUI, BorderLayout.SOUTH);
		optionEncryptUI.getTxtPlain().setSize(7, 50);
//		optionEncryptUI.setPreferredSize(new Dimension(750, 265));
//		Border optionLine = BorderFactory.createTitledBorder("Option");
//		pnOption.setBorder(optionLine);
		setBorder(new EmptyBorder(5, 0, 10, 0));
//		((TitledBorder) pnOption.getBorder()).setTitleFont(new Font("Dialog",
//				Font.PLAIN, 13));
//		Border keyLine = BorderFactory.createTitledBorder("Key");
//		pnKey.setBorder(keyLine);
//		((TitledBorder) pnKey.getBorder()).setTitleFont(new Font("Dialog",
//				Font.PLAIN, 13));
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
		pnAlgorithms.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnKeySize.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnMode.setBorder(new EmptyBorder(0, 5, 0, 5));
		pnPadding.setBorder(new EmptyBorder(0, 5, 0, 5));
		choiceAlgorithms.setFocusable(false);
		choiceKeySize.setFocusable(false);
		choiceMode.setFocusable(false);
		choicePadding.setFocusable(false);

		pnFieldPrivate.setBorder(new EmptyBorder(-5, 45, 0, 0));
		lblPrivatekey.setPreferredSize(new Dimension(120,40));
		txtPublicKey.setPreferredSize(dmTxtKey);
		txtPrivateKey.setPreferredSize(dmTxtKey);
		btnCreateKey.setPreferredSize(dmBtnCreate);
		btnCopyPublicKey.setPreferredSize(dmBtnCopy);
		btnCopyPrivateKey.setPreferredSize(dmBtnCopy);
		btnImportPublicKey.setPreferredSize(dmChoose);
		btnImportPrivatekey.setPreferredSize(dmChoose);
		choicePadding.setPreferredSize(new Dimension(190,30));
		optionEncryptUI.getBtnChooseInput().setPreferredSize(dmChoose);
		optionEncryptUI.getBtnChooseOutput().setPreferredSize(dmChoose);
		pnOption.setBorder(new EmptyBorder(20, 10, 20, 10));
		pnKey.setBorder(new EmptyBorder(0, 20, 0, 20));
		pnFieldPrivate.setBorder(new EmptyBorder(0, 0, 0, 0));
		optionEncryptUI.setPreferredSize( new Dimension(740, 280));
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
					privateFile = openFile();
					btnImportPrivatekey.setText(privateFile.getAbsolutePath());
				} catch (Exception ex) {
					System.out.println("Cancel choose file");
				}

			}
		});
		btnImportPublicKey.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					publicFile = openFile();
					btnImportPublicKey.setText(publicFile.getAbsolutePath());
				} catch (Exception ex) {
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
	public OptionEncryptUI getOptionEncryptUI(){
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
	
}
